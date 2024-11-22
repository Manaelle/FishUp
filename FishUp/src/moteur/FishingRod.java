/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moteur;

/**
 *
 * @author zzahir
 */
public class FishingRod {
   
    private double castDistance;
    private double reelSpeed;
    private double castSpeed;
    private boolean isCasting;
    private boolean isReeling;

    public FishingRod(double castDistance, double reelSpeed, double castSpeed, boolean isCasting, boolean isReeling) {
        this.castDistance = castDistance;
        this.reelSpeed = reelSpeed;
        this.castSpeed = castSpeed;
        this.isCasting = isCasting;
        this.isReeling = isReeling;
    }

    public FishingRod() {
        this(0.0f, 0.0f, 0.0f, false, false);
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
    
