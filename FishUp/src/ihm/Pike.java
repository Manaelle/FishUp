/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ihm;

/**
 *
 * @author tpereira
 */

import ihm.Hook;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.util.Random;

/**
 * Exemple de classe lutin
 *
 * @author guillaume.laurent
 */
public class Pike {

    protected BufferedImage sprite;
    protected BufferedImage blueFishD;
    protected BufferedImage redFishD;
    protected BufferedImage yellowFishD;
    protected BufferedImage greenFishD;
    protected BufferedImage blueFishG;
    protected BufferedImage redFishG;
    protected BufferedImage yellowFishG;
    protected BufferedImage greenFishG;
    protected double x, y;
    protected boolean sens;

    public Pike() {
        try {
            //this.sprite = ImageIO.read(getClass().getResource("../resources/Poisson bleuD.png"));
            this.blueFishD = ImageIO.read(getClass().getResource("../resources/Poisson bleuD.png"));
            this.redFishD = ImageIO.read(getClass().getResource("../resources/Poisson rougeD.png"));
            this.greenFishD = ImageIO.read(getClass().getResource("../resources/Poisson vertD.png"));
            this.yellowFishD = ImageIO.read(getClass().getResource("../resources/Poisson jauneD.png"));
            this.blueFishG = ImageIO.read(getClass().getResource("../resources/Poisson bleuG.png"));
            this.redFishG = ImageIO.read(getClass().getResource("../resources/Poisson rougeG.png"));
            this.greenFishG = ImageIO.read(getClass().getResource("../resources/Poisson vertG.png"));
            this.yellowFishG = ImageIO.read(getClass().getResource("../resources/Poisson jauneG.png"));
            this.sens = true;
        } catch (IOException ex) {
            Logger.getLogger(Hook.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Random rand = new Random();
        int fishType = rand.nextInt(4);
        switch(fishType){
            case 0: this.sprite = blueFishD; break;
            case 1: this.sprite = redFishD; break;
            case 2: this.sprite = greenFishD; break;
            case 3: this.sprite = yellowFishD; break;
        }
        
        lancer();
    }

    public void sensPoisson(){
        //try {
            if (this.sprite == this.blueFishD){ 
                if (sens){
                    this.sprite = this.blueFishD;
                }
                else{
                    this.sprite = this.blueFishG;
                }
            }
            if (this.sprite == this.redFishD){ 
                if (sens){
                    this.sprite = this.redFishD;
                }
                else{
                    this.sprite = this.redFishG;
                }
            //else{
                //this.sprite = ImageIO.read(getClass().getResource("../resources/Poisson bleuG.png"));
            }
        //} catch (IOException ex) {
            //Logger.getLogger(Hook.class.getName()).log(Level.SEVERE, null, ex);
        
    }

    public void miseAJour() {
        if (x >= 1088){
            this.sens = false;
            sensPoisson();
        }
        else if (x <= 96){
            this.sens = true;
            sensPoisson();
        }
        if(this.sens){
            x = x + 5;
        }
        else{
            x = x - 5;
        }
    }

    public void rendu(Graphics2D contexte) {
        contexte.drawImage(this.sprite, (int) x, (int) y, null);
        //contexte.drawImage(this.blueFish, (int) x, (int) y, null);
    }

    public void lancer() {
        this.y = 96 + Math.random() * (704-sprite.getWidth());
        this.x = 96;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    public double getLargeur() {
        return sprite.getHeight();
    }

    public double getHauteur() {
        return sprite.getWidth();
    }

}
