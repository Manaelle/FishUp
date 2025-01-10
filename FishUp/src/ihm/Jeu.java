package ihm;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;

import sql.*;

public class Jeu {

    private int score;
    private final FenetreDeJeu fenetre;
    private final List<Hook> hooks;
    private final Boat boat;
    private final Carte carte;
    private final List<Pike> poissons;
    private final Hook hook;

    public Jeu(FenetreDeJeu fenetre) {
        this.fenetre = fenetre;
        this.score = 0;
        hooks = new ArrayList<>();
        this.boat = new Boat();
        this.carte = new Carte();
        poissons = new ArrayList<>();
        
        HookSQL hookSQL = new HookSQL();

        int newID = 1; // ID initial
        int[] playerIDs = hookSQL.voirHookID();

        if (playerIDs.length > 0) {
            Arrays.sort(playerIDs);
            newID = playerIDs[playerIDs.length - 1] + 1;
        }

        this.hook = new Hook(newID); // Initialisation garantie ici
        this.hooks.add(this.hook);

        hookSQL.creerHook(this.hook); // Ajouter le Hook à la base de données
        
        if (newID==1){
            for (int i = 1; i <= 10; i++) {
                Pike poisson = new Pike(i);
                poisson.insertOrUpdateInDB();
                poissons.add(poisson);
            }
        }
        else {
            poissons.addAll(new PikeSQL().voirAllPike());
        }
    }

    public void rendu(Graphics2D contexte) {
        this.carte.rendu(contexte);
        this.boat.rendu(contexte);
        this.hook.rendu(contexte);
        
        for (Hook hook : hooks) {
            hook.rendu(contexte);
        }

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
    // Iterator<Hook> it2 =hooks.iterator();
    Iterator<Pike> it = poissons.iterator();
    List<Pike> poissonsRelances = new ArrayList<>();

    while (it.hasNext()) { //&&it2.hasNext()
        //Hook hook = it2.next();
        Pike poisson = it.next();
        // hook.miseAJour();
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
