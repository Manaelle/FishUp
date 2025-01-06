package moteur;
/**
 *
 * @author lgarciaa
 */

public class Fish extends Entite {
    
    public int fish_id; 
    public double value; 
    public boolean isCaught;

    public Fish(int id, double val, boolean caught){
        super();
        this.fish_id = id;
        this.value = val;
        this.isCaught = caught;
    }
    
    public void OnCaught(int fish_id, boolean caught ){
        if (caught == true){
            System.out.println("Le poisson " + fish_id + "a été attrapé");
        }
    }
    public int getFish_id(){
        return fish_id;
    }
    public void setFish_id(int newFish_id){
        this.fish_id = newFish_id;
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