package ihm;

import java.awt.Graphics2D;
import moteur.JeuMoteur;


public class JeuInterface {

    private Carte carte;
    private JeuMoteur jeuMoteur;
     
    public JeuInterface() {        
        this.carte = new Carte();
    }

    public void miseAJour() {
        this.carte.miseAJour();
    }

    public void rendu(Graphics2D contexte) {
        this.carte.rendu(contexte);
    }

}
