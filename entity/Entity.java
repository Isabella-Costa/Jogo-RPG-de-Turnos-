package entity;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;;

public class Entity {
    
    public int worldX;
    public int worldY;
    public int speed;

    public BufferedImage frente1, frente2, costas1, costas2, esquerda1, esquerda2, direita1, direita2;

    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean colisaoOn = false;
}
