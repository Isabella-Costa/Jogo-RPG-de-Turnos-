package main;

import entity.*;
import tile.Tile;
import tile.TileManager;
import objeto.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
    
    //      Instaciações

    //Sistema:
    TileManager tileM = new TileManager(this);
    public Movimentacao keyH = new Movimentacao(this);
    //Sound sound = new Sound();
    public ColisaoCheck cCheck = new ColisaoCheck(this); 
    public ConfiguracaoDeObjetos aSetter = new ConfiguracaoDeObjetos(this);
    public UserInterface ui = new UserInterface(this);
    public ManipuladorDeEventos eManipuladorDeEventos = new ManipuladorDeEventos(this);
    Thread gameThread;

    //Entity e Objetos
    public Player player = new Player(this, keyH); 
    public Entity obj[] = new Entity[10]; //quantidade de objetos no jogo
    public Entity npc[] = new Entity[10];
    ArrayList<Entity> entityList = new ArrayList<>();
    

    //Estado do jogo
    public int gameState;
    public final int menuState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogoState = 3;


    public GamePanel(){

        this.setPreferredSize(new Dimension (screenWidth,screenHeigth));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }
    
    public void setupGame(){
        aSetter.setObjeto();
        aSetter.setNPC();
        gameState = menuState;
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

        // Título Inicial
        if(gameState == menuState){
            ui.draw(g2);

        }
        //Outros
        else {
            //Tile
            tileM.draw(g2);

            // Add os entitiex na lista
            entityList.clear();

            entityList.add(player);

            for (int i = 0; i < npc.length; i++) {
                if(npc[i] != null){
                    entityList.add(npc[i]);

                }  
            }
            for (int i = 0; i < obj.length; i++) {
                if(obj[i] != null){
                    entityList.add(obj[i]);
                }
                
            }

            //Ordenação
            Collections.sort(entityList, new Comparator<Entity>() {
            @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });
            
            //Desenha Entities
            for (Entity entity : entityList) {
                entity.draw(g2);
            }

            //Lista Entity vazia
            for (int i = 0; i<entityList.size(); i++) {
                entityList.remove(i); 
            }




            //User Interface
            ui.draw(g2);

            }


        g2.dispose();
    }
    
    /*public void playMusic(int i){

        sound.setFile(i);
        sound.play();
        sound.loop();

    }*/

}
