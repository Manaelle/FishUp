package ihm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class Menu extends JFrame {

    private String pseudo;
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

        // Création du panneau de fond avec l'image
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imageDeFond != null) {
                    g.drawImage(imageDeFond, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        panel.setLayout(null);
        add(panel);

        // Bouton pour jouer seul
        JButton soloButton = new JButton("Jouer seul");
        soloButton.setBounds(530, 490, 200, 40);
        panel.add(soloButton);

        // Bouton pour jouer en multijoueur
        JButton multiplayerButton = new JButton("Multijoueur");
        multiplayerButton.setBounds(530, 540, 200, 40);
        panel.add(multiplayerButton);

        // Bouton pour ouvrir les règles
        JButton rulesButton = new JButton("Règles");
        rulesButton.setBounds(530, 590, 200, 40);
        panel.add(rulesButton);

        // Action du bouton "Jouer seul"
        soloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                demanderPseudoEtJouer(false); // Appelle une méthode pour demander le pseudo
            }
        });

        // Action du bouton "Multijoueur"
        multiplayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                demanderPseudoEtJouer(true); // Appelle une méthode pour demander le pseudo
            }
        });

        // Action du bouton "Règles"
        rulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

    /**
     * Méthode pour demander le pseudo et démarrer le jeu
     */
    private void demanderPseudoEtJouer(boolean isMultijoueur) {
        this.pseudo = JOptionPane.showInputDialog(Menu.this, "Entrez votre pseudo :", "Pseudo", JOptionPane.QUESTION_MESSAGE);

        if (pseudo == null || pseudo.trim().isEmpty()) {
            JOptionPane.showMessageDialog(Menu.this, "Veuillez entrer un pseudo avant de continuer.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            if (isMultijoueur) {
                JOptionPane.showMessageDialog(Menu.this, "Mode Multijoueur pas encore disponible.", "Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                setVisible(false);
                new FenetreDeJeu().setVisible(true);
            }
        }
    }
    public String getPseudo() {
        return pseudo;
    }

    public static void main(String[] args) {
        new Menu();
    }
}
