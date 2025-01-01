/*package ihm;

import java.net.*;
import java.io.*;
import java.util.concurrent.*;


public class GameServer {
    private static final int PORT = 12345; // Port for the server to listen on
    private final ConcurrentHashMap<Integer, PrintWriter> clients = new ConcurrentHashMap<>(); // Tracks connected clients
    private final GameState gameState = new GameState(); // Global game state
    private String lastBroadcastState = ""; // Track the last broadcasted state
    
    

    public static void main(String[] args) {
        new GameServer().start(); // Start the server
    }

    public void start() {
        System.out.println("Starting the Game Server on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running and waiting for clients...");

            // Periodically broadcast the global game state to all clients
            Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
                if (!clients.isEmpty()) { // Only broadcast if there are clients connected
                    try {
                        String serializedState = gameState.serialize(); // Convert game state to a string
                        broadcast(serializedState); // Send the state to all clients
                        System.out.println("Number of connected clients: " + getClientCount()); // Log the client count
                    } catch (Exception e) {
                        System.err.println("Error broadcasting game state: " + e.getMessage());
                    }
                }
            }, 0, 40, TimeUnit.MILLISECONDS); // Update every 40 ms (~25 FPS)

            while (true) {
                // Accept new client connections
                Socket clientSocket = serverSocket.accept();
                int clientId = clientSocket.hashCode(); // Use the hashcode as a unique client ID
                System.out.println("New client connected: " + clientSocket.getInetAddress());

                // Add client to the map of connected clients
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                clients.put(clientId, out);

                // Handle the client in a separate thread
                new Thread(new ClientHandler(clientSocket, clientId)).start();
            }
        } catch (IOException e) {
            System.err.println("Error in server operation: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to get the number of connected clients
    public int getClientCount() {
        return clients.size();
    }

   public void broadcast(String message) {
    if (!message.equals(lastBroadcastState)) {
        for (PrintWriter client : clients.values()) {
            client.println(message);
        }
        lastBroadcastState = message; // Update the last broadcasted state
    }
}


    // Remove a disconnected client from the list
    public void removeClient(int clientId) {
        clients.remove(clientId);
        System.out.println("Client " + clientId + " disconnected.");
    }

    // Inner class to handle each client connection
    private class ClientHandler implements Runnable {
        private final Socket socket;
        private final int clientId;

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
                    try {
                        gameState.updateFromClient(clientId, input); // Update the global game state based on client input
                    } catch (Exception e) {
                        System.err.println("Error updating game state from client " + clientId + ": " + e.getMessage());
                    }
                }
            } catch (IOException e) {
                System.err.println("Error with client " + clientId + ": " + e.getMessage());
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
*/
/*package ihm;

import java.net.*;
import java.io.*;
import java.util.concurrent.*;
import java.util.HashMap;

public class GameServer {
    private static final int PORT = 12345; // Port for the server to listen on
    private ConcurrentHashMap<Integer, PrintWriter> clients = new ConcurrentHashMap<>(); // Tracks connected clients
    private GameState gameState = new GameState(); // Global game state
    private volatile boolean gameStarted = false; // Tracks if the game has started

    public static void main(String[] args) {
        new GameServer().start(); // Start the server
    }

    public void start() {
        System.out.println("Starting the Game Server on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running and waiting for clients...");

            // Periodically broadcast the global game state to all clients
            Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
                if (gameStarted) { // Only broadcast if the game has started
                    if (!clients.isEmpty()) {
                        String serializedState = gameState.serialize();
                        broadcast(serializedState);
                        System.out.println("Number of connected clients: " + getClientCount());
                    }
                } else if (getClientCount() >= 2) { // Start the game when two clients are connected
                    gameStarted = true;
                    System.out.println("Game is starting with " + getClientCount() + " players!");
                    broadcast("GAME_START");
                }
            }, 0, 40, TimeUnit.MILLISECONDS);

            while (true) {
                // Accept new client connections
                Socket clientSocket = serverSocket.accept();
                int clientId = clientSocket.hashCode();
                System.out.println("New client connected: " + clientSocket.getInetAddress());

                // Add client to the map of connected clients
                clients.put(clientId, new PrintWriter(clientSocket.getOutputStream(), true));

                // Handle the client in a separate thread
                new Thread(new ClientHandler(clientSocket, clientId)).start();
            }
        } catch (IOException e) {
            System.err.println("Error in server operation: " + e.getMessage());
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
                    gameState.updateFromClient(clientId, input); // Update the global game state based on client input
                }
            } catch (IOException e) {
                System.err.println("Error with client " + clientId + ": " + e.getMessage());
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
}*/
// GameServer.java
/*package ihm;

import java.net.*;
import java.io.*;
import java.util.concurrent.*;

public class GameServer {
    private static final int PORT = 12345;
    private ConcurrentHashMap<Integer, PrintWriter> clients = new ConcurrentHashMap<>();
    private GameState gameState = new GameState(); // Global game state
    private String lastBroadcastState = "";

    public static void main(String[] args) {
        new GameServer().start();
    }

    public void start() {
        System.out.println("Starting the Game Server on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running and waiting for clients...");

            // Periodically broadcast the global game state to all clients
            Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
                if (!clients.isEmpty()) {
                    String serializedState = gameState.serialize();
                    broadcast(serializedState);
                    System.out.println("Number of connected clients: " + getClientCount());
                }
            }, 0, 40, TimeUnit.MILLISECONDS);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                int clientId = clientSocket.hashCode();
                System.out.println("New client connected: " + clientSocket.getInetAddress());

                clients.put(clientId, new PrintWriter(clientSocket.getOutputStream(), true));

                new Thread(new ClientHandler(clientSocket, clientId)).start();

                // Start game if at least two players are connected
                if (getClientCount() == 2) {
                    broadcast("GAME_START");
                }
            }
        } catch (IOException e) {
            System.err.println("Error in server operation: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public int getClientCount() {
        return clients.size();
    }

    public void broadcast(String message) {
        if (!message.equals(lastBroadcastState)) {
            for (PrintWriter client : clients.values()) {
                client.println(message);
            }
            lastBroadcastState = message;
        }
    }

    public void removeClient(int clientId) {
        clients.remove(clientId);
        System.out.println("Client " + clientId + " disconnected.");
    }

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
                    gameState.updateFromClient(clientId, input);
                }
            } catch (IOException e) {
                System.err.println("Error with client " + clientId + ": " + e.getMessage());
            } finally {
                removeClient(clientId);
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}*/
package ihm;

