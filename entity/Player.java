package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.Movimentacao;

public class Player extends Entity {
    
    GamePanel gp;
    Movimentacao keyH;

    BufferedImage frente1, frente2, costas1, costas2, esquerda1, esquerda2, direita1, direita2;

    public Player(GamePanel gp, Movimentacao keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage(); // Chama o método para carregar as imagens
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "direita";
    }

    public void getPlayerImage() {
        try {
            frente1 = ImageIO.read(getClass().getResourceAsStream("/resources/player/playerFrente1.png"));
            frente2 = ImageIO.read(getClass().getResourceAsStream("/resources/player/playerFrente2.png"));
            costas1 = ImageIO.read(getClass().getResourceAsStream("/resources/player/playerCostas1.png"));
            costas2 = ImageIO.read(getClass().getResourceAsStream("/resources/player/playerCostas2.png"));
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
               y -= speed;
            } else if (keyH.downPressed) {
               direction = "costas";
               y += speed;
            } else if (keyH.leftPressed) {
              direction = "esquerda";
              x -= speed;
            } else if (keyH.rightPressed) {
              direction = "direita";
               x += speed;
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
        g2.drawImage(imagem, x, y, gp.tileSize, gp.tileSize, null);
    }
}


