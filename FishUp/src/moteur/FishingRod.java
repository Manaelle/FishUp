/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moteur;

/**
 *
 * @author zzahir
 */
public class FishingRod extends Entite{
    
    private double castDistance;
    private double reelSpeed;
    private double castSpeed;
    private boolean isCasting;
    private boolean isReeling;
    private String RodId;

    public FishingRod(String RodId, double castDistance, double reelSpeed, double castSpeed, boolean isCasting, boolean isReeling, int x , int y) {
        super();
        this.castDistance = castDistance;
        this.reelSpeed = reelSpeed;
        this.castSpeed = castSpeed;
        this.isCasting = isCasting;
        this.isReeling = isReeling;
        this.RodId = RodId;
    }

    public FishingRod() {
        this("",0.0f, 0.0f, 0.0f, false, false,0,0);
    }
    public double getcastDistance(){
        return castDistance;
    }
    public double getreelSpeed(){
        return reelSpeed;
    }
    public double getcastSpeed(){
        return castSpeed;
    }
    public boolean getisCasting(){
        return isCasting;
    }
    public boolean getisReeling(){
        return isReeling;
    }
    public double setcastDistance(float castdistance){
        return this.castDistance = castdistance;
    }
    public double setreelSpeed(float reelspeed){
        return this.reelSpeed =reelspeed ;
    }
    public double setcastSpeed(float castspeed){
        return this.castSpeed =castspeed;
    }
    public boolean setisCasting(boolean iscasting){
        return this.isCasting = iscasting;
    }
    public boolean setisReeling(boolean isreeling){
        return this.isReeling =isreeling;
    }
 
    public void cast(){
        this.isCasting =true;
    }
    public double getX() {
        return super.getAbscisse(); // Appelle la méthode définie dans Entite
    }

    public double getY() {
        return super.getOrdonnee(); // Appelle la méthode définie dans Entite
    }

    public void setX(int x) {
        super.setAbscisse(x); // Modifie la coordonnée X dans Entite
    }

    public void setY(int y) {
        super.setOrdonnee(y); // Modifie la coordonnée Y dans Entite
    }
    public String getRodId(){
        return this.RodId;
    }
    public String setRodId(String RodId){
        return this.RodId = RodId;
    }
    
    /*public void HandleCasting(float ocean_floor){
        if( isCasting == true ){
            while(castDistance < ocean_floor){
                castPosition[1]+=(ocean_floor -castPosition [1])*castSpeed;
            }
        }
    }*/
        
    /*public void ReelIn(float Horizontal_input, float ocean_surface){
        this.isReeling = true;
        while(castPosition[1]>ocean_surface || castPosition[0] != Horizontal_input){
            if (castPosition[1]>ocean_surface){
                castPosition[1]-=(-ocean_surface+castPosition[1])*reelSpeed;
            }
            else if(castPosition[1]==ocean_surface){
            }
            if(castPosition[0]>Horizontal_input){
                castPosition[0]-=(-Horizontal_input+castPosition[0])*reelSpeed;
            }
            else if(castPosition[0]<Horizontal_input){
                castPosition[0]-=(Horizontal_input-castPosition[0])*reelSpeed;
            }
        }
    }
    
    public void stopReeling(){
        this.isReeling = false;
    }*/
    }
    
