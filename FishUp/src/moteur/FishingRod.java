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
   
    private float castDistance;
    private float reelSpeed;
    private float castSpeed;
    private boolean isCasting;
    private boolean isReeling;
    private float [] castPosition;

    public FishingRod(float castDistance, float reelSpeed, float castSpeed, boolean isCasting, boolean isReeling, float[] castPosition) {
        this.castDistance = castDistance;
        this.reelSpeed = reelSpeed;
        this.castSpeed = castSpeed;
        this.isCasting = isCasting;
        this.isReeling = isReeling;
        this.castPosition = castPosition;
    }

    public FishingRod() {
        this(0.0f, 0.0f, 0.0f, false, false, new float []{0.0f, 0.0f});
    }
    public float getcastDistance(){
        return castDistance;
    }
    public float getreelSpeed(){
        return reelSpeed;
    }
    public float getcastSpeed(){
        return castSpeed;
    }
    public boolean getisCasting(){
        return isCasting;
    }
    public boolean getisReeling(){
        return isReeling;
    }
    public float[] getcastPosition(){
        return castPosition;
    }
    public float setcastDistance(float castdistance){
        return this.castDistance = castdistance;
    }
    public float setreelSpeed(float reelspeed){
        return this.reelSpeed =reelspeed ;
    }
    public float setcastSpeed(float castspeed){
        return this.castSpeed =castspeed;
    }
    public boolean setisCasting(boolean iscasting){
        return this.isCasting = iscasting;
    }
    public boolean setisReeling(boolean isreeling){
        return this.isReeling =isreeling;
    }
    public float[] setcastPosition(float [] castposition){
        return castPosition = castposition;
    }
    public void cast(){
        this.isCasting =true;
    }
    public void HandleCasting(float ocean_floor){
        if( isCasting == true ){
            while(castDistance < ocean_floor){
                castPosition[1]+=(ocean_floor -castPosition [1])*castSpeed;
            }
        }
    }
        
    public void ReelIn(float Horizontal_input, float ocean_surface){
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
    }
    }
    
