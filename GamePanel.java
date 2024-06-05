import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable{

    //configurações de tela
    final int originalTileSize = 16;  //Bloco 16x16
    final int scale = 3;

    final int tileSize = originalTileSize * scale;   // Bloco 48x48
    final int maxScreenColuna = 16;
    final int maxScreenLargura = 12;
    final int screenWidth = tileSize * maxScreenColuna;      //768 pixels
    final int screenHeigth = tileSize * maxScreenLargura;    //576 pixels

    Movimentacao keyH = new Movimentacao();
    Thread gameThread;

    //Define a posição padrão do jogador
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;


    public GamePanel(){

        this.setPreferredSize(new Dimension (screenWidth,screenHeigth));
        this.setBackground(Color.DARK_GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    
    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){

        while(gameThread != null){
             //System.out.println("O loop do jogo está em execução" );

             // 1 ATUALIZAÇÃO: informações da posição do personagem
            update();

             // 2 DRAW: Desenha a tela com as informações atualizadas
            repaint();
        }

    }

    public void update(){

        if(keyH.upPressed == true){
            playerY -= playerSpeed;

        }
        else if(keyH.downPressed == true){
            playerY += playerSpeed;
        }
        else if(keyH.leftPressed){
            playerX -= playerSpeed;
        }
        else if(keyH.rightPressed){
            playerX += playerSpeed;
        }


    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);
        
        g2.fillRect(playerX, playerY, tileSize, tileSize);

        g2.dispose();
    }


}
