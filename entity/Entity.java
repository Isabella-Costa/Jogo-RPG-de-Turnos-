package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.Rectangle;
import main.*;;

public class Entity {
    GamePanel gp;
    
    public int worldX;
    public int worldY;
    public int speed;

    public BufferedImage subida1, subida2, descida1, descida2, esquerda1, esquerda2, direita1, direita2;

    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean colisaoOn = false;


    public Entity(GamePanel gp) {
        this.gp = gp;
    }
    
    
    public BufferedImage setup(String imagePath){
         
        UtilsFerramentas utilsF = new UtilsFerramentas();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/" +imagePath +".png"));
            image = utilsF.scaleImage(image, gp.tileSize, gp.tileSize);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    
}
    


