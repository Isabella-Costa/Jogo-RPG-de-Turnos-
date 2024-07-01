package entity;
import main.*;

public class NPC_Mago extends Entity {

    public NPC_Mago(GamePanel gp){
        super(gp);

        direction = "direita";
        speed = 1;
    }

    public void getPlayerImage() {
        subida1 = setup("/npc/magoSubida1");
        subida2 = setup("/npc/magoSubida2");
        descida1 = setup("/npc/magoDescida1");
        descida2 = setup("/npc/magoDescida2");
        esquerda1 = setup("/npc/magoEsquerda1");
        esquerda2 = setup("/npc/magoEsquerda2");
        direita1 = setup("/npc/magoDireita2");
        direita2 = setup("/npc/magoDireita1");
    }
}
