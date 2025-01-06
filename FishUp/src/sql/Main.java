package sql;

import moteur.Fish;
import moteur.Joueur;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        
        // test connexion à la database
        String url = "jdbc:mariadb://nemrod.ens2m.fr:3306/2024_2025_s1_vs2_tp2_fish_up"; // L'URL correcte pour MariaDB
        String user = "etudiant"; // nom utilisateur
        String password = "YTDTvj9TR3CDYCmP"; // mot de passe

        try {
            // Connexion à la base de données MariaDB
            Connection connexion = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion réussie à la base de données MariaDB.");

            // Exécuter des requêtes SQL...
            Statement statement = connexion.createStatement();
            
            // Fermer la connexion
            connexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        // créer un joueur
        // JoueurSQL joueurSQL = new JoueurSQL();
        // Joueur newJoueur = new Joueur("deka4", 5, 0, false);
        // joueurSQL.creerJoueur(newJoueur);
        
        
        
        // Crée une instance de ta classe FishSQL
        // FishSQL fishSQL = new FishSQL();
        // Exemple : Crée un nouvel objet Fish
        // Fish newFish = new Fish(0, 5, false);
        // Ajoute ce poisson à la base de données
        // fishSQL.creerFish(newFish);

        // Modifie les informations du poisson
        // newFish.setIsCaught(true);
        // fishSQL.modifierFish(newFish);

        // Affiche les informations du poisson
        // fishSQL.voirFish(newFish);

        // Supprime le poisson de la base de données
        // fishSQL.supprimerFish(newFish);
    }
