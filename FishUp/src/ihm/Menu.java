package ihm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class Menu extends JFrame {

    private BufferedImage imagemDeFundo;

    public Menu() {
        try {
            imagemDeFundo = ImageIO.read(getClass().getResource("../resources/accueil.png")); // Ajuste o caminho da imagem
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Configuração da janela do menu
        setTitle("Menu");
        setSize(1280, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Criando o painel de fundo com a imagem
        JPanel panel = new JPanel() {
            // Sobrescreve o método paintComponent para desenhar a imagem de fundo
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);  // Chama o método da classe pai para garantir que o painel seja desenhado corretamente
                if (imagemDeFundo != null) {
                    g.drawImage(imagemDeFundo, 0, 0, getWidth(), getHeight(), this);  // Redimensiona a imagem para cobrir todo o painel
                }
            }
        };
        panel.setLayout(null); // Layout nulo para posicionar os componentes manualmente
        add(panel); // Adiciona o painel à janela
        
        // Botão para jogar sozinho
        JButton soloButton = new JButton("Jouer seul");
        soloButton.setBounds(530, 490, 200, 40);
        panel.add(soloButton);

        // Botão para jogar multiplayer
        JButton multiplayerButton = new JButton("Multijoueur");
        multiplayerButton.setBounds(530, 540, 200, 40);
        panel.add(multiplayerButton);

        // Botão para as regras
        JButton rulesButton = new JButton("Règles");
        rulesButton.setBounds(530, 590, 200, 40);
        panel.add(rulesButton);

        // Ação do botão "Jouer seul"
        soloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aqui você abre o jogo para um jogador
                setVisible(false); // Fecha o menu
                new FenetreDeJeu().setVisible(true); // Abre a janela do jogo
            }
        });

        // Ação do botão "Multijoueur"
        /*multiplayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aqui você abre o jogo para multiplayer
                setVisible(false);
                new FenetreDeJeuMultijoueur().setVisible(true); // Supondo que você tenha uma classe para multiplayer
            }
        });*/

        // Ação do botão "Règles"
        rulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exibe as regras do jogo
                JOptionPane.showMessageDialog(Menu.this, "Règles du jeu:\n\n1. ...\n2. ...", "Règles", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Centraliza a janela e a torna visível
        setLocationRelativeTo(null);  
        setVisible(true);
    }

    public static void main(String[] args) {
        new Menu();  // Inicia a janela do menu
    }
}