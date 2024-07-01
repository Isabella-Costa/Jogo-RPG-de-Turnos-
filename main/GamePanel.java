package main;

import entity.*;
import tile.Tile;
import tile.TileManager;
import objeto.*;

import java.awt.*;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

    //configurações de tela
    final int originalTileSize = 16;  //Bloco 20x20
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;   // Bloco 48x48
    public final int maxScreenColuna = 16;
    public final int maxScreenLargura = 12;
    public final int screenWidth = tileSize * maxScreenColuna;      //768 pixels
    public final int screenHeigth = tileSize * maxScreenLargura;    //576 pixels

    //Configurações Mapa WORLD
    public final int maxWorldColuna = 50;
    public final int maxWorldLargura = 50;

    //FPS (Frame Per Second)
    int FPS = 60;
    
    //Instaciações

    //Sistema:
    TileManager tileM = new TileManager(this);
    Movimentacao keyH = new Movimentacao(this);
    //Sound sound = new Sound();
    public ColisaoCheck cCheck = new ColisaoCheck(this); 
    public ConfiguracaoDeObjetos aSetter = new ConfiguracaoDeObjetos(this);
    public UserInterface ui = new UserInterface(this);
    Thread gameThread;

    //Entity e Objetos
    public Player player = new Player(this, keyH); 
    public SuperObjeto obj[] = new SuperObjeto[10]; //quantidade de objetos no jogo
    public Entity npc[] = new Entity[10];
    

    //Estado do jogo
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    //public final int menuState = 0;

    public GamePanel(){

        this.setPreferredSize(new Dimension (screenWidth,screenHeigth));
        this.setBackground(Color.DARK_GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }
    
    public void setupGame(){
        aSetter.setObjeto();
        aSetter.setNPC();
        gameState = playState;
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

        }
    }

    public void update(){
        if(gameState == playState){
            //Player
           player.update();
           //NPC
           for (int i = 0; i < npc.length; i++) {
             if(npc[i] != null){
                npc[i].update();
             }
           }
        }
        if(gameState == pauseState){
            //
        }

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        
        //Tile
        tileM.draw(g2);

        //Player
        player.draw(g2);

        //Objeto
        for ( int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }

        //NPC
        for ( int i = 0; i < npc.length; i++){
            if(npc[i] != null){
                npc[i].draw(g2);
            }
        }
        //User Interface
        ui.draw(g2);



        g2.dispose();
    }
    
    /*public void playMusic(int i){

        sound.setFile(i);
        sound.play();
        sound.loop();

    }*/

}
