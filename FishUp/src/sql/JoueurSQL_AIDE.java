package sql;


import java.sql.*;
import outils.OutilsJDBC;
import moteur.Joueur;

public class JoueurSQL_AIDE {
    
    //Ok ! L'idée c'est que dans cette classe, tu implémentes TOUTES les actions posible avec la Table Joueur (sur le serveur distant)
    //Pour faire ça, déjà tu as besoin de pouvoir te connecter à la base de donnée, c'est pourquoi c'est judicieux de les mettre en 
    //attributs les choses dont t'as besoin pour te connecter.
    private String adresseBase;
    private String user;
    private String motdepasse;
    
    
    //Ici, on fait un constructeur qui va juste initialiser l'intermédiaire SQL
    public JoueurSQL_AIDE(){
        this.adresseBase = "jdbc:mariadb://nemrod.ens2m.fr:3306/2024_2025_s1_vs2_tp2_fish_up";
        this.user = "etudiant";
        this.motdepasse = "YTDTvj9TR3CDYCmP";
    }
    
    //Je t'ai mis ici les 4 méthodes qui vont être importantes à coder, à toi de fustionner ça avec les bouts de code dans tes tests : 
    public void creerJoueur(Joueur J){
       //TODO (va utiliser CREATE dans sa requête SQL)
    }
    
     public void modifierJoueur(Joueur J){
       //TODO (va utiliser UPDATE dans sa requête SQL)
    }
     
     public void supprimerJoueur(Joueur J){
       //TODO (va utiliser DELETE dans sa requête SQL)
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
    
   //Si tu as une autre table, tu dois créer une autre classe similaire à celle-ci ! A présent, ton collègue qui travaille sur le moteur pourra
   //facilement utiliser tes méthodes pour mettre à jour la BDD ! En utilisant les méthodes que tu as crée pour lui :)
    
    
    
}
