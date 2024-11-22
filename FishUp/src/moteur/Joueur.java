
package moteur;

public class Joueur extends Entite {
    
    //TODO à compléter avec ce qui va définir votre joueur, etc...
    private String nom;
    private int score;
    private int n_poisson;
    private boolean etat;

    public Joueur(String nom, int score, int n_poisson, boolean etat, int x, int y) {
        super();            // Call the constructor of Entité
        this.nom = nom;
        this.score = score;
        this.n_poisson = n_poisson;
        this.etat = etat;
    }
    
    public String getNom(){
        return nom;
    }
    public String setNom(String nom){
        return this.nom = nom;
    }
    
    public int getScore(){
        return score;
    }
    public int setScore(int score){
        return this.score = score;
    }
    
    public int getN_poisson(){
        return n_poisson;
    }
    public int setN_poisson(int n_poisson){
        return this.n_poisson = n_poisson;
    }
    
    public void AddScore(int valeur_peche){
        this.score += valeur_peche;   // Je suis pas sur de l'implementation
                                      // Le score actuel va etre incremente par la valeur du peche attrape
    }
    public boolean getEtat(){
        return etat;
    }
    public boolean setEtat(boolean etat){
        return this.etat = etat;
    }
    
    public void displayInfo() {
        System.out.println("Player Name: " + nom);
        System.out.println("Poissons attrapes: " + n_poisson);
        System.out.println("Score: " + score);
        System.out.println("Position: (" + getAbscisse() + ", " + getOrdonee() + ")");
    }
}