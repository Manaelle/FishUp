/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ihm;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Font;


/**
 *
 * @author tpereira
 */
public class Jeu {
    private int score;
    private FenetreDeJeu fenetre;
    private Hook hook;
    private Boat boat;
    private Carte carte;
    private List<Pike> poissons;
    
    public Jeu(FenetreDeJeu fenetre) {
        this.fenetre = fenetre;
        this.score = 0;
        this.hook = new Hook();
        this.boat = new Boat();
        this.carte = new Carte();
        poissons = new ArrayList<>();
        
        for (int i = 0; i < 10; i++) {
            poissons.add(new Pike());
        }
    }
    
    public void rendu(Graphics2D contexte) {
        this.carte.rendu(contexte);
        this.hook.rendu(contexte);
        this.boat.rendu(contexte);
        for (Pike poisson : poissons) {
            poisson.rendu(contexte);
        }

        int tempsRestant = fenetre.getTempsRestant();

        contexte.setFont(new Font("Arial", Font.BOLD, 20));
        contexte.setColor(Color.WHITE);

        String textTemps = "Temps restant : " + (tempsRestant / 1000) + " s";
        contexte.drawString(textTemps, 1050, 20);
        contexte.setFont(contexte.getFont().deriveFont(18f));

        String textScore = "Score Actuel : " + (score);
        contexte.drawString(textScore, 1050, 50);
    }
    public void miseAJour() {
        this.carte.miseAJour();
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
    }
    
    
    public  Hook getHook() {
        return hook;   
    }
    
    public  int getScore() {
        return score;   
    }

    public boolean collisionEntreHookEtPike(Pike poisson) {
        if ((poisson.getAbscisse() >= hook.getAbscisse() + hook.getLargeur()) // trop à droite
                || (poisson.getAbscisse() + poisson.getLargeur() <= hook.getAbscisse()) // trop à gauche
                || (poisson.getOrdonnee() >= hook.getOrdonnee() + hook.getHauteur()) // trop en bas
                || (poisson.getOrdonnee()+ poisson.getHauteur() <= hook.getOrdonnee())) { // trop en haut
            return false;
        } else {
            return true;
        }
    }    
}
