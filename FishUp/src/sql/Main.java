package sql;

import moteur.Fish;
import moteur.Joueur;
import moteur.FishingRod;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import ihm.Hook;

public class Main {
    public static void main(String[] args) {

        // créer un joueur
        // JoueurSQL joueurSQL = new JoueurSQL();
        // Joueur newJoueur = new Joueur("deka4", 5, 0, false);
        // joueurSQL.creerJoueur(newJoueur);
        
        
        
        // Crée une instance de ta classe FishSQL
        HookSQL hookSQL = new HookSQL();
        // Exemple : Crée un nouvel objet Fish
        Hook newHook = new Hook(5);
        // Ajoute ce poisson à la base de données
        hookSQL.creerHook(newHook);

        // Modifie les informations du poisson
        // newFish.setIsCaught(true);
        // fishSQL.modifierFish(newFish);

        // Affiche les informations du poisson
        // fishSQL.voirFish(newFish);

        // Supprime le poisson de la base de données
        // fishSQL.supprimerFish(newFish);
    }
}
