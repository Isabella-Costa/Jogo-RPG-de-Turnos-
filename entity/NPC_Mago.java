package entity;
import java.util.Random;

import main.*;

public class NPC_Mago extends Entity {

    public NPC_Mago(GamePanel gp){
        super(gp);

        direction = "descida";
        speed = 1;

        getImage();
    }

    public void getImage() {
        subida1 = setup("/npc/magoSubida1");
        subida2 = setup("/npc/magoSubida2");
        descida1 = setup("/npc/magoDescida1");
        descida2 = setup("/npc/magoDescida2");
        esquerda1 = setup("/npc/magoEsquerda1");
        esquerda2 = setup("/npc/magoEsquerda2");
        direita1 = setup("/npc/magoDireita2");
        direita2 = setup("/npc/magoDireita1");
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
