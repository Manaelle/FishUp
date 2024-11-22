package moteur;
/**
 *
 * @author lgarciaa
 */

public class Fish extends Entite {
    
    public String fishName; 
    public double value; 
    public boolean isCaught;

    public Fish(String name, float val, boolean caught){
        super();
        this.fishName = name;
        this.value = val;
        this.isCaught = false;
    }
    
    public void OnCaught(String fishName, double val, boolean caught ){
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
    public double getValue(){
        return value;
    }
    public void setValue(double newValue){
        this.value = newValue;
    }
    public boolean getIsCaught(){
        return isCaught;
    }
    public void setIsCaught(boolean newIsCaught){
        this.isCaught = newIsCaught;
    }
}