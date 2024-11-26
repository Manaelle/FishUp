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
 *
 * @author tpereira
 */
public class Boat {
    
    protected BufferedImage sprite;
    protected double x, y;

    public Boat() {
        try {
            this.sprite = ImageIO.read(getClass().getResource("../resources/bateau-peche1.png"));
        } catch (IOException ex) {
            Logger.getLogger(Pike.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // We can make a conditional for the amount of players to have them at equal distances
        // ex: if we have two players, the boats would be more spaced, if we have 3, they  would have their distance lowered
        
        this.x = 170;
        this.y = -30;
    }

    public void miseAJour() {
        if (x > 800 - sprite.getWidth()) { // collision avec le bord droit de la scene
            x = 800 - sprite.getWidth() ;
        }
        if (x < 0) { // collision avec le bord gauche de la scene
            x = 0;
        }
        if (y > 429 - sprite.getWidth()) { // collision avec le bord haut de la scene
            y = 429 - sprite.getWidth() ;
        }
        if (y < -30) { // collision avec le bord bas de la scene
            y = -30;
        }
    }

    public void rendu(Graphics2D contexte) {
        contexte.drawImage(this.sprite, (int) x, (int) y, null);
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
