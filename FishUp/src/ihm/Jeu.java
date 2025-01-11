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
    private final HookSQL hookSQL;


    public Jeu(FenetreDeJeu fenetre) {
        this.fenetre = fenetre;
        this.score = 0;
        hooks = new ArrayList<>();
        this.boat = new Boat();
        this.carte = new Carte();
        poissons = new ArrayList<>();
        this.hookSQL = new HookSQL(); // Initialisation de l'attribut
        
        // HookSQL hookSQL = new HookSQL();

        int newID = 1; // ID initial
        int[] playerIDs = hookSQL.voirHookID();

        if (playerIDs.length > 0) {
            Arrays.sort(playerIDs);
            newID = playerIDs[playerIDs.length - 1] + 1;
        }

        this.hook = new Hook(newID); // Initialisation garantie ici
        this.hooks.add(this.hook);

        hookSQL.creerHook(this.hook); // Ajouter le Hook à la base de données
        
        if (newID == 1) {
            // Joueur maître : Créer les poissons et les insérer dans la base
            hookSQL.definirMaster(this.hook);
            PikeSQL pikeSQL = new PikeSQL();
            for (int i = 1; i <= 5; i++) {
                Pike poisson = new Pike(i); // Crée un nouveau Pike avec des données aléatoires
                poisson.lancer(); // Positionne aléatoirement le poisson
                pikeSQL.creerPike(poisson); // Insère le Pike dans la base de données
                poissons.add(poisson); // Ajoute le Pike à la liste des poissons
            }
        } else {
            // Joueurs non maîtres : Charger les poissons existants
            poissons.addAll(new PikeSQL().voirAllPike());
        }

    }

    public void rendu(Graphics2D contexte) {
        this.carte.rendu(contexte);
        this.boat.rendu(contexte);
        this.hook.rendu(contexte);
        
        HookSQL hookSQL = new HookSQL(); // Accès à la base de données
        List<Hook> hooksFromDB = hookSQL.getAllHooks(); // Charge tous les hooks de la BDD

        for (Hook hookFromDB : hooksFromDB) {
            // Affiche uniquement les hooks dont l'ID est différent de celui de this.hook
            if (hookFromDB.getHook_id() != this.hook.getHook_id()) {
                hookFromDB.rendu(contexte);
            }
        }   
        
        // Afficher les poissons de la base si le joueur n'est pas maître
        if (!hookSQL.isMaster(this.hook)) {
            poissons.clear(); // Vide la liste locale
            poissons.addAll(new PikeSQL().voirAllPike()); // Charge les poissons depuis la base
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

        // Mettre à jour les hooks contrôlés par d'autres joueurs
        for (Hook otherHook : hooks) {
            otherHook.miseAJour();
        }

        // Mettre à jour les poissons
        PikeSQL pikeSQL = new PikeSQL();
        List<Pike> poissonsRelances = new ArrayList<>();

        for (Pike poisson : new ArrayList<>(poissons)) { // Création d'une copie pour éviter les problèmes de modification concurrente
            poisson.miseAJour();

            if (collisionEntreHookEtPike(poisson)) {
                switch (poisson.getFishType()) {
                    case 0 -> score += 1;
                    case 1 -> score += 3;
                    case 2 -> score += 10;
                    case 3 -> score += 50;
                }

                pikeSQL.supprimerPike(poisson); // Supprime le poisson actuel de la base de données
                poissons.remove(poisson); // Supprime le poisson de la liste

                poisson.lancer(); // Relance le poisson
                pikeSQL.creerPike(poisson); // Met à jour le poisson dans la base de données
                poissonsRelances.add(poisson); // Ajoute à la liste des poissons relancés
            }
        }

        // Ajouter les poissons relancés à la liste principale
        poissons.addAll(poissonsRelances);

        // Synchroniser les poissons pour tous les joueurs
        // HookSQL hookSQL = new HookSQL();
        if (hookSQL.isMaster(this.hook)) { 
            // Le joueur maître met à jour tous les poissons dans la base
            for (Pike poisson : poissons) {
                poisson.insertOrUpdateInDB();
            }
        } else {
            // Les joueurs non maîtres synchronisent leur liste locale avec la base
            for (Pike poisson : poissonsRelances) {
                pikeSQL.modifierPike(poisson);
            }
            poissons.clear();
            poissons.addAll(new PikeSQL().voirAllPike());
        }
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
