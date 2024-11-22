/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ihm;

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
public class Jeu {
    
    private BufferedImage decor;
    private int score;
    private Banane uneBanane1;
    private Banane uneBanane2;
    private Avatar unAvatar;
    
    public Jeu() {
        try {
            this.decor = ImageIO.read(getClass().getResource("/resources/jungle.png"));
        } catch (IOException ex) {
            Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.score = 0;
        this.uneBanane1 = new Banane();
        this.uneBanane2 = new Banane();
        this.unAvatar = new Avatar();
    }
    
    public void rendu(Graphics2D contexte) {
        //System.out.println("azeza");
        contexte.drawImage(this.decor, 0, 0, null);
        contexte.drawString("Score : " + score, 10, 20);
        this.uneBanane1.rendu(contexte);
        this.uneBanane2.rendu(contexte);
        this.unAvatar.rendu(contexte);
        // 1. Rendu du décor
        // 2. Rendu des sprites
        // 3. Rendu des textes
    }
    public void miseAJour() {
        this.uneBanane1.miseAJour();
        this.uneBanane2.miseAJour();
        this.unAvatar.miseAJour();
        if (collisionEntreAvatarEtBanane1()) {
            this.score += 10;
            uneBanane1.lancer();
        }
        if (collisionEntreAvatarEtBanane2()) {
            this.score += 10;
            uneBanane2.lancer();
        }
        // 1. Mise à jour de l’avatar en fonction des commandes des joueurs
        // 2. Mise à jour des autres éléments (objets, monstres, etc.)
        // 3. Gérer les interactions (collisions et autres règles)
    }
    
    public  Avatar getAvatar() {
        return unAvatar;   
    }
    
    public boolean collisionEntreAvatarEtBanane1() {
        if ((uneBanane1.getX() >= unAvatar.getX() + unAvatar.getLargeur()) // trop à droite
                || (uneBanane1.getX() + uneBanane1.getLargeur() <= unAvatar.getX()) // trop à gauche
                || (uneBanane1.getY() >= unAvatar.getY() + unAvatar.getHauteur()) // trop en bas
                || (uneBanane1.getY()+ uneBanane1.getHauteur() <= unAvatar.getY())) { // trop en haut
            return false;
        } else {
            return true;
        }
    }
    
    public boolean collisionEntreAvatarEtBanane2() {
        if ((uneBanane2.getX() >= unAvatar.getX() + unAvatar.getLargeur()) // trop à droite
                || (uneBanane2.getX() + uneBanane2.getLargeur() <= unAvatar.getX()) // trop à gauche
                || (uneBanane2.getY() >= unAvatar.getY() + unAvatar.getHauteur()) // trop en bas
                || (uneBanane2.getY()+ uneBanane2.getHauteur() <= unAvatar.getY())) { // trop en haut
            return false;
        } else {
            return true;
        }
    }
    
    public boolean estTermine1() {
        // Renvoie vrai si la partie est terminée (gagnée ou perdue)
        return this.uneBanane1.getY()>400;
    //return false ;
    }
    
    public boolean estTermine2() {
        // Renvoie vrai si la partie est terminée (gagnée ou perdue)
        return this.uneBanane2.getY()>400;
    //return false ;
    }
    
}
