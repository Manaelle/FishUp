package ihm;

import moteur.Entite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Pike extends Entite {

    private BufferedImage sprite;
    private BufferedImage noirFishD, orangeFishD, rougeFishD, roseFishD, vertFishD;
    private BufferedImage noirFishG, orangeFishG, rougeFishG, roseFishG, vertFishG;
    private boolean sens; // Direction du mouvement: true = droite, false = gauche
    private int fishType;

    public Pike() {
        super(0, 0); // Initialise les coordonnées à (0, 0) via Entite
        Random rand = new Random();
        try {
            // Chargement des images
            this.noirFishD = ImageIO.read(getClass().getResource("../resources/poisson_noirD.png"));
            this.orangeFishD = ImageIO.read(getClass().getResource("../resources/poisson_orangeD.png"));
            this.roseFishD = ImageIO.read(getClass().getResource("../resources/poisson_roseD.png"));
            this.rougeFishD = ImageIO.read(getClass().getResource("../resources/poisson_rougeD.png"));
            this.vertFishD = ImageIO.read(getClass().getResource("../resources/poisson_vertD.png"));
            this.noirFishG = ImageIO.read(getClass().getResource("../resources/poisson_noirG.png"));
            this.orangeFishG = ImageIO.read(getClass().getResource("../resources/poisson_orangeG.png"));
            this.roseFishG = ImageIO.read(getClass().getResource("../resources/poisson_roseG.png"));
            this.rougeFishG = ImageIO.read(getClass().getResource("../resources/poisson_rougeG.png"));
            this.vertFishG = ImageIO.read(getClass().getResource("../resources/poisson_vertG.png"));

            // Initialisation des propriétés aléatoires
            this.fishType = rand.nextInt(5);
            this.sens = rand.nextBoolean();
            sensPoisson();
            lancer(); // Position initiale
        } catch (IOException ex) {
            Logger.getLogger(Pike.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sensPoisson() {
        if (sens) { // Poisson vers la droite
            switch (this.fishType) {
                case 0 -> this.sprite = noirFishD;
                case 1 -> this.sprite = orangeFishD;
                case 2 -> this.sprite = roseFishD;
                case 3 -> this.sprite = rougeFishD;
                case 4 -> this.sprite = vertFishD;
            }
        } else { // Poisson vers la gauche
            switch (this.fishType) {
                case 0 -> this.sprite = noirFishG;
                case 1 -> this.sprite = orangeFishG;
                case 2 -> this.sprite = roseFishG;
                case 3 -> this.sprite = rougeFishG;
                case 4 -> this.sprite = vertFishG;
            }
        }
    }

    public void miseAJour() {
        if (getAbscisse() >= 1088) { // Bords droits
            this.sens = false;
            sensPoisson();
        } else if (getAbscisse() <= 96) { // Bords gauches
            this.sens = true;
            sensPoisson();
        }

        int vitesse = switch (this.fishType) {
            case 0 -> 5;
            case 1 -> 10;
            case 2 -> 13;
            case 3 -> 15;
            case 4 -> 20;
            default -> 5;
        };

        if (this.sens) {
            Move(vitesse, 0); // Déplacement vers la droite
        } else {
            Move(-vitesse, 0); // Déplacement vers la gauche
        }
    }

    public void rendu(Graphics2D contexte) {
        contexte.drawImage(this.sprite, (int) getAbscisse(), (int) getOrdonnee(), null);
    }

    public void lancer() {
        Random rand = new Random();
        setOrdonnee(96 + rand.nextInt(704 - this.sprite.getWidth())); // Position aléatoire en hauteur
        if (this.sens) {
            setAbscisse(96); // Position initiale à gauche
        } else {
            setAbscisse(1088); // Position initiale à droite
        }
    }

    public int getFishType() {
        return fishType;
    }

    public double getLargeur() {
        return sprite.getWidth();
    }

    public double getHauteur() {
        return sprite.getHeight();
    }
}
