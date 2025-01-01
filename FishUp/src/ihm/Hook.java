/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ihm;

/**
 *
 * @author tpereira
 */
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.util.List;
import java.util.Random;

/**
 *
 * @author tpereira
 */
public class Hook {
    protected BufferedImage sprite;
    protected double x, y;
    private GameServer gameserver;
    private boolean toucheGauche;
    private boolean toucheDroite;
    private boolean toucheHaut;
    private boolean toucheBas;

    // Constructor with GameServer for multiplayer
    public Hook(GameServer gameserver) {
        this.gameserver = gameserver;
        initializeHook();
    }

    // Default constructor for single-player or local mode
    public Hook() {
        initializeHook();
    }

    // Unified initialization logic
    private void initializeHook() {
        this.x = 600; // Initial x position
        this.y = 400; // Initial y position
        this.toucheGauche = false;
        this.toucheDroite = false;
        this.toucheHaut = false;
        this.toucheBas = false;

        assignSpriteBasedOnClients(); // Assign sprite based on client count or default logic
    }

    // Assigns a unique sprite to the hook based on the number of clients
    private void assignSpriteBasedOnClients() {
        List<String> images = new ArrayList<>();
        images.add("../resources/Yellow_Hook.png");
        images.add("../resources/Green_Hook.png");
        images.add("../resources/Red_Hook.png");
        images.add("../resources/Blue_Hook.png");

        Random random = new Random();
        int clientCount = gameserver != null ? gameserver.getClientCount() : 1; // Default to 1 if no GameServer

        try {
            if (clientCount > images.size()) {
                Logger.getLogger(Hook.class.getName()).log(Level.WARNING, 
                    "More clients than available images. Assigning randomly.");
            }

            // Assign an image based on the client count
            int index = Math.min(clientCount - 1, images.size() - 1);
            String chosenImage = images.get(index);
            this.sprite = ImageIO.read(getClass().getResource(chosenImage));

        } catch (IOException ex) {
            Logger.getLogger(Hook.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Updates the hook's position based on key inputs
    public void miseAJour() {
        if (this.toucheGauche) {
            x -= 5;
        }
        if (this.toucheDroite) {
            x += 5;
        }
        if (this.toucheHaut) {
            y -= 5;
        }
        if (this.toucheBas) {
            y += 5;
        }

        // Ensure the hook stays within bounds
        if (x > 1184 - sprite.getWidth()) { // Right boundary
            x = 1184 - sprite.getWidth();
        }
        if (x < 96) { // Left boundary
            x = 96;
        }
        if (y > 800 - sprite.getWidth()) { // Bottom boundary
            y = 800 - sprite.getWidth();
        }
        if (y < 96) { // Top boundary
            y = 96;
        }
    }

    // Renders the hook on the screen
    public void rendu(Graphics2D contexte) {
        contexte.drawImage(this.sprite, (int) x, (int) y, null);
    }

    // Movement setters
    public void setGauche(boolean gauche) {
        this.toucheGauche = gauche;
    }

    public void setDroite(boolean droite) {
        this.toucheDroite = droite;
    }

    public void setHaut(boolean haut) {
        this.toucheHaut = haut;
    }

    public void setBas(boolean bas) {
        this.toucheBas = bas;
    }

    // Getters for position and dimensions
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getLargeur() {
        return sprite != null ? sprite.getHeight() : 0;
    }

    public double getHauteur() {
        return sprite != null ? sprite.getWidth() : 0;
    }  
}
