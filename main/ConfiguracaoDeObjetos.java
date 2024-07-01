package main;

import entity.NPC_Mago;
import objeto.*;

public class ConfiguracaoDeObjetos {
    

    GamePanel gp;

    public ConfiguracaoDeObjetos(GamePanel gp) {
        this.gp = gp;
    }

    
    public void setObjeto(){
        gp.obj[0] = new OBJ_Chave(gp);
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 9 * gp.tileSize;

        gp.obj[1] = new OBJ_Chave(gp);
        gp.obj[1].worldX = 22 * gp.tileSize;
        gp.obj[1].worldY = 42 * gp.tileSize;

        gp.obj[2] = new OBJ_Chave(gp);
        gp.obj[2].worldX = 2 * gp.tileSize;
        gp.obj[2].worldY = 14 * gp.tileSize;

        gp.obj[3] = new OBJ_Bau(gp);
        gp.obj[3].worldX = 3 * gp.tileSize;
        gp.obj[3].worldY = 3 * gp.tileSize;

        gp.obj[4] = new OBJ_Porta(gp);
        gp.obj[4].worldX = 13 * gp.tileSize;
        gp.obj[4].worldY = 2 * gp.tileSize;

        gp.obj[5] = new OBJ_Porta(gp);
        gp.obj[5].worldX = 37 * gp.tileSize;
        gp.obj[5].worldY = 3 * gp.tileSize;

        gp.obj[6] = new OBJ_Porta(gp);
        gp.obj[6].worldX = 22 * gp.tileSize;
        gp.obj[6].worldY = 13 * gp.tileSize;

        gp.obj[7] = new OBJ_Raio(gp);
        gp.obj[7].worldX = 39 * gp.tileSize;
        gp.obj[7].worldY = 7 * gp.tileSize;

    
}

    public void setNPC(){
        gp.npc[0] = new NPC_Mago(gp);
        gp.npc[0].worldX = 21 * gp.tileSize;
        gp.npc[0].worldY = 21 * gp.tileSize;
    }
}
