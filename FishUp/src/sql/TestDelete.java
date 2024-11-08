/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sql;

import java.sql.*;

/**
 *
 * @author abriton
 */
public class TestDelete {

    public static void main(String[] args) {

        try {
            // Paramètres de la base de données
            String adresseBase = "jdbc:mariadb://nemrod.ens2m.fr:3306/2024_2025_s1_vs2_tp2_fish_up";
            String user = "etudiant";
            String motdepasse = "YTDTvj9TR3CDYCmP";
            
            Connection connexion = DriverManager.getConnection(adresseBase, user, motdepasse);

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

}
