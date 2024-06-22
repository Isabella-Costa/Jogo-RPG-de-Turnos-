package main;

import entity.Entity;

public class ColisaoCheck {

    GamePanel gp;

    public ColisaoCheck(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRigthWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
        
        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRigthCol = entityRigthWorldX/gp.tileSize;
        int entityTopLargura = entityTopWorldY/gp.tileSize;
        int entityBottomLargura = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction){
            case "up":
               entityTopLargura = (entityTopWorldY - entity.speed) / gp.tileSize;
               tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopLargura];
               tileNum2 = gp.tileM.mapTileNum[entityRigthCol][entityTopLargura];
               if(gp.tileM.tile[tileNum1].colisao == true || gp.tileM.tile[tileNum2].colisao == true ){
                    entity.colisaoOn = true;
               }
               break;

            case "down":
               entityBottomLargura = (entityBottomWorldY + entity.speed) / gp.tileSize;
               break;

            case "left":
               break;

            case "right":
               break;
            
            
        }

    }
}

