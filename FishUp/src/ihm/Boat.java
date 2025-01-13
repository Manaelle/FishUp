/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ihm;

/**
 *
 * @author lgarciaa
 */
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author tpereira
 */
public class Boat {
    
    protected BufferedImage bateau1;
    protected BufferedImage bateau2;
    protected BufferedImage bateau3;
    protected BufferedImage bateau4;
    protected double x1, x2, x3, x4, y;

    public Boat() {
        try {
            this.bateau1 = ImageIO.read(getClass().getResource("../resources/bateau-peche1.png"));
            this.bateau2 = ImageIO.read(getClass().getResource("../resources/bateau-peche2.png"));
            this.bateau3 = ImageIO.read(getClass().getResource("../resources/bateau-peche3.png"));
            this.bateau4 = ImageIO.read(getClass().getResource("../resources/bateau-peche4.png"));
        } catch (IOException ex) {
            Logger.getLogger(Pike.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.x1 = 170;
        this.x2 = 400;
        this.x3 = 630;
        this.x4 = 860;
        this.y = -30;        
    }

    public void rendu(Graphics2D contexte) {
        contexte.drawImage(this.bateau1, (int) x1, (int) y, null);
        contexte.drawImage(this.bateau2, (int) x2, (int) y, null);
        contexte.drawImage(this.bateau3, (int) x3, (int) y, null);
        contexte.drawImage(this.bateau4, (int) x4, (int) y, null);
    }
    
}
