/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moteur;

/**
 *
 * @author zzahir
 */
import java.util.ArrayList;

public class GameManager {
    private ArrayList<Joueur> Players;
    private ArrayList<FishingRod> fishingRod;
    private ArrayList<Fish> fishList;
    private ArrayList<Integer> fishValue;
    private int score;
    public GameManager(ArrayList<Joueur> player,ArrayList<FishingRod> fishingRod, ArrayList<Fish> fishList, ArrayList<Integer> fishValue, int score) {
        this.Players = player;
        this.fishingRod = fishingRod;
        this.fishList = fishList;
        this.fishValue = fishValue;
        this.score = score;
    }
    public GameManager() {
        this.Players = new ArrayList<>();
        this.fishingRod = new ArrayList<>(); 
        this.fishList = new ArrayList<>();
        this.fishValue = new ArrayList<>();
        this.score = 0;
    }
    public void startFishing(Fish fish, int value) {
        this.fishList.add(fish); 
        this.fishValue.add(value); 
    }
    public ArrayList<Joueur> getPlayers(){
        return this.Players;
    }
    public void addPlayer(Joueur player) {
    this.Players.add(player); 
    }
    /*public void attemptCatch(Fish fish) {
        for (int i = 0; i < fishList.size(); i++) {
            Fish currentFish = fishList.get(i); 
            
            if (fishingRod.x == currentFish.x && 
                fishingRod.y == currentFish.y) {
                currentFish.setIsCaught(true); 
                this.score += this.fishValue.get(i); 
                break; 
            }
        }
    }*/
    public int getScore() {
        return this.score;
    }
    public void setScore(int score) {
        this.score = score;
    }
}


