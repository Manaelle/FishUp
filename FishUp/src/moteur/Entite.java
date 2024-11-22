/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moteur;

/**
 *
 * @author lgarciaa
 */
public class Entite {
    private double x;
    private double y;

    public void Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setAbscisse(double x) {
        this.x = x;
    }
    public void setOrdonnee(double y) {
        this.y = y;
    }

    public double distance(Entite p) {
        return Math.sqrt((this.x - p.x) * (this.x - p.x)
                + (this.y - p.y) * (this.y - p.y));
    }

    @Override
    public void afficher() {
        System.out.println("Abscisse = " + this.x);
        System.out.println("Ordonnee = " + this.y);
    }

    @Override
    public int compareTo(Object obj) {
        Entite autre = (Entite) obj;
        if (this.x < autre.x) {
            return -1;
        } else if (this.x > autre.x) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ')';
    }
}
