/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lgarciaa
 */
public class InterfaceManager {
    public String scoreText; // affiche le score actuel du joueur
    public String fishCaughtText; // Indicateur du nombre de poissons attrapés par le joueur
    public String timerText; // Affiche le temps de pêche restant dans la session
    public String messageText; // Affiche des instructions, des notifs, ou des alertes (ex. : "Poisson rare attrapé !" ou "Temps écoulé").
    
    public void UpdateScore(int score){
        // comment mettre a jour l'affichage du score ;
        
        // Display the current score to the console or update a UI element.
        System.out.println("Score actuel : " + score);
    }
    public void UpdateFishCaught(int fishCount){
        System.out.println("Nombre de poissons attrapes : " + fishCount);
    }
    public void UpdateTimer(float timeRemaining){
        System.out.println(timeRemaining);
    }
    public void ShowGameOverScreen(int score){
        System.out.println("Fin de partie!\nScore : " + score + "\nNouvelle partie\nMenu Principal");
    }
}
