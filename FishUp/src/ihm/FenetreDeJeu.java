package ihm;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import moteur.Joueur;

public class FenetreDeJeu extends JFrame implements ActionListener, KeyListener {

    private BufferedImage framebuffer;
    private Graphics2D contexte;
    private JLabel jLabel1;
    private Jeu jeu;
    private Timer timer;
    private int tempsRestant;
    private int tempoCountDown;  // Pour le compte à rebours
    private boolean jeuCommence = false; // Pour contrôler si le jeu a commencé ou non

    public FenetreDeJeu() {
        // Initialisation de la fenêtre
        this.setSize(1280, 800);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jLabel1 = new JLabel();
        this.jLabel1.setPreferredSize(new java.awt.Dimension(1280, 800));
        this.setContentPane(this.jLabel1);
        this.pack();

        // Création du buffer pour l'affichage du jeu
        this.framebuffer = new BufferedImage(this.jLabel1.getWidth(), this.jLabel1.getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.jLabel1.setIcon(new ImageIcon(framebuffer));
        this.contexte = this.framebuffer.createGraphics();
        
        // Initialisation du jeu
        this.jeu = new Jeu(this);

        // Initialisation du Timer qui appelle actionPerformed() toutes les 40 ms
        this.timer = new Timer(40, this);
        this.timer.start();
        
        // Configuration du temps pour le compte à rebours (5 secondes)
        this.tempoCountDown = 5;
        this.tempsRestant = 100 * 1000; // Temps de jeu, par exemple, 100 secondes

        // Ajout du listener pour le clavier
        this.addKeyListener(this);
    }

    // Méthode appelée par le timer, qui exécute la boucle de jeu
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!jeuCommence) {
            // Affiche le compte à rebours avant le début du jeu
            if (tempoCountDown > 0) {
                this.contexte.clearRect(0, 0, getWidth(), getHeight()); // Efface l'écran
                this.contexte.setFont(new Font("Arial", Font.BOLD, 50));  // Police Arial, Gras, taille 100
                this.contexte.setColor(Color.WHITE);  // Définit la couleur du texte
                this.contexte.drawString("Le jeu commence dans : " + tempoCountDown, 300, 400); // Affiche le compte à rebours
                this.jLabel1.repaint();
                tempoCountDown--; // Décrémente le compte à rebours
                try {
                    Thread.sleep(1000); // Pause de 1 seconde
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            } else {
                // Démarre le jeu après le compte à rebours
                jeuCommence = true;
                this.jeu.miseAJour();
            }
        } else {
            // Met à jour le jeu et effectue le rendu normalement
            this.jeu.miseAJour();
            this.jeu.rendu(contexte);
            this.jLabel1.repaint();

            // Diminue le temps restant du jeu
            if (tempsRestant > 0) {
                tempsRestant -= 40; 
            } else {
                this.timer.stop();
                afficherMessageFinDeJeu(jeu.getScore());
                System.out.println("Fin du Jeu !");
                return;
            }
        }
    }

    // Méthodes de KeyListener pour le déplacement du joueur
    @Override
    public void keyTyped(KeyEvent evt) {
    }
    
    @Override
    public void keyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == evt.VK_RIGHT) {
            this.jeu.getHook().setDroite(true);
        }
        if (evt.getKeyCode() == evt.VK_LEFT) {
            this.jeu.getHook().setGauche(true);
        }
        if (evt.getKeyCode() == evt.VK_UP) {
            this.jeu.getHook().setHaut(true);
        }
        if (evt.getKeyCode() == evt.VK_DOWN) {
            this.jeu.getHook().setBas(true);
        }
    }
    
    @Override
    public void keyReleased(KeyEvent evt) {
        if (evt.getKeyCode() == evt.VK_RIGHT) {
            this.jeu.getHook().setDroite(false);
        }
        if (evt.getKeyCode() == evt.VK_LEFT) {
            this.jeu.getHook().setGauche(false);
        }
        if (evt.getKeyCode() == evt.VK_UP) {
            this.jeu.getHook().setHaut(false);
        }
        if (evt.getKeyCode() == evt.VK_DOWN) {
            this.jeu.getHook().setBas(false);
        }
    }
    
    public int getTempsRestant() {
        return this.tempsRestant;
    }
    
    public static void main(String[] args) {
        FenetreDeJeu fenetre = new FenetreDeJeu();
        fenetre.setVisible(true);
    }

    // Méthode pour afficher le message de fin de jeu
    private void afficherMessageFinDeJeu(int score){
        String message = "Le temps est écoulé !\n" +
                         "Score final :\n" + score;
        javax.swing.JOptionPane.showMessageDialog(this, message, "Fin de Jeu !", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}
