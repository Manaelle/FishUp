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
import javax.swing.Timer;

/**
 * Exemple de fenetre de jeu en utilisant uniquement des commandes
 *
 * @author guillaume.laurent
 */
public class FenetreDeJeu extends JFrame implements ActionListener, KeyListener {

    private BufferedImage framebuffer;
    private Graphics2D contexte;
    private JLabel jLabel1;
    private Jeu jeu;
    private Timer timer;

    public FenetreDeJeu() {
        // initialisation de la fenetre
        this.setSize(1280, 800);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jLabel1 = new JLabel();
        this.jLabel1.setPreferredSize(new java.awt.Dimension(1280, 800));
        this.setContentPane(this.jLabel1);
        this.pack();

        // Creation du buffer pour l'affichage du jeu et recuperation du contexte graphique
        this.framebuffer = new BufferedImage(this.jLabel1.getWidth(), this.jLabel1.getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.jLabel1.setIcon(new ImageIcon(framebuffer));
        this.contexte = this.framebuffer.createGraphics();
        
        // Creation du jeu
        this.jeu = new Jeu () ;
        
        // Creation du Timer qui appelle this.actionPerformed() tous les 40 ms
        this.timer = new Timer (40, this);
        this.timer.start();
        
        // Ajout d’un ecouteur clavier
        this.addKeyListener(this);
    }

    // Methode appelee par le timer et qui effectue la boucle de jeu
    @Override
    public void actionPerformed ( ActionEvent e) {
        this.jeu.miseAJour();
        this.jeu.rendu(contexte);
        this.jLabel1.repaint();
//        if(this.jeu.estTermine1()){
//            this.timer.stop();
//        }
//        if(this.jeu.estTermine2()){
//            this.timer.stop();
//        }
    }
    
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
    
    
    public static void main(String[] args) {
        FenetreDeJeu fenetre = new FenetreDeJeu();
        fenetre.setVisible(true);
    }

}
