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
import java.util.ArrayList;
import outils.OutilsJDBC;
import ihm.Pike;
import java.util.List;


public class PikeSQL {
    
    //Ok ! L'idée c'est que dans cette classe, tu implémentes TOUTES les actions posible avec la Table Joueur (sur le serveur distant)
    //Pour faire ça, déjà tu as besoin de pouvoir te connecter à la base de donnée, c'est pourquoi c'est judicieux de les mettre en 
    //attributs les choses dont t'as besoin pour te connecter.
    private String adresseBase;
    private String user;
    private String motdepasse;
    
    
    //Ici, on fait un constructeur qui va juste initialiser l'intermédiaire SQL
    public PikeSQL(){
        this.adresseBase = "jdbc:mariadb://nemrod.ens2m.fr:3306/2024_2025_s1_vs2_tp2_fish_up";
        this.user = "etudiant";
        this.motdepasse = "YTDTvj9TR3CDYCmP";
    }
    
    //Je t'ai mis ici les 4 méthodes qui vont être importantes à coder, à toi de fustionner ça avec les bouts de code dans tes tests : 
    public void creerPike(Pike P){
       //TODO (va utiliser CREATE dans sa requête SQL)
        try {

            Connection connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse);

            PreparedStatement requete = connexion.prepareStatement("INSERT INTO fish (Id) VALUES (?)");
            requete.setInt(1, P.getId());
            System.out.println(requete);
            int nombreDAjouts = requete.executeUpdate();
            System.out.println(nombreDAjouts + " enregistrement(s) ajoute(s)");
            requete.close();
            connexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    
     public void modifierPikeXY(Pike P){
       //TODO (va utiliser UPDATE dans sa requête SQL)
        try {

            Connection connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse);

            PreparedStatement requete = connexion.prepareStatement("""
                                                                   UPDATE fish 
                                                                   SET x = ?,
                                                                   y = ?,
                                                                   WHERE Id = ?""");
            requete.setDouble(1, P.getX());
            requete.setDouble(2, P.getY());
            requete.setInt(3, P.getId());
            System.out.println(requete);
            int nombreDeModifications = requete.executeUpdate();
            System.out.println(nombreDeModifications + " enregistrement(s) modifie(s)");

            requete.close();
            connexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     
     public void supprimerPike(Pike P){
       //TODO (va utiliser DELETE dans sa requête SQL)
        try {

            Connection connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse);

            PreparedStatement requete = connexion.prepareStatement("DELETE FROM fish WHERE Id = ?");
            requete.setInt(1, P.getId());
            System.out.println(requete);
            int nombreDeSuppressions = requete.executeUpdate();
            System.out.println(nombreDeSuppressions + " enregistrement(s) supprime(s)");

            requete.close();
            connexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     
     public void voirPike(Pike P){
       //TODO (va utiliser SELECT dans sa requête SQL)
       //Sur celui-ci je t'ai fait un exemple, tu vois ? on utilise les attributs pour la connection ! Plus propre non ? :)
        try {

            Connection connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse);

            PreparedStatement requete = connexion.prepareStatement("SELECT * FROM fish WHERE Id = ?");
            requete.setInt(1, P.getId());
            System.out.println(requete);
            ResultSet resultat = requete.executeQuery();
            OutilsJDBC.afficherResultSet(resultat);

            requete.close();
            connexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
     
        public List<Pike> voirAllPike() {
            List<Pike> pikes = new ArrayList<>();

            try (Connection connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse);
                PreparedStatement requete = connexion.prepareStatement("SELECT * FROM fish");
                ResultSet resultat = requete.executeQuery()) {

                while (resultat.next()) {
                    // Supposons que la classe Pike a un constructeur prenant les colonnes de fish comme paramètres
                    int id = resultat.getInt("ID");
                    Pike poisson = new Pike(id);
                    pikes.add(poisson);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            return pikes;
        }
     
     
     
    public double[] voirPikeXY(int pikeID) {
        double[] coordinates = new double[2]; // Tableau pour stocker x et y
        try {
            Connection connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse);
            PreparedStatement requete = connexion.prepareStatement("SELECT x, y FROM fish WHERE Id = ?");
            requete.setInt(1, pikeID);
            ResultSet resultat = requete.executeQuery();
        
            if (resultat.next()) { // Si un résultat est trouvé
                coordinates[0] = resultat.getDouble("x");
                coordinates[1] = resultat.getDouble("y");
            }
        
            requete.close();
            connexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return coordinates; // Retourne les coordonnées
    }
    
    /*
    
    public int[] voirHookID() {
        ArrayList<Integer> listeID = new ArrayList<>(); // Utilisation d'une liste dynamique

        try (Connection connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse);
             PreparedStatement requete = connexion.prepareStatement("SELECT PlayerID FROM hook");
             ResultSet resultat = requete.executeQuery()) {

            while (resultat.next()) { // Parcourt tous les résultats
                listeID.add(resultat.getInt("PlayerID"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Convertit la liste dynamique en tableau d'entiers
        return listeID.stream().mapToInt(Integer::intValue).toArray();
    }
*/
}
    
   //Si tu as une autre table, tu dois créer une autre classe similaire à celle-ci ! A présent, ton collègue qui travaille sur le moteur pourra
   //facilement utiliser tes méthodes pour mettre à jour la BDD ! En utilisant les méthodes que tu as crée pour lui :)