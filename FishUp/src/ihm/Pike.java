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
    protected BufferedImage noirFishD;
    protected BufferedImage orangeFishD;
    protected BufferedImage rougeFishD;
    protected BufferedImage roseFishD;
    protected BufferedImage vertFishD;
    protected BufferedImage noirFishG;
    protected BufferedImage orangeFishG;
    protected BufferedImage rougeFishG;
    protected BufferedImage roseFishG;
    protected BufferedImage vertFishG;  
    protected double x, y;
    protected boolean sens;
    protected int fishType;

    public Pike() {
        Random rand = new Random();
        try {
            this.noirFishD = ImageIO.read(getClass().getResource("../resources/poisson_noirD.png"));
            this.orangeFishD = ImageIO.read(getClass().getResource("../resources/poisson_orangeD.png"));
            this.roseFishD = ImageIO.read(getClass().getResource("../resources/poisson_roseD.png"));
            this.rougeFishD = ImageIO.read(getClass().getResource("../resources/poisson_rougeD.png"));
            this.vertFishD = ImageIO.read(getClass().getResource("../resources/poisson_vertD.png"));
            this.noirFishG = ImageIO.read(getClass().getResource("../resources/poisson_noirG.jpeg"));
            this.orangeFishG = ImageIO.read(getClass().getResource("../resources/poisson_orangeG.jpeg"));
            this.roseFishG = ImageIO.read(getClass().getResource("../resources/poisson_roseG.jpeg"));
            this.rougeFishG = ImageIO.read(getClass().getResource("../resources/poisson_rougeG.jpeg"));
            this.vertFishG = ImageIO.read(getClass().getResource("../resources/poisson_vertG.jpeg"));
            
            
            this.fishType = rand.nextInt(5);
            this.sens = rand.nextBoolean();   
            if (sens){    
                switch(this.fishType){
                    case 0: this.sprite = noirFishD; break;
                    case 1: this.sprite = orangeFishD; break;
                    case 2: this.sprite = roseFishD; break;
                    case 3: this.sprite = rougeFishD; break;
                    case 4: this.sprite = vertFishD; break;
                }
            }
            else{
                switch(this.fishType){
                    case 0: this.sprite = noirFishG; break;
                    case 1: this.sprite = orangeFishG; break;
                    case 2: this.sprite = roseFishG; break;
                    case 3: this.sprite = rougeFishG; break;
                    case 4: this.sprite = vertFishG; break;
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
                    case 0: this.sprite = noirFishD; break;
                    case 1: this.sprite = orangeFishD; break;
                    case 2: this.sprite = roseFishD; break;
                    case 3: this.sprite = rougeFishD; break;
                    case 4: this.sprite = vertFishD; break;
                }
            }
            else{
                switch(this.fishType){
                    case 0: this.sprite = noirFishG; break;
                    case 1: this.sprite = orangeFishG; break;
                    case 2: this.sprite = roseFishG; break;
                    case 3: this.sprite = rougeFishG; break;
                    case 4: this.sprite = vertFishG; break;
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
            switch(this.fishType){
                case 0: x = x + 5; break;
                case 1: x = x + 10; break;
                case 2: x = x + 13; break;
                case 3: x = x + 15; break;
                case 4: x = x + 20; break;
            }
        }
        else{
            switch(this.fishType){
                case 0: x = x - 5; break;
                case 1: x = x - 10; break;
                case 2: x = x - 13; break;
                case 3: x = x - 15; break;
                case 4: x = x - 20; break;
            }
        }
    }

    public void rendu(Graphics2D contexte) {
        contexte.drawImage(this.sprite, (int) x, (int) y, null);
        //contexte.drawImage(this.blueFish, (int) x, (int) y, null);
    }

    public void lancer() {
        Random rand = new Random();
        this.y = 96 + Math.random() * (704-sprite.getWidth());
        this.fishType = rand.nextInt(5);
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
    
    public int getFishType() {
        return fishType;
    }

    public double getLargeur() {
        return sprite.getHeight();
    }

    public double getHauteur() {
        return sprite.getWidth();
    }

}
