/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import moteur.FishingRod;

public class FishingRodSQL {
    
    //Ok ! L'idée c'est que dans cette classe, tu implémentes TOUTES les actions posible avec la Table Joueur (sur le serveur distant)
    //Pour faire ça, déjà tu as besoin de pouvoir te connecter à la base de donnée, c'est pourquoi c'est judicieux de les mettre en 
    //attributs les choses dont t'as besoin pour te connecter.
    private String adresseBase;
    private String user;
    private String motdepasse;
    
    
    //Ici, on fait un constructeur qui va juste initialiser l'intermédiaire SQL
    public FishingRodSQL(){
        this.adresseBase = "jdbc:mariadb://nemrod.ens2m.fr:3306/2024_2025_s1_vs2_tp2_fish_up";
        this.user = "etudiant";
        this.motdepasse = "YTDTvj9TR3CDYCmP";
    }
    
    //Je t'ai mis ici les 4 méthodes qui vont être importantes à coder, à toi de fustionner ça avec les bouts de code dans tes tests : 
    public void creerFishingRod(FishingRod R){
       //TODO (va utiliser CREATE dans sa requête SQL)
        try {

            Connection connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse);

            PreparedStatement requete = connexion.prepareStatement("INSERT INTO FishingRod VALUES (?, ?, ?, ?, ?, ?, ?)");
            requete.setString(1, R.getRodId());
            requete.setDouble(2, R.getcastDistance());
            requete.setDouble(3, R.getreelSpeed());
            requete.setBoolean(4, R.getisCasting());
            requete.setBoolean(5, R.getisReeling());
            requete.setDouble(6, R.getX());
            requete.setDouble(7, R.getY());
            System.out.println(requete);
            int nombreDAjouts = requete.executeUpdate();
            System.out.println(nombreDAjouts + " enregistrement(s) ajoute(s)");

            requete.close();
            connexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    
     public void modifierFishingRod(FishingRod R){
       //TODO (va utiliser UPDATE dans sa requête SQL)
        try {

            Connection connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse);

            PreparedStatement requete = connexion.prepareStatement("""
                                                                   UPDATE FishingRod 
                                                                   SET castDistance = ?, 
                                                                   reelSpeed = ?, 
                                                                   isCasting = ?, 
                                                                   isReeling = ?, 
                                                                   castPositionX = ?, 
                                                                   castPositionY = ?,  
                                                                   WHERE Rod_Id = ?""");
            
            // Lier les valeurs de l'objet FishingRod aux placeholders
            requete.setDouble(1, R.getcastDistance());
            requete.setDouble(2, R.getreelSpeed());
            requete.setBoolean(3, R.getisCasting());  
            requete.setBoolean(4, R.getisReeling()); 
            requete.setDouble(5, R.getX());
            requete.setDouble(6, R.getY()); 
            requete.setString(7, R.getRodId());
            
            System.out.println(requete);
            int nombreDeModifications = requete.executeUpdate();
            System.out.println(nombreDeModifications + " enregistrement(s) modifie(s)");

            requete.close();
            connexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     
     public void supprimerFishingRod(FishingRod R){
       //TODO (va utiliser DELETE dans sa requête SQL)
        try {

            Connection connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse);

            PreparedStatement requete = connexion.prepareStatement("DELETE FROM FishingRod WHERE Rod_Id = ?");
            requete.setString(1, R.getRodId());
            System.out.println(requete);
            int nombreDeSuppressions = requete.executeUpdate();
            System.out.println(nombreDeSuppressions + " enregistrement(s) supprime(s)");

            requete.close();
            connexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     
     public void voirFishingRod(FishingRod R){
       //TODO (va utiliser SELECT dans sa requête SQL)
       //Sur celui-ci je t'ai fait un exemple, tu vois ? on utilise les attributs pour la connection ! Plus propre non ? :)
        try {

            Connection connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse);

            PreparedStatement requete = connexion.prepareStatement("SELECT * FROM FishingRod WHERE Rod_Id = ?");
            requete.setString(1, R.getRodId());
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