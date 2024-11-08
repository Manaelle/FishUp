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
public class TestUpdate {

    public static void main(String[] args) {

        try {
            // Paramètres de la base de données
            String adresseBase = "jdbc:mariadb://nemrod.ens2m.fr:3306/2024_2025_s1_vs2_tp2_fish_up";
            String user = "etudiant";
            String motdepasse = "YTDTvj9TR3CDYCmP";
            
            Connection connexion = DriverManager.getConnection(adresseBase, user, motdepasse);

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

}