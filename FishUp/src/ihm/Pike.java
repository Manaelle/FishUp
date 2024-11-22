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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Exemple de classe lutin
 *
 * @author guillaume.laurent
 */
public class Pike {

    protected BufferedImage sprite;
    protected double x, y;

    public Pike() {
        try {
            this.sprite = ImageIO.read(getClass().getResource("../resources/Brochet.png"));
        } catch (IOException ex) {
            Logger.getLogger(Hook.class.getName()).log(Level.SEVERE, null, ex);
        }
        lancer();
    }

    public void miseAJour() {
        x = x + 5;
    }

    public void rendu(Graphics2D contexte) {
        contexte.drawImage(this.sprite, (int) x, (int) y, null);
    }

    public void lancer() {
        this.y = 15 + Math.random() * 330;
        this.x = -27;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    public double getLargeur() {
        return sprite.getHeight();
    }

    public double getHauteur() {
        return sprite.getWidth();
    }

}
