
package moteur;

import java.util.ArrayList;

public class Joueur {
        
    private String ID;
    private int score;
    private ArrayList<Fish> caughtFish; 

    public Joueur() {
        this.ID = "";
        this.score = 0;
        this.caughtFish = new ArrayList<>();
    }


    public Joueur(String ID) {
        this.ID = ID;
        this.score = 0;
        this.caughtFish = new ArrayList<>();
    }

 
    public String getID() {
        return this.ID;
    }

   
    public void setID(String ID) {
        this.ID = ID;
    }

  
    public int getScore() {
        return this.score;
    }

    
    public void setScore(int score) {
        this.score = score;
    }

    
    public ArrayList<Fish> getCaughtFish() {
        return this.caughtFish;
    }

    
    public void addFish(Fish fish) { 
        this.caughtFish.add(fish);
    }

    public void clearCaughtFish() {
        this.caughtFish.clear();
    }

    
    public void addScore(Fish fish) { 
        if (fish != null && fish.getIsCaught()) { 
            this.score += fish.getValue();
        }
    }
}


