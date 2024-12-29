/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;
import java.io.*;   // For handling input and output streams
import java.net.*;  // For networking classes like ServerSocket and Socket
import java.util.*; // For using ArrayList to store connected clients

/**
 *
 * @author zzahir
 */
public class GameServer {
    private static final int MIN_PLAYERS = 4;
    private static List<Socket> players = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Serveur en attente de connexions...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                players.add(clientSocket);
                System.out.println("Client connecté : " + clientSocket.getInetAddress());
                if (players.size() >= MIN_PLAYERS) {
                    System.out.println("Nombre minimum de joueurs atteint. Partie démarrée !");
                    startGame();
                    players.clear();
                }
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void startGame() {
        synchronized (players) {
            for (Socket player : players) {
                try {
                    PrintWriter out = new PrintWriter(player.getOutputStream(), true);
                    out.println("La partie commence !");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}