/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sql;

/**
 *
 * @author abriton
 */

import java.sql.*;
import outils.OutilsJDBC;
import moteur.Joueur;

public class JoueurSQL {
    
    //Ok ! L'idée c'est que dans cette classe, tu implémentes TOUTES les actions posible avec la Table Joueur (sur le serveur distant)
    //Pour faire ça, déjà tu as besoin de pouvoir te connecter à la base de donnée, c'est pourquoi c'est judicieux de les mettre en 
    //attributs les choses dont t'as besoin pour te connecter.
    private String adresseBase;
    private String user;
    private String motdepasse;
    
    
    //Ici, on fait un constructeur qui va juste initialiser l'intermédiaire SQL
    public JoueurSQL(){
        this.adresseBase = "jdbc:mariadb://nemrod.ens2m.fr:3306/2024_2025_s1_vs2_tp2_fish_up";
        this.user = "etudiant";
        this.motdepasse = "YTDTvj9TR3CDYCmP";
    }
    
    //Je t'ai mis ici les 4 méthodes qui vont être importantes à coder, à toi de fustionner ça avec les bouts de code dans tes tests : 
    public void creerJoueur(Joueur J){
       //TODO (va utiliser CREATE dans sa requête SQL)
        try {

            Connection connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse);

            PreparedStatement requete = connexion.prepareStatement("INSERT INTO Joueur VALUES (?, ?, ?, ?, ?)");
            requete.setString(1, "deka3");
            requete.setDouble(2, 21.26545);
            requete.setDouble(3, 47.25032);
            requete.setInt(4, 5);
            requete.setString(5, "merlu");
            System.out.println(requete);
            int nombreDAjouts = requete.executeUpdate();
            System.out.println(nombreDAjouts + " enregistrement(s) ajoute(s)");

            requete.close();
            connexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    
     public void modifierJoueur(Joueur J){
       //TODO (va utiliser UPDATE dans sa requête SQL)
        try {

            Connection connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse);

            PreparedStatement requete = connexion.prepareStatement("UPDATE Joueur SET position_x = 47.251032, position_y = 5.9935, poissons_attrapés = 'rascasse' WHERE nom = 'deka2'");
            requete.setDouble(1, 47.251032);
            requete.setDouble(2, 5.9935);
            requete.setString(3, "rascasse");
            requete.setString(4, "deka2");
            System.out.println(requete);
            int nombreDeModifications = requete.executeUpdate();
            System.out.println(nombreDeModifications + " enregistrement(s) modifie(s)");

            requete.close();
            connexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     
     public void supprimerJoueur(Joueur J){
       //TODO (va utiliser DELETE dans sa requête SQL)
        try {

            Connection connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse);

            PreparedStatement requete = connexion.prepareStatement("DELETE FROM Joueur WHERE nom = ?");
            requete.setString(1, "deka2gf");
            System.out.println(requete);
            int nombreDeSuppressions = requete.executeUpdate();
            System.out.println(nombreDeSuppressions + " enregistrement(s) supprime(s)");

            requete.close();
            connexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     
     public void voirJoueur(Joueur J){
       //TODO (va utiliser SELECT dans sa requête SQL)
       //Sur celui-ci je t'ai fait un exemple, tu vois ? on utilise les attributs pour la connection ! Plus propre non ? :)
        try {

            Connection connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse);

            PreparedStatement requete = connexion.prepareStatement("SELECT * FROM Joueur");
            System.out.println(requete);
            ResultSet resultat = requete.executeQuery();
            OutilsJDBC.afficherResultSet(resultat);

            requete.close();
            connexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
    
   //Si tu as une autre table, tu dois créer une autre classe similaire à celle-ci ! A présent, ton collègue qui travaille sur le moteur pourra
   //facilement utiliser tes méthodes pour mettre à jour la BDD ! En utilisant les méthodes que tu as crée pour lui :)