/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lgarciaa
 */

public class Fish {
    public String fishName; // Nom du poisson
    public float value; // Valeur en points du poisson
    public boolean isCaught; // Indique si le poisson a été attrapé.

    public Fish(String name, float val, boolean caught){
        this.fishName = name;
        this.value = val;
        this.isCaught = false;
    }
    
    public void OnCaught(String fishName, float val, boolean caught ){
        if (caught == true){
            System.out.println("Le peche " + fishName + "qui cout " + value + "a ete attrape");
        }
    }
    public String getFishName(){
        return fishName;
    }
    public void setFishName(String newFishName){
        this.fishName = newFishName;
    }
    public float getValue(){
        return value;
    }
    public void setValue(float newValue){
        this.value = newValue;
    }
    public boolean getIsCaught(){
        return isCaught;
    }
    public void setIsCaught(boolean newIsCaught){
        this.isCaught = newIsCaught;
    }
}