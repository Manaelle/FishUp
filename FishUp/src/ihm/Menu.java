package ihm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class Menu extends JFrame {

    private BufferedImage imageDeFond;

    public Menu() {
        try {
            imageDeFond = ImageIO.read(getClass().getResource("../resources/accueil.png")); // Ajustez le chemin de l'image
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Configuration de la fenêtre du menu
        setTitle("Menu");
        setSize(1280, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Création du panneau avec l'image de fond
        JPanel panel = new JPanel() {
            // Surcharge de la méthode paintComponent pour dessiner l'image de fond
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);  // Appelle la méthode parente pour garantir que le panneau est correctement dessiné
                if (imageDeFond != null) {
                    g.drawImage(imageDeFond, 0, 0, getWidth(), getHeight(), this);  // Redimensionne l'image pour couvrir tout le panneau
                }
            }
        };
        panel.setLayout(null); // Layout nul pour positionner manuellement les composants
        add(panel); // Ajoute le panneau à la fenêtre
        
        // Bouton pour jouer seul
        JButton boutonSolo = new JButton("Jouer seul");
        boutonSolo.setBounds(530, 490, 200, 40);
        panel.add(boutonSolo);

        // Bouton pour jouer en multijoueur
        JButton boutonMultijoueur = new JButton("Multijoueur");
        boutonMultijoueur.setBounds(530, 540, 200, 40);
        panel.add(boutonMultijoueur);

        // Bouton pour les règles
        JButton boutonRegles = new JButton("Règles");
        boutonRegles.setBounds(530, 590, 200, 40);
        panel.add(boutonRegles);

        // Action du bouton "Jouer seul"
        boutonSolo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ouvre le jeu pour un joueur
                setVisible(false); // Ferme le menu
                new FenetreDeJeu().setVisible(true); // Ouvre la fenêtre du jeu
            }
        });

        // Action du bouton "Multijoueur"
        /*boutonMultijoueur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ouvre le jeu en multijoueur
                setVisible(false);
                new FenetreDeJeuMultijoueur().setVisible(true); // Supposons que vous ayez une classe pour le multijoueur
            }
        });*/

        // Action du bouton "Règles"
        boutonRegles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Affiche les règles du jeu
                JOptionPane.showMessageDialog(Menu.this, "Règles du jeu:\n\nL'objectif est de pêcher le maximum de poissons dans le temps imparti !\n\nPour vous déplacer, appuyez sur :\n" + //
                                        "Flèche droite pour se déplacer vers la droite\n" + //
                                        "Flèche gauche pour se déplacer vers la gauche\n" + //
                                        "Flèche haut pour se déplacer vers le haut\n" + //
                                        "Flèche bas pour se déplacer vers le bas\n" + //
                                        "Évitez les poissons protégés si vous ne voulez pas être ralenti dans votre quête du meilleur pêcheur !\n" + //
                                        "\n" + //
                                        "BONNE CHANCE !\n" + //
                                        "\n", "Règles", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Centre la fenêtre et la rend visible
        setLocationRelativeTo(null);  
        setVisible(true);
    }

    public static void main(String[] args) {
        new Menu();  // Lance la fenêtre du menu
    }
}