package ihm;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import sql.*;

/**
 * Fenêtre de jeu utilisant Swing
 *
 * @author 
 */
public class FenetreDeJeu extends JFrame implements ActionListener, KeyListener {

    private BufferedImage framebuffer;
    private Graphics2D contexte;
    private JLabel jLabel1;
    private Jeu jeu;
    private Timer timer;
    private int tempsRestant;

    /**
     * Constructeur de la fenêtre de jeu
     */
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

        // Création du jeu
        this.jeu = new Jeu(this);

        // Création du Timer qui appelle this.actionPerformed() tous les 40 ms
        this.timer = new Timer(40, this);
        this.timer.start();
        this.tempsRestant = 50 * 1000; // Temps restant en millisecondes (100 secondes)

        // Ajout d’un écouteur clavier
        this.addKeyListener(this);
    }

    /**
     * Méthode appelée par le timer pour effectuer la boucle de jeu
     *
     * @param e L'événement d'action déclenché par le Timer
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.jeu.miseAJour();
        this.jeu.rendu(contexte);
        this.jLabel1.repaint();

        // Mise à jour du temps restant
        if (tempsRestant > 0) {
            tempsRestant -= 40;
        } else {
            // Fin du jeu
            this.timer.stop();
            MessageGameOver(jeu.getScore());
            HookSQL hookSQL = new HookSQL();
            hookSQL.supprimerTousLesHooks();
        }
    }

    /**
     * Gestion des entrées clavier : touche enfoncée
     *
     * @param evt L'événement déclenché par une touche pressée
     */
    @Override
    public void keyPressed(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_RIGHT -> this.jeu.getHook().setDroite(true);
            case KeyEvent.VK_LEFT -> this.jeu.getHook().setGauche(true);
            case KeyEvent.VK_UP -> this.jeu.getHook().setHaut(true);
            case KeyEvent.VK_DOWN -> this.jeu.getHook().setBas(true);
        }
    }

    /**
     * Gestion des entrées clavier : touche relâchée
     *
     * @param evt L'événement déclenché par une touche relâchée
     */
    @Override
    public void keyReleased(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_RIGHT -> this.jeu.getHook().setDroite(false);
            case KeyEvent.VK_LEFT -> this.jeu.getHook().setGauche(false);
            case KeyEvent.VK_UP -> this.jeu.getHook().setHaut(false);
            case KeyEvent.VK_DOWN -> this.jeu.getHook().setBas(false);
        }
    }

    @Override
    public void keyTyped(KeyEvent evt) {
        // Non utilisé
    }

    /**
     * Retourne le temps restant dans le jeu
     *
     * @return Le temps restant en millisecondes
     */
    public int getTempsRestant() {
        return this.tempsRestant;
    }

    /**
     * Affiche un message de fin de jeu avec le score final
     *
     * @param score Le score final du joueur
     */
    private void MessageGameOver(int score) {
        String message = "Le temps est écoulé!\nScore final:\n" + score;
        JOptionPane.showMessageDialog(this, message, "Fin du jeu!", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0); // Fermer l'application après la fin du jeu
    }

    /**
     * Méthode principale pour lancer la fenêtre de jeu
     *
     * @param args Arguments de la ligne de commande
     */
    public static void main(String[] args) {
        FenetreDeJeu fenetre = new FenetreDeJeu();
        fenetre.setVisible(true);
    }
}
