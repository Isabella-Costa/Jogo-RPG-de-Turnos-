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

        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerAttack();
            }
        });

        inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openInventory();
            }
        });

        abandonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abandonBattle();
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
        // Lógica para abrir o inventário
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
        Graphics2D g2 = (Graphics2D) g;
        drawGrid(g2);
        player.draw(g2);
        inimigo.draw(g2);
    }

    public void drawGrid(Graphics2D g2) {
        int largura = 50; // número de linhas
        int altura = 50; // número de colunas
        int squareSize = 50; // tamanho de cada quadrado

        for (int larg = 0; larg < largura; larg++) {
            for (int alt = 0; alt < altura; alt++) {
                int y = alt * squareSize;
                int x = larg * squareSize;
                g2.drawRect(x, y, squareSize, squareSize);
            }
        }
    }
}


