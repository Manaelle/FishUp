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
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Font;


/**
 *
 * @author tpereira
 */
public class Jeu {
    
    private BufferedImage decor;
    private int score;
    private FenetreDeJeu fenetre;
    private Pike pike1;
    private Pike pike2;
    private Hook hook;
    private Boat boat;
    private Carte carte;
    private List<Pike> poissons;
    
    public Jeu(FenetreDeJeu fenetre) {
        //try {
            //this.decor = ImageIO.read(getClass().getResource("/resources/jungle.png"));
        //} catch (IOException ex) {
            //Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
        //}
        this.fenetre = fenetre;
        this.score = 0;
        //this.pike1 = new Pike();
        //this.pike2 = new Pike();
        this.hook = new Hook();
        this.boat = new Boat();
        this.carte = new Carte();
        poissons = new ArrayList<>();
        
        for (int i = 0; i < 10; i++) {
            poissons.add(new Pike());
        }
    }
    
    public void rendu(Graphics2D contexte) {
        //System.out.println("azeza");
        //contexte.drawImage(this.decor, 0, 0, null);
        //contexte.drawString("Score : " + score, 10, 20);
        this.carte.rendu(contexte);
        //this.pike1.rendu(contexte);
        //this.pike2.rendu(contexte);
        this.hook.rendu(contexte);
        this.boat.rendu(contexte);
        for (Pike poisson : poissons) {
            poisson.rendu(contexte);
        }

        int tempsRestant = fenetre.getTempsRestant();

        contexte.setFont(new Font("Arial", Font.BOLD, 24));
        contexte.setColor(Color.WHITE);
        String textTemps = "Temps restant : " + (tempsRestant / 1000) + " s";
        contexte.drawString(textTemps, 900, 50);
        contexte.setFont(contexte.getFont().deriveFont(18f));
        String textScore = "Score Actuel : " + (score);
        contexte.drawString(textScore, 900, 80);

        // 1. Rendu du décor
        // 2. Rendu des sprites
        // 3. Rendu des textes
    }
    public void miseAJour() {
        this.carte.miseAJour();
        //this.pike1.miseAJour();
        //this.pike2.miseAJour();
        this.hook.miseAJour();
        for (Pike poisson : poissons) {
            poisson.miseAJour();
            if (collisionEntreHookEtPike(poisson)){
                switch(poisson.getFishType()){
                    case 0: this.score += 5; break;
                    case 1: this.score += 10; break;
                    case 2: this.score += 15; break;
                    case 3: this.score += 20; break;
                    case 4: this.score += 25; break;
                }
                poisson.lancer();
            }
            
        }
//        if (collisionEntreAvatarEtBanane1()) {
//            this.score += 10;
//            pike1.lancer();
//        }
//        if (collisionEntreAvatarEtBanane2()) {
//            this.score += 10;
//            pike2.lancer();
//        }
        // 1. Mise à jour de l’avatar en fonction des commandes des joueurs
        // 2. Mise à jour des autres éléments (objets, monstres, etc.)
        // 3. Gérer les interactions (collisions et autres règles)
    }
    
    
    public  Hook getHook() {
        return hook;   
    }
    
    public  int getScore() {
        return score;   
    }

    public boolean collisionEntreHookEtPike(Pike poisson) {
        if ((poisson.getX() >= hook.getX() + hook.getLargeur()) // trop à droite
                || (poisson.getX() + poisson.getLargeur() <= hook.getX()) // trop à gauche
                || (poisson.getY() >= hook.getY() + hook.getHauteur()) // trop en bas
                || (poisson.getY()+ poisson.getHauteur() <= hook.getY())) { // trop en haut
            return false;
        } else {
            return true;
        }
    }
    
//    public boolean collisionEntreAvatarEtBanane2() {
//        if ((pike2.getX() >= hook.getX() + hook.getLargeur()) // trop à droite
//                || (pike2.getX() + pike2.getLargeur() <= hook.getX()) // trop à gauche
//                || (pike2.getY() >= hook.getY() + hook.getHauteur()) // trop en bas
//                || (pike2.getY()+ pike2.getHauteur() <= hook.getY())) { // trop en haut
//            return false;
//        } else {
//            return true;
//        }
//    }
    
//    public boolean estTermine1() {
//         //Renvoie vrai si la partie est terminée (gagnée ou perdue)
//        return this.pike1.getY()>800;
//        //return false ;
//    }
//    
//    public boolean estTermine2() {
//         //Renvoie vrai si la partie est terminée (gagnée ou perdue)
//        return this.pike2.getY()>800;
//        //return false ;
//    }
    
}
