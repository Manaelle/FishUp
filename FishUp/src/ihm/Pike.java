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
    protected int fishType;

    public Pike() {
        Random rand = new Random();
        try {
            this.blueFishD = ImageIO.read(getClass().getResource("../resources/Poisson bleuD.png"));
            this.redFishD = ImageIO.read(getClass().getResource("../resources/Poisson rougeD.png"));
            this.greenFishD = ImageIO.read(getClass().getResource("../resources/Poisson vertD.png"));
            this.yellowFishD = ImageIO.read(getClass().getResource("../resources/Poisson jauneD.png"));
            this.blueFishG = ImageIO.read(getClass().getResource("../resources/Poisson bleuG.png"));
            this.redFishG = ImageIO.read(getClass().getResource("../resources/Poisson rougeG.png"));
            this.greenFishG = ImageIO.read(getClass().getResource("../resources/Poisson vertG.png"));
            this.yellowFishG = ImageIO.read(getClass().getResource("../resources/Poisson jauneG.png"));
            this.fishType = rand.nextInt(4);
            this.sens = rand.nextBoolean();   
            if (sens){    
                switch(this.fishType){
                    case 0: this.sprite = blueFishD; break;
                    case 1: this.sprite = redFishD; break;
                    case 2: this.sprite = greenFishD; break;
                    case 3: this.sprite = yellowFishD; break;
                }
            }
            else{
                switch(this.fishType){
                    case 0: this.sprite = blueFishG; break;
                    case 1: this.sprite = redFishG; break;
                    case 2: this.sprite = greenFishG; break;
                    case 3: this.sprite = yellowFishG; break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Hook.class.getName()).log(Level.SEVERE, null, ex);
        }
        lancer();
    }

    public void sensPoisson(){
        //try {
            if (sens){
                switch(this.fishType){
                    case 0: this.sprite = blueFishD; break;
                    case 1: this.sprite = redFishD; break;
                    case 2: this.sprite = greenFishD; break;
                    case 3: this.sprite = yellowFishD; break;
                }
            }
            else{
                switch(this.fishType){
                    case 0: this.sprite = blueFishG; break;
                    case 1: this.sprite = redFishG; break;
                    case 2: this.sprite = greenFishG; break;
                    case 3: this.sprite = yellowFishG; break;
                }
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
        if(this.sens){
            this.x = 96;
        }
        else{
            this.x = 1088;
        }
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
