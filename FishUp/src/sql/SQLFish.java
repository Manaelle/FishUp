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



public class SQLFish {
    public static void main(String[] args) {
        // Paramètres de la base de données
        String adresseBase = "jdbc:mariadb://nemrod.ens2m.fr:3306/2024_2025_s1_vs2_tp2_fish_up";
        String user = "etudiant";
        String motdepasse = "YTDTvj9TR3CDYCmP";

        // Test de la connexion à la base de données
        try (Connection connection = DriverManager.getConnection(adresseBase, user, motdepasse)) {
            System.out.println("Connexion réussie à la base de données.");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion : " + e.getMessage());
            e.printStackTrace();
        }
    }
}

