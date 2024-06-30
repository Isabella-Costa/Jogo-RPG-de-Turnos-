package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.*;


public class Player extends Entity {
    
    GamePanel gp;
    Movimentacao keyH;
    public int temChave = 0;

    public final int screenX; 
    public final int screenY;

    BufferedImage subida1, subida2, descida1, descida2, esquerda1, esquerda2, direita1, direita2;

    public Player(GamePanel gp, Movimentacao keyH) {
        this.gp = gp;
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
    }

    public void getPlayerImage() {
        subida1 = setup("playerSubida1");
        subida2 = setup("playerSubida2");
        descida1 = setup("playerDescida1");
        descida2 = setup("playerDescida2");
        esquerda1 = setup("playerEsquerda1");
        esquerda2 = setup("playerEsquerda2");
        direita1 = setup("playerDireita2");
        direita2 = setup("playerDireita1");
    }

    public BufferedImage setup(String imageName){
         
        UtilsFerramentas utilsF = new UtilsFerramentas();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/player/" +imageName +".png"));
            image = utilsF.scaleImage(image, gp.tileSize, gp.tileSize);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
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
    }



    public void pegarObjeto(int i){
        
        if ( i != 999){

            String objetoName = gp.obj[i].name;
            switch (objetoName){
                case "Chave":
                   temChave++;
                   gp.obj[i] = null;
                   gp.ui.showMessage("VOCÊ COLETOU UMA CHAVE!");
                   break;
                case "Porta":
                   if(temChave > 0){
                      gp.obj[i] = null;
                      temChave--;
                      gp.ui.showMessage("VOCÊ ABRIU A PORTA");
                   } else {
                    gp.ui.showMessage("VOCÊ PRECISA DE UMA CHAVE");
                   }
                   break;  
                case "Raio":
                    speed +=2;
                    gp.obj[i] = null;
                    gp.ui.showMessage("++VELOCIDADE");
                    break;
                case "Bau":
                    gp.ui.fimDeJogo = true;
                    break;
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
        g2.drawImage(imagem, screenX, screenY, null);
    }
}


