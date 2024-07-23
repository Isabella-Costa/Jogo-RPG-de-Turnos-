package main;

import entity.*;
import tile.TileManager;


import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GamePanel extends JPanel implements Runnable{

    //configurações de tela
    final int originalTileSize = 16;  //Bloco 20x20
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;   // Bloco 48x48
    public final int maxScreenColuna = 20;
    public final int maxScreenLargura = 12;
    public final int screenWidth = tileSize * maxScreenColuna;      //960 pixels
    public final int screenHeigth = tileSize * maxScreenLargura;    //576 pixels

    //Configurações Mapa WORLD
    public final int maxWorldColuna = 50;
    public final int maxWorldLargura = 50;

    //FPS (Frame Per Second)
    int FPS = 60;
    
    //      Instaciações    //

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
    public Player player; 
    public Entity obj[] = new Entity[30]; //quantidade de objetos no jogo
    public Entity npc[] = new Entity[10];
    public Entity monster[] = new Entity[20];
    public ArrayList<Entity> projeteisList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();
    

    //Estado do jogo
    public int gameState;
    public final int menuState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogoState = 3;
    public final int personagemState = 4;
    public final int opcaoState = 5;
    public final int gameOverState = 6;
    public final int batalhaState = 7;



    public GamePanel(){

        this.setPreferredSize(new Dimension (screenWidth,screenHeigth));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void createPlayer(String characterType) {
        if (characterType == null || characterType.isEmpty()) {
            throw new IllegalArgumentException("Tipo de personagem não pode ser nulo ou vazio.");
        }

        switch (characterType) {
            case "Guerreiro":
                player = new Guerreiro(this, keyH);
                break;
            case "Soldado":
                player = new Soldado(this, keyH);
                break;
            case "Curandeira":
                player = new Curandeira(this, keyH);
                break;
            default:
                throw new IllegalArgumentException("Tipo de personagem desconhecido: " + characterType);
        }
    }

    public Player getPlayer() {
        return player;
    }
    
    public void setupGame(){
        aSetter.setObjeto();
        aSetter.setNPC();
        aSetter.setMontro();

        gameState = menuState;
    }
    
    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void startCombat(Inimigo inimigo) {
        CombatPanel combatPanel = new CombatPanel(this, player, inimigo);
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.setContentPane(combatPanel);
        frame.revalidate();
    }

    public void endCombat() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.setContentPane(this);
        frame.revalidate();
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
           //Monstro
           for (int i = 0; i < monster.length; i++) {
            if(monster[i] != null){
                if(monster[i].vivo == true && monster[i].morrendo == false){
                    monster[i].update();
                }
                if(monster[i].vivo == false){
                    monster[i] = null;
                }
            }
          }
          // PROJETEIS
          for (int i = 0; i < projeteisList.size(); i++) {
            if(projeteisList.get(i) != null){
                if( projeteisList.get(i).vivo == true){
                    projeteisList.get(i).update();
                }
                if(projeteisList.get(i).vivo == false){
                    projeteisList.remove(i);
                }
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

            // Add os Entities na lista
            entityList.clear();

            entityList.add(player);
            //NPC
            for (int i = 0; i < npc.length; i++) {
                if(npc[i] != null){
                    entityList.add(npc[i]);

                }  
            }
            //OBJ
            for (int i = 0; i < obj.length; i++) {
                if(obj[i] != null){
                    entityList.add(obj[i]);
                }
                
            }
            //MONSTRO
            for (int i = 0; i < monster.length; i++) {
                if(monster[i] != null){
                    entityList.add(monster[i]);
                }
                
            }
            for (int i = 0; i < projeteisList.size(); i++) {
                if (projeteisList.get(i) != null) {
                    entityList.add(projeteisList.get(i));
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
            entityList.clear();

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
