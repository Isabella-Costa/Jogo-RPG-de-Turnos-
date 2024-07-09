package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import main.*;;

public class Entity {

    GamePanel gp;
    public int worldX;
    public int worldY;
    public int speed;
    public BufferedImage subida1, subida2, descida1, descida2, esquerda1, esquerda2, direita1, direita2;
    public String direction = "descida";
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean colisaoOn = false;
    public int bloqueioDeAcaoContador =0;
    String dialogos[] = new String[50]; // variedade de diálogo melhorar dps
    int dialogosIndex = 0;

    public BufferedImage image, image2, image3;
    public String name; 
    public boolean colisao = false;

    //Status Vida
    public int maxLife;
    public int life;


    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction(){

    }
    public void speak(){
        if(dialogos[dialogosIndex] == null){
            dialogosIndex = 0;
        }

        gp.ui.currentDialogo = dialogos[dialogosIndex];
        dialogosIndex++;

        switch (gp.player.direction) {
            case "subida":
                direction = "descida";
                break;
            case "descida":
                direction = "subida";
                break;
            case "esquerda":
                direction = "direita";
                break;
            case "direita":
                direction = "esquerda";
                break;
            
            
        }
        
    }

    public void update(){
        setAction();

        colisaoOn = false;
        gp.cCheck.checkTile(this);
        gp.cCheck.checkPlayer(this);

        if(!colisaoOn){
                
            switch (direction) {
                case "subida": worldY -= speed; break;
                case "descida": worldY += speed; break;
                case "esquerda": worldX -= speed; break;
                case "direita": worldX += speed; break;
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
    
    public void draw(Graphics2D g2){

        BufferedImage imagem = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
    
            //Desenha apenas os tiles visíveis na tela
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            
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
    
            g2.drawImage(imagem, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
    
    }
    
    public BufferedImage setup(String imagePath) {
        UtilsFerramentas utilsF = new UtilsFerramentas();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/" + imagePath + ".png"));
            image = utilsF.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    
}
    


