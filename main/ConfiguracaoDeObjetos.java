package main;

import objeto.*;

public class ConfiguracaoDeObjetos {
    

    GamePanel gp;

    public ConfiguracaoDeObjetos(GamePanel gp) {
        this.gp = gp;
    }

    
    public void setObjeto(){
        gp.obj[0] = new OBJ_Chave();
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 9 * gp.tileSize;

        gp.obj[1] = new OBJ_Chave();
        gp.obj[1].worldX = 22 * gp.tileSize;
        gp.obj[1].worldY = 42 * gp.tileSize;

        gp.obj[2] = new OBJ_Chave();
        gp.obj[2].worldX = 2 * gp.tileSize;
        gp.obj[2].worldY = 14 * gp.tileSize;

        gp.obj[3] = new OBJ_Bau();
        gp.obj[3].worldX = 3 * gp.tileSize;
        gp.obj[3].worldY = 3 * gp.tileSize;

        gp.obj[4] = new OBJ_Porta();
        gp.obj[4].worldX = 13 * gp.tileSize;
        gp.obj[4].worldY = 2 * gp.tileSize;

        gp.obj[5] = new OBJ_Porta();
        gp.obj[5].worldX = 37 * gp.tileSize;
        gp.obj[5].worldY = 3 * gp.tileSize;

        gp.obj[6] = new OBJ_Porta();
        gp.obj[6].worldX = 22 * gp.tileSize;
        gp.obj[6].worldY = 13 * gp.tileSize;

    }
}
