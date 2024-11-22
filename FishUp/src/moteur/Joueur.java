
package moteur;

public class Joueur extends Entite {
    
    //TODO à compléter avec ce qui va définir votre joueur, etc...
    private String nom;
    private int score;
    private int n_poisson;
    private boolean attrape;

    public Joueur(String nom, int score, int n_poisson, boolean attrape) {
        super();            // Call the constructor of Entité
        this.nom = nom;
        this.score = score;
        this.n_poisson = n_poisson;
        this.attrape = attrape;
     
    }
    
    public String getNom(){
        return nom;
    }
    public String setNom(String NewNom){
        return this.nom = NewNom;
    }
    
    public int getScore(){
        return score;
    }
    public int setScore(int NewScore){
        return this.score = NewScore;
    }
    
    public int getN_poisson(){
        return n_poisson;
    }
    public int setN_poisson(int NewN_poisson){
        return this.n_poisson = NewN_poisson;
    }
    
    public void AddPoisson(boolean etat){
        if(etat == true){
            this.n_poisson += 1;
        }
    }
    
    public void AddScore(int valeur_peche){
        this.score += valeur_peche;   // Je suis pas sur de l'implementation
                                      // Le score actuel va etre incremente par la valeur du peche attrape
    }
    public boolean getAttrape(){
        return attrape;
    }
    public boolean setEtat(boolean NewAttrape){
        return this.attrape = NewAttrape;
    }
    
    public void displayInfo() {
        System.out.println("Player Name: " + nom);
        System.out.println("Poissons attrapes: " + n_poisson);
        System.out.println("Score: " + score);
        System.out.println("Position: (" + getAbscisse() + ", " + getOrdonnee() + ")");
    }
}