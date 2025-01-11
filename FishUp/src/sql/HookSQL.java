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
import ihm.Hook;
import java.util.List;

public class HookSQL {
    
    //Ok ! L'idée c'est que dans cette classe, tu implémentes TOUTES les actions posible avec la Table Joueur (sur le serveur distant)
    //Pour faire ça, déjà tu as besoin de pouvoir te connecter à la base de donnée, c'est pourquoi c'est judicieux de les mettre en 
    //attributs les choses dont t'as besoin pour te connecter.
    private String adresseBase;
    private String user;
    private String motdepasse;
    
    
    //Ici, on fait un constructeur qui va juste initialiser l'intermédiaire SQL
    public HookSQL(){
        this.adresseBase = "jdbc:mariadb://nemrod.ens2m.fr:3306/2024_2025_s1_vs2_tp2_fish_up";
        this.user = "etudiant";
        this.motdepasse = "YTDTvj9TR3CDYCmP";
    }
    
    //Je t'ai mis ici les 4 méthodes qui vont être importantes à coder, à toi de fustionner ça avec les bouts de code dans tes tests : 
    public void creerHook(Hook H){
       //TODO (va utiliser CREATE dans sa requête SQL)
        try {

            Connection connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse);

            PreparedStatement requete = connexion.prepareStatement("INSERT INTO hook (PlayerID) VALUES (?)");
            requete.setInt(1, H.getHook_id());
            // System.out.println(requete);
            int nombreDAjouts = requete.executeUpdate();
            // System.out.println(nombreDAjouts + " enregistrement(s) ajoute(s)");
            requete.close();
            connexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    
     public void modifierHookXY(Hook H){
       //TODO (va utiliser UPDATE dans sa requête SQL)
        try {

            Connection connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse);

            PreparedStatement requete = connexion.prepareStatement("""
                                                                   UPDATE hook 
                                                                   SET x = ?,
                                                                   y = ?
                                                                   WHERE PlayerID = ?""");
            requete.setDouble(1, H.getX());
            requete.setDouble(2, H.getY());
            requete.setInt(3, H.getHook_id());
            // System.out.println(requete);
            int nombreDeModifications = requete.executeUpdate();
            // System.out.println(nombreDeModifications + " enregistrement(s) modifie(s)");

            requete.close();
            connexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     
     public void supprimerHook(Hook H){
       //TODO (va utiliser DELETE dans sa requête SQL)
        try {

            Connection connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse);

            PreparedStatement requete = connexion.prepareStatement("DELETE FROM hook WHERE PlayerId = ?");
            requete.setInt(1, H.getHook_id());
            // System.out.println(requete);
            int nombreDeSuppressions = requete.executeUpdate();
            // System.out.println(nombreDeSuppressions + " enregistrement(s) supprime(s)");

            requete.close();
            connexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     
     public void voirHook(Hook H){
       //TODO (va utiliser SELECT dans sa requête SQL)
       //Sur celui-ci je t'ai fait un exemple, tu vois ? on utilise les attributs pour la connection ! Plus propre non ? :)
        try {

            Connection connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse);

            PreparedStatement requete = connexion.prepareStatement("SELECT * FROM hook WHERE PlayerId = ?");
            requete.setInt(1, H.getHook_id());
            // System.out.println(requete);
            ResultSet resultat = requete.executeQuery();
            OutilsJDBC.afficherResultSet(resultat);

            requete.close();
            connexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
     
     
     
    public double[] voirHookXY(int hookID) {
        double[] coordinates = new double[2]; // Tableau pour stocker x et y
        try {
            Connection connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse);
            PreparedStatement requete = connexion.prepareStatement("SELECT x, y FROM hook WHERE PlayerId = ?");
            requete.setInt(1, hookID);
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
    
    public int[] voirHookID() {
        ArrayList<Integer> listeID = new ArrayList<>(); // Utilisation d'une liste dynamique

        try (Connection connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse);
             PreparedStatement requete = connexion.prepareStatement("SELECT PlayerId FROM hook");
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
    
    public void definirMaster(Hook H) {
        try {
            Connection connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse);

            PreparedStatement requete = connexion.prepareStatement("""
                                                                    UPDATE hook 
                                                                    SET isMaster = 1
                                                                    WHERE PlayerID = ?""");
            requete.setInt(1, H.getHook_id());
        
            // System.out.println(requete);
            int nombreDeModifications = requete.executeUpdate();
            // System.out.println(nombreDeModifications + " enregistrement(s) modifié(s)");

            requete.close();
            connexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public List<Hook> getAllHooks() {
        List<Hook> hooks = new ArrayList<>();
        try {
            Connection connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse);

            PreparedStatement requete = connexion.prepareStatement("SELECT PlayerID, x, y FROM hook");
            ResultSet resultat = requete.executeQuery();

            while (resultat.next()) {
                int playerID = resultat.getInt("PlayerID");
                double x = resultat.getDouble("x");
                double y = resultat.getDouble("y");
                Hook hook = new Hook(playerID); // Crée un hook avec l'ID
                hook.setX(x); // Définit la position X
                hook.setY(y); // Définit la position Y
                hooks.add(hook);
            }

            requete.close();
            connexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return hooks;
    }
    
    public boolean isMaster(Hook hook) {
        String requete = "SELECT isMaster FROM hook WHERE PlayerID = ?";
        try (Connection connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse);
             PreparedStatement statement = connexion.prepareStatement(requete)) {

            statement.setInt(1, hook.getHook_id());
            try (ResultSet resultat = statement.executeQuery()) {
                if (resultat.next()) {
                    return resultat.getBoolean("isMaster");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public void supprimerTousLesHooks() {
        String deleteQuery = "DELETE FROM hook";

        try (Connection connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse);
             PreparedStatement stmt = connexion.prepareStatement(deleteQuery)) {

            int rowsDeleted = stmt.executeUpdate();
            System.out.println(rowsDeleted + " hooks supprimés.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
    
   //Si tu as une autre table, tu dois créer une autre classe similaire à celle-ci ! A présent, ton collègue qui travaille sur le moteur pourra
   //facilement utiliser tes méthodes pour mettre à jour la BDD ! En utilisant les méthodes que tu as crée pour lui :)