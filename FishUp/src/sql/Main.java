package sql;

import moteur.Fish;

public class Main {
    public static void main(String[] args) {
        // Crée une instance de ta classe FishSQL
        FishSQL fishSQL = new FishSQL();

        // Exemple : Crée un nouvel objet Fish
        Fish newFish = new Fish("Trout", 15, false);

        // Ajoute ce poisson à la base de données
        fishSQL.creerFish(newFish);

        // Modifie les informations du poisson
        newFish.setIsCaught(true);
        fishSQL.modifierFish(newFish);

        // Affiche les informations du poisson
        fishSQL.voirFish(newFish);

        // Supprime le poisson de la base de données
        fishSQL.supprimerFish(newFish);
    }
}
