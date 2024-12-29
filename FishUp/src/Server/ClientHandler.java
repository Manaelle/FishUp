/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;
import java.io.*;
import java.net.*;

/**
 *
 * @author zzahir
 */
class ClientHandler implements Runnable {
    private Socket socket; // Socket pour gérer la connexion avec un client spécifique

    public ClientHandler(Socket socket) {
        this.socket = socket; // Initialise le socket avec celui fourni lors de la connexion
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Flux pour lire les données reçues
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) { // Flux pour envoyer des données au client
            String message;
            while ((message = in.readLine()) != null) { // Boucle pour lire les messages envoyés par le client
                System.out.println("Message reçu : " + message); // Affiche chaque message reçu pour suivi
                // Ici, on peut traiter le message ou diffuser aux autres joueurs
            }
        } catch (IOException e) {
            e.printStackTrace(); // Affiche les erreurs liées aux flux de communication
        }
    }
}
