package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import objeto.OBJ_Pedra;

public class Monstro extends Entity{
    GamePanel gp;

    public Monstro(GamePanel gp){
        super(gp);
        this.gp = gp;

        tipo = tipoMonstro;
        name = "Minotauro";
        speed = 2;
        maxLife = 4;
        life = maxLife;
        ataque = 5;
        defesa =0;
        exp = 2;
        projectiles = new OBJ_Pedra(gp);

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage(){
        subida1 = setup("/monstro/monstro3", gp.tileSize,gp.tileSize);
        subida2 = setup("/monstro/monstro3", gp.tileSize,gp.tileSize);
        descida1 = setup("/monstro/monstro3", gp.tileSize,gp.tileSize);
        descida2 = setup("/monstro/monstro3", gp.tileSize,gp.tileSize);
        esquerda1 = setup("/monstro/monstro3", gp.tileSize,gp.tileSize);
        esquerda2 = setup("/monstro/monstro3", gp.tileSize,gp.tileSize);
        direita1 = setup("/monstro/monstro3", gp.tileSize,gp.tileSize);
        direita2 = setup("/monstro/monstro3", gp.tileSize,gp.tileSize);
        

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
        int i = new Random().nextInt(100) + 1;
        if(i > 99  && projectiles.vivo == false && shotDisponivelContador== 30){

            projectiles.set(worldX, worldY, direction, true, this);
            gp.projeteisList.add(projectiles);
            shotDisponivelContador = 0;
        }


    }

    public void danoReacao(){
        bloqueioDeAcaoContador =0;
        direction = gp.player.direction;

    }
}