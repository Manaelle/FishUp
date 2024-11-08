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
public class TestInsert {

    public static void main(String[] args) {

        try {
            // Paramètres de la base de données
            String adresseBase = "jdbc:mariadb://nemrod.ens2m.fr:3306/2024_2025_s1_vs2_tp2_fish_up";
            String user = "etudiant";
            String motdepasse = "YTDTvj9TR3CDYCmP";
            
            Connection connexion = DriverManager.getConnection(adresseBase, user, motdepasse);

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

}