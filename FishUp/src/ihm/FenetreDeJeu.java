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
    private int tempoCountDown;  // Para a contagem regressiva
    private boolean gameStarted = false; // Para controlar se o jogo começou ou não

    public FenetreDeJeu() {
        // Inicialização da janela
        this.setSize(1280, 800);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jLabel1 = new JLabel();
        this.jLabel1.setPreferredSize(new java.awt.Dimension(1280, 800));
        this.setContentPane(this.jLabel1);
        this.pack();

        // Criação do buffer para o display do jogo
        this.framebuffer = new BufferedImage(this.jLabel1.getWidth(), this.jLabel1.getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.jLabel1.setIcon(new ImageIcon(framebuffer));
        this.contexte = this.framebuffer.createGraphics();
        
        // Inicializando o jogo
        this.jeu = new Jeu(this);

        // Inicializando o Timer que chama actionPerformed() a cada 40 ms
        this.timer = new Timer(40, this);
        this.timer.start();
        
        // Configurando o tempo de contagem regressiva (5 segundos)
        this.tempoCountDown = 5;
        this.tempsRestant = 100 * 1000; // O tempo do jogo, por exemplo, 100 segundos

        // Adicionando o listener do teclado
        this.addKeyListener(this);
    }

    // Método chamado pelo timer, que executa o loop do jogo
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameStarted) {
            // Exibe a contagem regressiva até começar o jogo
            if (tempoCountDown > 0) {
                this.contexte.clearRect(0, 0, getWidth(), getHeight()); // Limpa a tela
                this.contexte.setFont(new Font("Arial", Font.BOLD, 50));  // Fonte Arial, Negrito, tamanho 100
                this.contexte.setColor(Color.WHITE);  // Define a cor do texto
                this.contexte.drawString("Le jeu commence en: " + tempoCountDown, 300, 400); // Exibe a contagem regressiva
                this.jLabel1.repaint();
                tempoCountDown--; // Decrementa a contagem regressiva
                try {
                    Thread.sleep(1000); // Atraso de 1 segundo
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            } else {
                // Inicia o jogo após a contagem regressiva
                gameStarted = true;
                this.jeu.miseAJour();
            }
        } else {
            // Atualiza o jogo e renderiza normalmente
            this.jeu.miseAJour();
            this.jeu.rendu(contexte);
            this.jLabel1.repaint();

            // Decrementa o tempo restante do jogo
            if (tempsRestant > 0) {
                tempsRestant -= 40; 
            } else {
                this.timer.stop();
                MessageGameOver(jeu.getScore());
                System.out.println("Fim do Jogo!");
                return;
            }
        }
    }

    // Métodos do KeyListener para movimentação do jogador
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

    // Método para mostrar a mensagem de fim de jogo
    private void MessageGameOver(int score){
        String mensagem = "O tempo acabou!\n" +
                          "Pontuação final:\n" + score;
        javax.swing.JOptionPane.showMessageDialog(this, mensagem, "Fim de Jogo!", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}