import java.net.*;
import java.io.*;
import java.util.concurrent.*;
import java.util.HashMap;

public class GameServer {
    private static final int PORT = 12345; // Port for the server to listen on
    private ConcurrentHashMap<Integer, PrintWriter> clients = new ConcurrentHashMap<>(); // Tracks connected clients
    private GameState gameState = new GameState(); // Global game state
    private volatile boolean gameStarted = false; // Tracks if the game has started

    public static void main(String[] args) {
        new GameServer().start(); // Start the server
    }

    public void start() {
        System.out.println("Starting the Game Server on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running and waiting for clients...");

            // Periodically broadcast the global game state to all clients
            Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
                if (gameStarted) { // Only broadcast if the game has started
                    if (!clients.isEmpty()) {
                        String serializedState = gameState.serialize();
                        broadcast(serializedState);
                        System.out.println("Number of connected clients: " + getClientCount());
                    }
                } else if (getClientCount() >= 2) { // Start the game when two clients are connected
                    gameStarted = true;
                    System.out.println("Game is starting with " + getClientCount() + " players!");
                    broadcast("GAME_START");
                }
            }, 0, 40, TimeUnit.MILLISECONDS);

            while (true) {
                // Accept new client connections
                Socket clientSocket = serverSocket.accept();
                int clientId = clientSocket.hashCode();
                System.out.println("New client connected: " + clientSocket.getInetAddress());

                // Add client to the map of connected clients
                clients.put(clientId, new PrintWriter(clientSocket.getOutputStream(), true));

                // Handle the client in a separate thread
                new Thread(new ClientHandler(clientSocket, clientId)).start();
            }
        } catch (IOException e) {
            System.err.println("Error in server operation: " + e.getMessage());
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
                    gameState.updateFromClient(clientId, input); // Update the global game state based on client input
                }
            } catch (IOException e) {
                System.err.println("Error with client " + clientId + ": " + e.getMessage());
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
