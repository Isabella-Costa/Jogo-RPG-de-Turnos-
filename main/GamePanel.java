package main;
import javax.swing.JPanel;

import entity.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable{

    //configurações de tela
    final int originalTileSize = 16;  //Bloco 16x16
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;   // Bloco 48x48
    final int maxScreenColuna = 16;
    final int maxScreenLargura = 12;
    final int screenWidth = tileSize * maxScreenColuna;      //768 pixels
    final int screenHeigth = tileSize * maxScreenLargura;    //576 pixels
    
    //FPS (Frame Per Second)
    int FPS = 60;



    Movimentacao keyH = new Movimentacao();
    Thread gameThread;
    Player player = new Player(this, keyH); 

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

        double drawIntervalo = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;


        while(gameThread != null){
          
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime)/ drawIntervalo;
            timer += (currentTime - lastTime);


            lastTime = currentTime;

            if(delta >=1){
              update();
              repaint();
              delta--;
              drawCount++;
            }

            /*if(timer >= 1000000000){
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }*/
        }
    }

    public void update(){
        player.update();

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        player.draw(g2);

        g2.dispose();
    }


}
