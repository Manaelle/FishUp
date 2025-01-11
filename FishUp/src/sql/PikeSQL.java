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
    public void creerPike(Pike pike) {
        String insertQuery = "INSERT INTO fish (Id, FishType, x, y, Sens) VALUES (?, ?, ?, ?, ?)";

        try (Connection connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse);
             PreparedStatement stmt = connexion.prepareStatement(insertQuery)) {

            stmt.setInt(1, pike.getId());
            stmt.setInt(2, pike.getFishType()); // Inclure fishType aléatoire
            stmt.setDouble(3, pike.getX());
            stmt.setDouble(4, pike.getY());
            stmt.setBoolean(5, pike.getSens());

            stmt.executeUpdate();
            // System.out.println("Pike créé : ID=" + pike.getId() + ", Type=" + pike.getFishType());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public void modifierPike(Pike pike) {
        String updateQuery = "UPDATE fish SET x = ?, y = ?, Sens = ?, FishType = ? WHERE Id = ?";

        try (Connection connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse);
             PreparedStatement stmt = connexion.prepareStatement(updateQuery)) {

            stmt.setDouble(1, pike.getX());
            stmt.setDouble(2, pike.getY());
            stmt.setBoolean(3, pike.getSens());
            stmt.setInt(4, pike.getFishType());
            stmt.setInt(5, pike.getId());

            stmt.executeUpdate();
            // System.out.println("Pike modifié : ID=" + pike.getId() + ", Type=" + pike.getFishType());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    
     public void modifierPikeXY(Pike P){
       //TODO (va utiliser UPDATE dans sa requête SQL)
        try {

            Connection connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse);

            PreparedStatement requete = connexion.prepareStatement("""
                                                                   UPDATE fish 
                                                                   SET x = ?,
                                                                   y = ?
                                                                   WHERE Id = ?""");
            requete.setDouble(1, P.getX());
            requete.setDouble(2, P.getY());
            requete.setInt(3, P.getId());
            // System.out.println(requete);
            int nombreDeModifications = requete.executeUpdate();
            // System.out.println(nombreDeModifications + " enregistrement(s) modifie(s)");

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
            // System.out.println(requete);
            int nombreDeSuppressions = requete.executeUpdate();
            // System.out.println(nombreDeSuppressions + " enregistrement(s) supprime(s)");

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
            // System.out.println(requete);
            ResultSet resultat = requete.executeQuery();
            OutilsJDBC.afficherResultSet(resultat);

            requete.close();
            connexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
     /*
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
        }*/
     
     public List<Pike> voirAllPike() {
        List<Pike> pikes = new ArrayList<>();

        try (Connection connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse);
             PreparedStatement requete = connexion.prepareStatement("SELECT id, x, y, sens, fishType FROM fish");
             ResultSet resultat = requete.executeQuery()) {

            while (resultat.next()) {
                int id = resultat.getInt("id");
                double x = resultat.getDouble("x");
                double y = resultat.getDouble("y");
                boolean sens = resultat.getBoolean("sens");
                int fishType = resultat.getInt("fishType");

                // Utilisation du constructeur complet de Pike
                Pike poisson = new Pike(id, x, y, sens, fishType);
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
    
    public void marquerPikeCommeInactif(Pike poisson) {
        try (Connection connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse)) {
            PreparedStatement requete = connexion.prepareStatement("""
                UPDATE fish 
                SET isVisible = false 
                WHERE Id = ?""");
            requete.setInt(1, poisson.getId());
            requete.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Pike> voirPikesVisibles() {
        List<Pike> poissonsVisibles = new ArrayList<>();
        try (Connection connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse)) {
            PreparedStatement requete = connexion.prepareStatement("""
                SELECT * FROM fish 
                WHERE isVisible = true""");
            ResultSet resultSet = requete.executeQuery();
            while (resultSet.next()) {
                Pike poisson = new Pike(resultSet.getInt("Id"));
                poisson.setX(resultSet.getDouble("x"));
                poisson.setY(resultSet.getDouble("y"));
                poissonsVisibles.add(poisson);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return poissonsVisibles;
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