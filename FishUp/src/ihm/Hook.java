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
public class Hook {
    
    protected BufferedImage sprite;
    protected double x, y;
    private boolean toucheGauche;
    private boolean toucheDroite;

    public Hook() {
        try {
            this.sprite = ImageIO.read(getClass().getResource("../resources/donkeyKong.png"));
        } catch (IOException ex) {
            Logger.getLogger(Pike.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.x = 170;
        this.y = 320;
        this.toucheGauche = false;
        this.toucheDroite = false;
    }

    public void miseAJour() {
        if (this.toucheGauche) {
            x -= 5;
        }
        if (this.toucheDroite) {
            x += 5;
        }
        if (x > 380 - sprite.getWidth()) { // collision avec le bord droit de la scene
            x = 380 - sprite.getWidth() ;
        }
        if (x < 0) { // collision avec le bord gauche de la scene
            x = 0;
        }
    }

    public void rendu(Graphics2D contexte) {
        contexte.drawImage(this.sprite, (int) x, (int) y, null);
    }

    public void setGauche(boolean gauche) {
        this.toucheGauche = gauche;
}
    public void setDroite(boolean droite) {
        this.toucheDroite = droite;
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
