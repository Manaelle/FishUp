/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Exemple de classe carte
 *
 * @author guillaume.laurent
 */
public class Carte {

    private int largeur = 24;
    private int hauteur = 15;
    private int tailleTuile = 32;
    private BufferedImage[] tuiles;
    
    private BufferedImage uneTuile;
    
    private int [][] decor = {
        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } ,
        { 2, 2, 2, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 2, 2, 2} ,
        { 3, 3, 3, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 3, 3, 3} ,
        { 3, 3, 3, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 3, 3, 3} ,
        { 3, 3, 3, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 3, 3, 3} ,
        { 3, 3, 3, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 3, 3, 3} ,
        { 3, 3, 3, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 3, 3, 3} ,
        { 3, 3, 3, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 3, 3, 3} ,
        { 3, 3, 3, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 3, 3, 3},
        { 3, 3, 3, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 3, 3, 3} ,
        { 3, 3, 3, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 3, 3, 3} ,
        { 3, 3, 3, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 3, 3, 3} ,
        { 3, 3, 3, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 3, 3, 3} ,
        { 3, 3, 3, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 3, 3, 3} ,
        { 3, 3, 3, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 3, 3, 3} ,
        
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

    
    //public Carte(){
    
    public void miseAJour() {

    }

    public void rendu(Graphics2D contexte) {
        for (int i = 0; i < 15; i += 1){
            for (int j = 0; j < 24; j += 1){
                int id = decor[i][j];
                contexte.drawImage(tuiles[id], j*32, i*32, null);
            }   
        }    
    }

}
