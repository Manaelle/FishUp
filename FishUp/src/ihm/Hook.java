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
import sql.*;

/**
 *
 * @author tpereira
 */
public class Hook {
    
    protected BufferedImage sprite;
    protected double x, y;
    private boolean toucheGauche;
    private boolean toucheDroite;
    private boolean toucheHaut;
    private boolean toucheBas;
    private int ID;

    public Hook(int ID) {
        try {
            this.sprite = ImageIO.read(getClass().getResource("../resources/Hook2.png"));
        } catch (IOException ex) {
            Logger.getLogger(Pike.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.x = 600;
        this.y = 400;
        this.toucheGauche = false;
        this.toucheDroite = false;
        this.toucheHaut = false;
        this.toucheBas = false;
        this.ID = ID;
    }
    
    

    public void miseAJour() {
        if (this.toucheGauche) {
            x -= 8;
        }
        if (this.toucheDroite) {
            x += 8;
        }
        if (this.toucheHaut) {
            y += 8;
        }
        if (this.toucheBas) {
            y -= 8;
        }
        if (x > 1184 - sprite.getWidth()) { // collision avec le bord droit de la scene
            x = 1184 - sprite.getWidth() ;
        }
        if (x < 96) { // collision avec le bord gauche de la scene
            x = 96;
        }
        if (y > 800 - sprite.getWidth()) { // collision avec le bord haut de la scene
            y = 800 - sprite.getWidth() ;
        }
        if (y < 96) { // collision avec le bord bas de la scene
            y = 96;
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
    
    public void setHaut(boolean haut) {
        this.toucheBas = haut;
}
    
        public void setBas(boolean bas) {
        this.toucheHaut = bas;
}

    
    public int getHook_id() {
        return ID;
    }
    

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

/*
    public void mettreAJourCoordonnees() {
        HookSQL hookSQL = new HookSQL(); // Crée une instance de HookSQL
        double[] coords = hookSQL.voirHookXY(ID); // Appelle voirHookXY
        this.x = coords[0];
        this.y = coords[1];
    }
    */
    public double getLargeur() {
        return sprite.getHeight();
    }

    public double getHauteur() {
        return sprite.getWidth();
    }

    
}
