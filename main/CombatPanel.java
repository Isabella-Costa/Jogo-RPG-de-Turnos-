package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import entity.*;


public class CombatPanel extends JPanel {
    private GamePanel gp;
    private Player player;
    private Inimigo inimigo;
    
    private JButton attackButton;
    private JButton inventoryButton;
    private JButton abandonButton;

    public CombatPanel(GamePanel gp, Player player, Inimigo inimigo) {
        this.gp = gp;
        this.player = player;
        this.inimigo = inimigo;

        setLayout(new FlowLayout());

        attackButton = new JButton("Atacar");
        inventoryButton = new JButton("Inventário");
        abandonButton = new JButton("Abandonar Batalha");

        Dimension buttonSize = new Dimension(150, 50);
        attackButton.setPreferredSize(buttonSize);
        inventoryButton.setPreferredSize(buttonSize);
        abandonButton.setPreferredSize(buttonSize);

        attackButton.setBackground(Color.RED);
        attackButton.setForeground(Color.WHITE);
        inventoryButton.setBackground(new Color(139, 69, 19));
        inventoryButton.setForeground(Color.WHITE);
        abandonButton.setBackground(Color.GRAY);
        abandonButton.setForeground(Color.WHITE);

        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Atacar botão pressionado");
            }
        });

        inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Inventário botão pressionado");
                openInventory();
            }
        });

        abandonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Abandonar Batalha botão pressionado");
                System.exit(0);
            }
        });

        add(attackButton);
        add(inventoryButton);
        add(abandonButton);
    }

    public void playerAttack() {
        player.atacando = true;
        player.atacando();
        if (inimigo.life <= 0) {
            gp.endCombat();
        } else {
            enemyTurn();
        }
    }

    public void openInventory() {
        gp.ui.drawInventory();
    }

    public void abandonBattle() {
        gp.endCombat();
    }

    public void enemyTurn() {
        inimigo.usarHabilidade();
        if (player.life <= 0) {
            gp.endCombat();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);  
        Graphics2D g2 = (Graphics2D) g;
        drawGrid(g2);
        drawCharacters(g2); 
    }

    public void drawCharacters(Graphics2D g2) {
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        
        // Centraliza os personagens
        int playerX = panelWidth / 2 - gp.tileSize * 2;
        int playerY = panelHeight / 2 - gp.tileSize / 2;
        
        int enemyX = panelWidth / 2 + gp.tileSize;
        int enemyY = panelHeight / 2 - gp.tileSize / 2;

        int originalPlayerX = player.worldX;
        int originalPlayerY = player.worldY;
        
        int originalEnemyX = inimigo.worldX;
        int originalEnemyY = inimigo.worldY;

        player.worldX = playerX;
        player.worldY = playerY;
        
        inimigo.worldX = enemyX;
        inimigo.worldY = enemyY;

        // Desenha o jogador e  inimigo
        String batalhaDirection = player.direction;
        player.direction = "direita";
        player.draw(g2);
        inimigo.draw(g2);

        player.worldX = originalPlayerX;
        player.worldY = originalPlayerY;
        player.direction = batalhaDirection;
        
        inimigo.worldX = originalEnemyX;
        inimigo.worldY = originalEnemyY;
    }


    public void drawGrid(Graphics2D g2) {
        int largura = getWidth();  
        int altura = getHeight();  
        int squareSize = 50;  

        g2.setColor(Color.WHITE);  

        for (int x = 0; x < largura; x += squareSize) {
            g2.drawLine(x, 0, x, altura);
        }
        for (int y = 0; y < altura; y += squareSize) {
            g2.drawLine(0, y, largura, y);
        }
    }
}


