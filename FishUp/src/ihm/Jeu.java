package ihm;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Font;

/**
 * Main game logic class
 */
public class Jeu {
    private BufferedImage decor;
    private int score;
    private FenetreDeJeu fenetre;
    private Hook hook;
    private Boat boat;
    private Carte carte;
    private List<Pike> poissons;
    private List<PlayerState> players;
    private GameServer gameserver;

    // Constructor for multiplayer with a GameServer
    public Jeu(GameServer gameserver) {
        this.gameserver = gameserver;
        initializeGame();
    }

    // Constructor for single-player or with a FenetreDeJeu
    public Jeu(FenetreDeJeu fenetre) {
        this.fenetre = fenetre;
        initializeGame();
    }

    // Shared initialization logic
    private void initializeGame() {
        this.score = 0;
        this.hook = new Hook();
        this.boat = new Boat();
        this.carte = new Carte();
        this.poissons = new ArrayList<>();
        this.players = new ArrayList<>();

        // Add fish to the game
        for (int i = 0; i < 10; i++) {
            poissons.add(new Pike());
        }
    }

    // Update the game state based on the server input
    public void updateFromServer(String serverState) {
        players.clear(); // Clear the current state
        String[] lines = serverState.split("\n");
        for (String line : lines) {
            if (line.startsWith("Player")) {
                String[] parts = line.split(":");
                String[] position = parts[1].trim().replace("Hook Position: (", "").replace(")", "").split(",");
                int x = Integer.parseInt(position[0].trim());
                int y = Integer.parseInt(position[1].trim());
                players.add(new PlayerState(x, y));
            }
        }
    }

    public void rendu(Graphics2D contexte) {
        // Draw the background (decor)
        this.carte.rendu(contexte);

        // Draw the boat, hook, and fish
        this.boat.rendu(contexte);
        this.hook.rendu(contexte);
        for (Pike poisson : poissons) {
            poisson.rendu(contexte);
        }

        // Draw time remaining and score
        int tempsRestant = fenetre != null ? fenetre.getTempsRestant() : 0;

        contexte.setFont(new Font("Arial", Font.BOLD, 24));
        contexte.setColor(Color.WHITE);
        contexte.drawString("Temps restant : " + (tempsRestant / 1000) + " s", 900, 50);
        contexte.setFont(contexte.getFont().deriveFont(18f));
        contexte.drawString("Score Actuel : " + score, 900, 80);
    }

    public void miseAJour() {
        this.carte.miseAJour();
        this.hook.miseAJour();
        for (Pike poisson : poissons) {
            poisson.miseAJour();
            if (collisionEntreHookEtPike(poisson)) {
                this.score += 10;
                poisson.lancer();
            }
        }
    }

    public Hook getHook() {
        return hook;
    }

    public int getScore() {
        return score;
    }

    private boolean collisionEntreHookEtPike(Pike poisson) {
        return !(poisson.getX() >= hook.getX() + hook.getLargeur()
                || poisson.getX() + poisson.getLargeur() <= hook.getX()
                || poisson.getY() >= hook.getY() + hook.getHauteur()
                || poisson.getY() + poisson.getHauteur() <= hook.getY());
    }
}
