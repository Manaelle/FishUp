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
import java.io.PrintWriter;

/**
 * FenetreDeJeu class for managing the game window and logic.
 */
public class FenetreDeJeu extends JFrame implements ActionListener, KeyListener {

    private BufferedImage framebuffer;
    private Graphics2D contexte;
    private JLabel jLabel1;
    private Jeu jeu; // Game logic object
    private Timer timer;
    private int tempsRestant;
    private GameServer gameserver;
    private PrintWriter serverOut;

    // Constructor for single-player mode
    public FenetreDeJeu() {
        initializeUI(); // Initialize UI components
        initializeGame(new Jeu(this)); // Initialize single-player game logic
    }

    // Constructor for multiplayer (server-side)
    public FenetreDeJeu(GameServer gameserver) {
        this.gameserver = gameserver;
        initializeUI();
        initializeGame(new Jeu(gameserver)); // Initialize multiplayer server-side game logic
    }

    // Constructor for multiplayer (client-side)
    public FenetreDeJeu(PrintWriter serverOut) {
        this.serverOut = serverOut;
        initializeUI();
        initializeGame(new Jeu(this)); // Initialize multiplayer client-side game logic
    }

    // Initialize the UI components
    private void initializeUI() {
        this.setSize(1280, 800);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.jLabel1 = new JLabel();
        this.jLabel1.setPreferredSize(new java.awt.Dimension(1280, 800));
        this.setContentPane(this.jLabel1);
        this.pack();

        this.framebuffer = new BufferedImage(this.jLabel1.getWidth(), this.jLabel1.getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.jLabel1.setIcon(new ImageIcon(framebuffer));
        this.contexte = this.framebuffer.createGraphics();

        this.timer = new Timer(40, this); // Timer for game loop (40 ms ~ 25 FPS)
        this.tempsRestant = 10 * 1000; // 10-second countdown

        this.addKeyListener(this);
    }

    // Initialize the game logic
    private void initializeGame(Jeu newJeu) {
        this.jeu = newJeu; // Assign the game logic object
        this.timer.start(); // Start the game loop timer
    }

    public void sendToServer(String message) {
        if (serverOut != null) {
            serverOut.println(message);
        }
    }

    // Game loop method called by the timer
    @Override
    public void actionPerformed(ActionEvent e) {
        if (jeu != null) {
            jeu.miseAJour(); // Update game logic
            jeu.rendu(contexte); // Render game elements
            this.jLabel1.repaint(); // Refresh the display

            if (tempsRestant > 0) {
                tempsRestant -= 40;
            } else {
                this.timer.stop();
                MessageGameOver(jeu.getScore());
            }
        } else {
            System.err.println("Error: jeu is not initialized."); // This should no longer occur
        }
    }

    @Override
    public void keyTyped(KeyEvent evt) {}

    @Override
    public void keyPressed(KeyEvent evt) {
        if (jeu == null) return;

        switch (evt.getKeyCode()) {
            case KeyEvent.VK_RIGHT -> {
                jeu.getHook().setDroite(true);
                sendToServer("MOVE_RIGHT");
            }
            case KeyEvent.VK_LEFT -> {
                jeu.getHook().setGauche(true);
                sendToServer("MOVE_LEFT");
            }
            case KeyEvent.VK_UP -> {
                jeu.getHook().setHaut(true);
                sendToServer("MOVE_UP");
            }
            case KeyEvent.VK_DOWN -> {
                jeu.getHook().setBas(true);
                sendToServer("MOVE_DOWN");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent evt) {
        if (jeu == null) return;

        switch (evt.getKeyCode()) {
            case KeyEvent.VK_RIGHT -> jeu.getHook().setDroite(false);
            case KeyEvent.VK_LEFT -> jeu.getHook().setGauche(false);
            case KeyEvent.VK_UP -> jeu.getHook().setHaut(false);
            case KeyEvent.VK_DOWN -> jeu.getHook().setBas(false);
        }
    }

    public int getTempsRestant() {
        return this.tempsRestant;
    }

    private void MessageGameOver(int score) {
        String mensagem = "Le temps est écoulé!\n" +
                          "Score final:\n" + score;

        javax.swing.JOptionPane.showMessageDialog(this, mensagem, "Fin du jeu!", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    public void updateFromServer(String serverState) {
        if (jeu != null) {
            jeu.updateFromServer(serverState);
            repaint(); // Refresh the display
        } else {
            System.err.println("Error: jeu is not initialized.");
        }
    }

    public static void main(String[] args) {
        FenetreDeJeu fenetre = new FenetreDeJeu();
        fenetre.setVisible(true);
    }
}
