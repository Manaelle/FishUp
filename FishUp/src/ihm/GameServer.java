package ihm;
import java.net.*;
import java.io.*;
import java.util.concurrent.*;

public class GameServer {
    private static final int PORT = 12345; // Port for the server to listen on
    private ConcurrentHashMap<Integer, PrintWriter> clients = new ConcurrentHashMap<>(); // Tracks connected clients
    private GameState gamestate = new GameState(); // Global game state

    public static void main(String[] args) {
        new GameServer().start(); // Start the server
    }

    public void start() {
        System.out.println("Starting the Game Server on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running and waiting for clients...");

            // Periodically broadcast the global game state to all clients
            Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
                String serializedState = gamestate.serialize(); // Convert game state to a string
                broadcast(serializedState); // Send the state to all clients
                System.out.println("Number of connected clients: " + getClientCount()); // Log the client count
            }, 0, 40, TimeUnit.MILLISECONDS); // Update every 40 ms (~25 FPS)

            while (true) {
                // Accept new client connections
                Socket clientSocket = serverSocket.accept();
                int clientId = clientSocket.hashCode(); // Use the hashcode as a unique client ID
                System.out.println("New client connected: " + clientSocket.getInetAddress());

                // Add client to the map of connected clients
                clients.put(clientId, new PrintWriter(clientSocket.getOutputStream(), true));

                // Handle the client in a separate thread
                new Thread(new ClientHandler(clientSocket, clientId)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to get the number of connected clients
    public int getClientCount() {
        return clients.size();
    }

    // Broadcast a message to all connected clients
    public void broadcast(String message) {
        for (PrintWriter client : clients.values()) {
            client.println(message);
        }
    }

    // Remove a disconnected client from the list
    public void removeClient(int clientId) {
        clients.remove(clientId);
        System.out.println("Client " + clientId + " disconnected.");
    }

    // Inner class to handle each client connection
    private class ClientHandler implements Runnable {
        private Socket socket;
        private int clientId;

        public ClientHandler(Socket socket, int clientId) {
            this.socket = socket;
            this.clientId = clientId;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String input;
                while ((input = in.readLine()) != null) {
                    System.out.println("Received from client " + clientId + ": " + input);
                    gamestate.updateFromClient(clientId, input); // Update the global game state based on client input
                }
            } catch (IOException e) {
                System.out.println("Error with client " + clientId + ": " + e.getMessage());
            } finally {
                removeClient(clientId); // Remove client on disconnect
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
