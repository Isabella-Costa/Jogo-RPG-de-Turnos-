package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class Monstro extends Entity{

    public Monstro(GamePanel gp){
        super(gp);

        tipo = 2;
        name = "Esqueleto Monstro";
        speed = 2;
        maxLife = 4;
        life = maxLife;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage(){
        subida1 = setup("/monstro/monstro");
        subida2 = setup("/monstro/monstro");
        descida1 = setup("/monstro/monstro");
        descida2 = setup("/monstro/monstro");
        esquerda1 = setup("/monstro/monstro");
        esquerda2 = setup("/monstro/monstro");
        direita1 = setup("/monstro/monstro");
        direita2 = setup("/monstro/monstro");
        

    }

    public void setAction(){
        bloqueioDeAcaoContador++;
        
        if(bloqueioDeAcaoContador == 120){
          Random random = new Random();
          int i = random.nextInt(100) + 1; // escolhe um numero de 1 a 100

          if(i <= 25){
              direction = "subida";
          }
          if(i > 25 && i<= 50){
              direction = "descida";
          }
          if(i > 50 && i<= 75){
              direction = "esquerda";
          }
          if(i > 75 && i <= 100){
              direction = "direita";
          }

          bloqueioDeAcaoContador = 0;
        }
    }
}