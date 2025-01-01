/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ihm;
import java.net.*;
import java.io.*;

/**
 *
 * @author zzahir
 */
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

