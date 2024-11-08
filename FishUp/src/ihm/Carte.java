
package ihm;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class Carte {

    private int largeur = 15;
    private int hauteur = 24;
    private int tailleTuile = 32;
    private BufferedImage[] tuiles;
    
    private BufferedImage uneTuile;
    
    private int [][] decor = {
        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } ,
        { 4, 4, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 4, 4} ,
        { 3, 3, 77, 77, 77, 77, 77, 77, 77,77, 77, 77, 77, 77, 3, 3} ,
        { 3, 3, 77, 77, 77, 77, 77, 77, 77,77, 77, 77, 77, 77, 3, 33} ,
        { 3, 3, 77, 77, 77, 77, 77, 77, 77,77, 77, 77, 77, 77, 3, 3} ,
        { 3, 3, 77, 77, 77, 77, 77, 77, 77,77, 77, 77, 77, 77, 3, 3} ,
        { 3, 3, 77, 77, 77, 77, 77, 77, 77,77, 77, 77, 77, 77, 3, 3} ,
        { 3, 3, 77, 77, 77, 77, 77, 77, 77,77, 77, 77, 77, 77, 3, 3} ,
        { 3, 3, 77, 77, 77, 77, 77, 77, 77,77, 77, 77, 77, 77, 3, 3},
        { 3, 3, 77, 77, 77, 77, 77, 77, 77,77, 77, 77, 77, 77, 3, 3} ,
        { 3, 3, 77, 77, 77, 77, 77, 77, 77,77, 77, 77, 77, 77, 3, 3} ,
        { 3, 3, 77, 77, 77, 77, 77, 77, 77,77, 77, 77, 77, 77, 3, 3} ,
        { 3, 3, 77, 77, 77, 77, 77, 77, 77,77, 77, 77, 77, 77, 3, 3} ,
        { 3, 3, 77, 77, 77, 77, 77, 77, 77,77, 77, 77, 77, 77, 3, 3} ,
        { 3, 3, 77, 77, 77, 77, 77, 77, 77,77, 77, 77, 77, 77, 3, 3} ,
    };

    public Carte() {
        tuiles = new BufferedImage[176];
        try {
            BufferedImage tileset = ImageIO.read(getClass().getResource("/resources/tileSetMinecraft32x32.png"));
            uneTuile = tileset.getSubimage(64, 32, tailleTuile, tailleTuile);
            for (int i = 0; i < tuiles . length ; i ++) {
                int x = (i % 16) * tailleTuile;
                int y = (i / 16) * tailleTuile;
                tuiles[i] = tileset.getSubimage(x, y, tailleTuile, tailleTuile);
            }
        } catch (IOException ex) {
            Logger.getLogger(Carte.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

    public void miseAJour() {

    }

    public void rendu(Graphics2D contexte) {
        for (int i = 0; i < 24; i += 1){
            for (int j = 0; j < 15; j += 1){
                int id = decor[j][i];
                contexte.drawImage(tuiles[id], j*32, i*32, null);
            }   
        }    
    }

}
