package main;

import objeto.OBJ_Chave;

public class ConfiguracaoDeAtivos {
    

    GamePanel gp;

    public ConfiguracaoDeAtivos(GamePanel gp) {
        this.gp = gp;
    }

    
    public void setObjeto(){
        gp.obj[0] = new OBJ_Chave();
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldX = 7 * gp.tileSize;

        gp.obj[1] = new OBJ_Chave();
        gp.obj[1].worldX = 20 * gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;

    }
}
