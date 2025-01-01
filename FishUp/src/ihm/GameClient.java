/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
package ihm;
import java.net.*;
import java.io.*;


public class GameClient {
    private FenetreDeJeu fenetre; // Manages the game UI for the client

    public static void main(String[] args) {
        new GameClient().start();
    }

    public void start() {
        try (Socket socket = new Socket("localhost", 12345)) {
            // Input and Output streams for communication with the server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Initialize the game window (FenetreDeJeu)
            fenetre = new FenetreDeJeu(out);
            fenetre.setVisible(true);

            // Listen for updates from the server
            String serverState;
            while ((serverState = in.readLine()) != null) {
                // Update the local game state with data from the server
                fenetre.updateFromServer(serverState);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
*/
/*package ihm;

import java.net.*;
import java.io.*;

public class GameClient {
    private Socket socket;
    private PrintWriter out;
    private FenetreDeJeu fenetre;

    public static void main(String[] args) {
        new GameClient().start();
    }

    public void start() {
        try {
            socket = new Socket("127.0.0.1", 12345); // Connect to the server
            out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String input;
            boolean gameStarted = false;

            // Wait for "GAME_START" signal
            while (!gameStarted && (input = in.readLine()) != null) {
                if (input.equals("GAME_START")) {
                    gameStarted = true;
                    System.out.println("Game is starting!");
                    fenetre = new FenetreDeJeu(out); // Initialize the game window
                    fenetre.setVisible(true);
                }
            }

            // Main game loop
            while ((input = in.readLine()) != null) {
                fenetre.updateFromServer(input); // Update game state
            }
        } catch (IOException e) {
            System.err.println("Error connecting to server: " + e.getMessage());
        }
    }
}
*/
package ihm;

import java.net.*;
import java.io.*;

public class GameClient {
    private Socket socket;
    private PrintWriter out;
    private FenetreDeJeu fenetre;

    public static void main(String[] args) {
        new GameClient().start();
    }

    public void start() {
        try {
            socket = new Socket("127.0.0.1", 12345); // Connect to the server
            out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String input;
            boolean gameStarted = false;

            // Wait for "GAME_START" signal
            while (!gameStarted && (input = in.readLine()) != null) {
                if (input.equals("GAME_START")) {
                    gameStarted = true;
                    System.out.println("Game is starting!");
                    fenetre = new FenetreDeJeu(out); // Initialize the game window
                    fenetre.setVisible(true);
                }
            }

            // Main game loop
            while ((input = in.readLine()) != null) {
                fenetre.updateFromServer(input); // Update game state
            }
        } catch (IOException e) {
            System.err.println("Error connecting to server: " + e.getMessage());
        }
    }
}

