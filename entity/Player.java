package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.*;


public class Player extends Entity {
    
    Movimentacao keyH;
    public int temChave = 0;

    public final int screenX; 
    public final int screenY;

    BufferedImage subida1, subida2, descida1, descida2, esquerda1, esquerda2, direita1, direita2;

    public Player(GamePanel gp, Movimentacao keyH) {
        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth / 2  - (gp.tileSize/2);
        screenY = gp.screenHeigth / 2 - (gp.tileSize/2);

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefaultValues();
        getPlayerImage(); 
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "direita";

        //Player VIDA
        maxLife = 6;
        life = maxLife;

    }

    public void getPlayerImage() {
        subida1 = setup("/player/playerSubida1");
        subida2 = setup("/player/playerSubida2");
        descida1 = setup("/player/playerDescida1");
        descida2 = setup("/player/playerDescida2");
        esquerda1 = setup("/player/playerEsquerda1");
        esquerda2 = setup("/player/playerEsquerda2");
        direita1 = setup("/player/playerDireita2");
        direita2 = setup("/player/playerDireita1");
    }

    public void update() {

        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true ){
           
            if (keyH.upPressed) {
               direction = "subida";

            } else if (keyH.downPressed) {
               direction = "descida";

            } else if (keyH.leftPressed) {
              direction = "esquerda";
              
            } else if (keyH.rightPressed) {
              direction = "direita";
            }
            
            //Check tile Colisão
            colisaoOn = false;
            gp.cCheck.checkTile(this);

            // Check colisão obj
            int objIndex = gp.cCheck.checkObjeto(this, true);
            pegarObjeto(objIndex);

            //Check colisão NPC
            int npcIndex = gp.cCheck.checkEntity(this, gp.npc);
            interacaoNPC(npcIndex);

            //Check Colisão com Monstro
            int monsterIndex = gp.cCheck.checkEntity(this, gp.monster);
            contatoMonstro(monsterIndex);
            

            //Check Evento
            gp.eManipuladorDeEventos.checkEvent();
            gp.keyH.enterPressed = false;



            //Se colisão for false, player consegue mover
            if(!colisaoOn){
                
                switch (direction) {
                    case "subida": worldY -= speed; break;
                    case "descida": worldY += speed; break;
                    case "esquerda": worldX -= speed; break;
                    case "direita": worldX += speed; break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 15) {
              if (spriteNum == 1) {
                  spriteNum = 2;
              } else if (spriteNum ==2){
                  spriteNum = 1;
              }
              spriteCounter =0;
            } 
        }

        //Isso precisa estar fora da instrução key if!
        if (invencibilidade == true){
            invencibilidadeContador++;
            if(invencibilidadeContador > 60){
                invencibilidade = false;
                invencibilidadeContador = 0;
            }
        }
    }



    public void pegarObjeto(int i){
        
        if ( i != 999){

            String objetoName = gp.obj[i].name;

            switch (objetoName) {
                case "Chave":
                    temChave++;
                    gp.obj[i] = null;
                    break;
                case "Porta":
                    if(temChave>0){
                        gp.obj[i] = null;
                        temChave++;

                    }
                    break;
                case "Raio":
                    speed +=1;
                    gp.obj[i] = null;
                    break;
            }

            }

    }
    
    public void interacaoNPC(int i){
        if ( i != 999){

            if(gp.keyH.enterPressed == true){
                gp.gameState = gp.dialogoState;
                gp.npc[i].speak();
            }

        }

    }

    public void contatoMonstro(int i){
        if ( i != 999){
            if(invencibilidade== false){
            life -=1;
            invencibilidade = true;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage imagem = null;
        switch (direction) {
            case "subida":
                if (spriteNum == 1){
                    imagem = subida1;
                }
                if (spriteNum == 2){
                    imagem = subida2;
                }
                break;
            case "descida":
                if (spriteNum == 1){
                  imagem = descida1;
                }
                if (spriteNum == 2){
                  imagem = descida2;
                }
                break;
            case "esquerda":
                if (spriteNum == 1){
                  imagem = esquerda1;
                }
                if (spriteNum == 2){
                  imagem = esquerda2;
                }
                break;
            case "direita":
                if (spriteNum == 1){
                  imagem = direita1;
                }
                if (spriteNum == 2){
                  imagem = direita2;
                }
                break;
        }

        if(invencibilidade == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(imagem, screenX, screenY, null);
        
        //Reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        //
    }
}


