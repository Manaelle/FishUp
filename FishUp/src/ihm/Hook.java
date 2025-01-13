package ihm;

import moteur.Entite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Hook extends Entite {

    protected BufferedImage sprite;
    private boolean toucheGauche;
    private boolean toucheDroite;
    private boolean toucheHaut;
    private boolean toucheBas;

    public Hook() {
        super(600, 400);
        try {
            this.sprite = ImageIO.read(getClass().getResource("../resources/hook_bleu.png"));
        } catch (IOException ex) {
            Logger.getLogger(Hook.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.toucheGauche = false;
        this.toucheDroite = false;
        this.toucheHaut = false;
        this.toucheBas = false;
    }

    public void miseAJour() {
        if (this.toucheGauche) {
            setAbscisse((int) (getAbscisse() - 8));
        }
        if (this.toucheDroite) {
            setAbscisse((int) (getAbscisse() + 8));
        }
        if (this.toucheHaut) {
            setOrdonnee((int) (getOrdonnee() - 8));
        }
        if (this.toucheBas) {
            setOrdonnee((int) (getOrdonnee() + 8));
        }
        if (getAbscisse() > 1184 - sprite.getWidth()) {
            setAbscisse(1184 - sprite.getWidth());
        }
        if (getAbscisse() < 96) {
            setAbscisse(96);
        }
        if (getOrdonnee() > 800 - sprite.getWidth()) {
            setOrdonnee(800 - sprite.getWidth());
        }
        if (getOrdonnee() < 96) {
            setOrdonnee(96);
        }
    }

    public void rendu(Graphics2D contexte) {
        contexte.drawImage(this.sprite, (int) getAbscisse(), (int) getOrdonnee(), null);
    }

    public void setGauche(boolean gauche) {
        this.toucheGauche = gauche;
    }

    public void setDroite(boolean droite) {
        this.toucheDroite = droite;
    }

    public void setHaut(boolean haut) {
        this.toucheHaut = haut;
    }

    public void setBas(boolean bas) {
        this.toucheBas = bas;
    }

    public double getLargeur() {
        return sprite.getHeight();
    }

    public double getHauteur() {
        return sprite.getWidth();
    }
}
