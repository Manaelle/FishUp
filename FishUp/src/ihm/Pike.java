package ihm;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import sql.*;

public class Pike {

    private static final String DB_URL = "jdbc:mariadb://nemrod.ens2m.fr:3306/2024_2025_s1_vs2_tp2_fish_up";
    private static final String DB_USER = "etudiant";
    private static final String DB_PASSWORD = "YTDTvj9TR3CDYCmP";

    private int id;
    private int fishType;
    private double x, y;
    private boolean sens;
    private BufferedImage sprite;

    private BufferedImage blueFishD, redFishD, yellowFishD, greenFishD;
    private BufferedImage blueFishG, redFishG, yellowFishG, greenFishG;

    // Constructeur avec ID uniquement
    public Pike(int id) {
        this.id = id;
        Random rand = new Random();
        this.fishType = rand.nextInt(4);
        this.sens = rand.nextBoolean();
        loadImages();
        setSprite();
        lancer();
    }

    // Constructeur complet
    public Pike(int id, double x, double y, boolean sens, int fishType) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.sens = sens;
        this.fishType = fishType;
        loadImages();
        setSprite();
    }

    // Chargement des images des poissons
    private void loadImages() {
        try {
            this.blueFishD = ImageIO.read(getClass().getResource("../resources/Poisson bleuD.png"));
            this.redFishD = ImageIO.read(getClass().getResource("../resources/Poisson rougeD.png"));
            this.greenFishD = ImageIO.read(getClass().getResource("../resources/Poisson vertD.png"));
            this.yellowFishD = ImageIO.read(getClass().getResource("../resources/Poisson jauneD.png"));
            this.blueFishG = ImageIO.read(getClass().getResource("../resources/Poisson bleuG.png"));
            this.redFishG = ImageIO.read(getClass().getResource("../resources/Poisson rougeG.png"));
            this.greenFishG = ImageIO.read(getClass().getResource("../resources/Poisson vertG.png"));
            this.yellowFishG = ImageIO.read(getClass().getResource("../resources/Poisson jauneG.png"));
        } catch (IOException ex) {
            Logger.getLogger(Pike.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Mise à jour de l'image en fonction du type et de la direction
    private void setSprite() {
        if (sens) {
            switch (fishType) {
                case 0 -> sprite = blueFishD;
                case 1 -> sprite = redFishD;
                case 2 -> sprite = greenFishD;
                case 3 -> sprite = yellowFishD;
            }
        } else {
            switch (fishType) {
                case 0 -> sprite = blueFishG;
                case 1 -> sprite = redFishG;
                case 2 -> sprite = greenFishG;
                case 3 -> sprite = yellowFishG;
            }
        }
    }

    // Mise à jour de la position
    public void miseAJour() {
        if (x >= 1088) {
            sens = false;
            setSprite();
        } else if (x <= 96) {
            sens = true;
            setSprite();
        }

        if (sens) {
            switch (fishType) {
                case 0 -> x += 5;
                case 1 -> x += 10;
                case 2 -> x += 13;
                case 3 -> x += 15;
            }
        } else {
            switch (fishType) {
                case 0 -> x -= 5;
                case 1 -> x -= 10;
                case 2 -> x -= 13;
                case 3 -> x -= 15;
            }
        }
        // Synchroniser avec la base de données
        PikeSQL pikeSQL = new PikeSQL();
        pikeSQL.modifierPikeXY(this);
        }
    

    // Insérer ou mettre à jour dans la base de données
    public void insertOrUpdateInDB() {
        String checkQuery = "SELECT COUNT(*) FROM fish WHERE id = ?";
        String insertQuery = "INSERT INTO fish (id, fishType, x, y, sens) VALUES (?, ?, ?, ?, ?)";
        String updateQuery = "UPDATE fish SET fishType = ?, x = ?, y = ?, sens = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {

            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                // Mise à jour si le poisson existe
                try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                    updateStmt.setInt(1, fishType);
                    updateStmt.setDouble(2, x);
                    updateStmt.setDouble(3, y);
                    updateStmt.setBoolean(4, sens);
                    updateStmt.setInt(5, id);
                    updateStmt.executeUpdate();
                }
            } else {
                // Insertion si le poisson n'existe pas
                try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                    insertStmt.setInt(1, id);
                    insertStmt.setInt(2, fishType);
                    insertStmt.setDouble(3, x);
                    insertStmt.setDouble(4, y);
                    insertStmt.setBoolean(5, sens);
                    insertStmt.executeUpdate();
                    System.out.println("Fish inserted into the database: ID =" + id);
                }
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Suppression de la base de données
    public void deleteFromDB() {
        String query = "DELETE FROM fish WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Affichage du poisson
    public void rendu(Graphics2D contexte) {
        contexte.drawImage(sprite, (int) x, (int) y, null);
    }

    // Réinitialiser la position du poisson
    public void lancer() {
        Random rand = new Random();
        this.y = 96 + rand.nextDouble() * 704;
        this.x = sens ? 96 : 1088;
    }

    // Getters pour la largeur et la hauteur
    public double getLargeur() {
        return sprite != null ? sprite.getWidth() : 0;
    }

    public double getHauteur() {
        return sprite != null ? sprite.getHeight() : 0;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getFishType() {
        return fishType;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
public void setX(double x) {
    this.x = x;
}

public void setY(double y) {
    this.y = y;
}


    public boolean getSens() {
        return sens;
    }
}
