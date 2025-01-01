/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ihm;
import java.util.concurrent.ConcurrentHashMap;


public class GameState {
     private ConcurrentHashMap<Integer, PlayerState> players = new ConcurrentHashMap<>(); // Tracks all player states

    // Serialize the game state to a string (e.g., JSON or plain text)
    public synchronized String serialize() {
        StringBuilder sb = new StringBuilder();
        sb.append("GAME_STATE\n");
        for (var entry : players.entrySet()) {
            int clientId = entry.getKey();
            PlayerState playerState = entry.getValue();
            sb.append("Player ").append(clientId).append(": ").append(playerState).append("\n");
        }
        return sb.toString();
    }

    // Update the global game state based on a client's input
    public synchronized void updateFromClient(int clientId, String input) {
        players.computeIfAbsent(clientId, id -> new PlayerState(600,400)); // Create a new player if they don't exist

        PlayerState player = players.get(clientId);
        switch (input) {
            case "MOVE_HOOK_UP" -> player.moveHookUp();
            case "MOVE_HOOK_DOWN" -> player.moveHookDown();
            case "MOVE_HOOK_LEFT" -> player.moveHookLeft();
            case "MOVE_HOOK_RIGHT" -> player.moveHookRight();
        }
    }
}

// GameState.java
/*package ihm;

import java.util.concurrent.ConcurrentHashMap;

public class GameState {
    private ConcurrentHashMap<Integer, PlayerState> players = new ConcurrentHashMap<>();

    public void updateFromClient(int clientId, String input) {
        PlayerState state = players.computeIfAbsent(clientId, id -> new PlayerState());

        switch (input) {
            case "MOVE_UP":
                state.moveHookUp();
                break;
            case "MOVE_DOWN":
                state.moveHookDown();
                break;
            case "MOVE_LEFT":
                state.moveHookLeft();
                break;
            case "MOVE_RIGHT":
                state.moveHookRight();
                break;
        }
    }

    public String serialize() {
        StringBuilder sb = new StringBuilder();
        for (int clientId : players.keySet()) {
            PlayerState state = players.get(clientId);
            sb.append("Player ")
              .append(clientId)
              .append(": Hook Position: (")
              .append(state.getHookX())
              .append(", ")
              .append(state.getHookY())
              .append(")\n");
        }
        return sb.toString();
    }

    public ConcurrentHashMap<Integer, PlayerState> getPlayers() {
        return players;
    }
}*/