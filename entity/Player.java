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

    public final int screenX; 
    public final int screenY;

    BufferedImage frente1, frente2, costas1, costas2, esquerda1, esquerda2, direita1, direita2;

    public Player(GamePanel gp, Movimentacao keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2  - (gp.tileSize/2);
        screenY = gp.screenHeigth / 2 - (gp.tileSize/2);

        solidArea = new Rectangle(8, 16, 32, 32);
        
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
        try {
            frente1 = ImageIO.read(getClass().getResourceAsStream("/resources/player/playerCostas1.png"));
            frente2 = ImageIO.read(getClass().getResourceAsStream("/resources/player/playerCostas2.png"));
            costas1 = ImageIO.read(getClass().getResourceAsStream("/resources/player/playerFrente1.png"));
            costas2 = ImageIO.read(getClass().getResourceAsStream("/resources/player/playerFrente2.png"));
            esquerda1 = ImageIO.read(getClass().getResourceAsStream("/resources/player/playerEsquerda1.png"));
            esquerda2 = ImageIO.read(getClass().getResourceAsStream("/resources/player/playerEsquerda2.png"));
            direita1 = ImageIO.read(getClass().getResourceAsStream("/resources/player/playerDireita1.png"));
            direita2 = ImageIO.read(getClass().getResourceAsStream("/resources/player/playerDireita2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    public void update() {

        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true ){
           
            if (keyH.upPressed) {
               direction = "frente";

            } else if (keyH.downPressed) {
               direction = "costas";

            } else if (keyH.leftPressed) {
              direction = "esquerda";
              
            } else if (keyH.rightPressed) {
              direction = "direita";
            }
            
            //Check tile Colisão
            colisaoOn = false;
            gp.cCheck.checkTile(this);

            //Se colisão for false, player consegue mover
            if(!colisaoOn){
                
                switch (direction) {
                    case "frente": worldY -= speed; break;
                    case "costas": worldY += speed; break;
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

    public void draw(Graphics2D g2) {
        BufferedImage imagem = null;
        switch (direction) {
            case "frente":
                if (spriteNum == 1){
                    imagem = frente1;
                }
                if (spriteNum == 2){
                    imagem = frente2;
                }
                break;
            case "costas":
                if (spriteNum == 1){
                  imagem = costas1;
                }
                if (spriteNum == 2){
                  imagem = costas2;
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
        g2.drawImage(imagem, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}


