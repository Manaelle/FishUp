package ihm;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Jeu {

    private int score;
    private final FenetreDeJeu fenetre;
    private final Hook hook;
    private final Boat boat;
    private final Carte carte;
    private final List<Pike> poissons;

    public Jeu(FenetreDeJeu fenetre) {
        this.fenetre = fenetre;
        this.score = 0;
        this.hook = new Hook();
        this.boat = new Boat();
        this.carte = new Carte();
        poissons = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Pike poisson = new Pike(i);
            poisson.insertOrUpdateInDB();
            poissons.add(poisson);
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
        contexte.setFont(new Font("Arial", Font.BOLD, 24));
        contexte.setColor(Color.WHITE);
        contexte.drawString("Temps restant : " + (tempsRestant / 1000) + " s", 900, 50);
        contexte.setFont(contexte.getFont().deriveFont(18f));
        contexte.drawString("Score Actuel : " + score, 900, 80);
    }

public void miseAJour() {
    this.carte.miseAJour();
    this.hook.miseAJour();

    Iterator<Pike> it = poissons.iterator();
    List<Pike> poissonsRelances = new ArrayList<>();

    while (it.hasNext()) {
        Pike poisson = it.next();
        poisson.miseAJour();

        if (collisionEntreHookEtPike(poisson)) {
            switch (poisson.getFishType()) {
                case 0 -> score += 5;
                case 1 -> score += 10;
                case 2 -> score += 15;
                case 3 -> score += 20;
            }

            // Supprimer le poisson de la base et de la liste
            poisson.deleteFromDB();
            it.remove();

            // Relancer le poisson et ajouter à une liste temporaire
            poisson.lancer();
            poisson.insertOrUpdateInDB();
            poissonsRelances.add(poisson);
        }
    }

    // Ajouter les poissons relancés après la boucle
    poissons.addAll(poissonsRelances);
}



    public Hook getHook() {
        return hook;
    }

    public int getScore() {
        return score;
    }

    public boolean collisionEntreHookEtPike(Pike poisson) {
        return !(poisson.getX() >= hook.getX() + hook.getLargeur()
                || poisson.getX() + poisson.getLargeur() <= hook.getX()
                || poisson.getY() >= hook.getY() + hook.getHauteur()
                || poisson.getY() + poisson.getHauteur() <= hook.getY());
    }
}
