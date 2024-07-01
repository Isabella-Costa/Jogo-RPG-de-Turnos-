package main;

import entity.Entity;

public class ColisaoCheck {

    GamePanel gp;

    public ColisaoCheck(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction) {
            case "subida":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].colisao || gp.tileM.tile[tileNum2].colisao) {
                    entity.colisaoOn = true;
                }
                break;
            case "descida":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].colisao || gp.tileM.tile[tileNum2].colisao) {
                    entity.colisaoOn = true;
                }
                break;
            case "esquerda":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].colisao || gp.tileM.tile[tileNum2].colisao) {
                    entity.colisaoOn = true;
                }
                break;
            case "direita":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].colisao || gp.tileM.tile[tileNum2].colisao) {
                    entity.colisaoOn = true;
                }
                break;
        }
    }


    public int checkObjeto (Entity entity, boolean player){
        int index = 999;
        
        for(int i = 0; i < gp.obj.length; i++){
            if(gp.obj[i] != null){

                //obtem a posição da área sólida da entity
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                //obtem a posição da área sólida do objeto
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (entity.direction) {
                    case "subida":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){ //Checa se os dois "retângulos" se inteceptam
                            if(gp.obj[i].colisao == true){
                                entity.colisaoOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "descida":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){ 
                            if(gp.obj[i].colisao == true){
                                entity.colisaoOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "esquerda":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){ 
                            if(gp.obj[i].colisao == true){
                                entity.colisaoOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "direita":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){ 
                            if(gp.obj[i].colisao == true){
                                entity.colisaoOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        };
                        break;
                } 
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
        }
    }
        return index;
    }
    // NPC ou Inimigo
    public int checkEntity(Entity entity, Entity[] alvo){
        int index = 999;
        
        for(int i = 0; i < alvo.length; i++){
            if(alvo[i] != null){

                //obtem a posição da área sólida da entidade
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                //obter a posição da área sólida do alvo
                alvo[i].solidArea.x = alvo[i].worldX + alvo[i].solidArea.x;
                alvo[i].solidArea.y = alvo[i].worldY + alvo[i].solidArea.y;

                switch (entity.direction) {
                    case "subida":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(alvo[i].solidArea)){ //Checa se os dois "retângulos" se inteceptam
                            entity.colisaoOn = true;
                            index = i;
                        break;
                    }
                    case "descida":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(alvo[i].solidArea)){ 
                            entity.colisaoOn = true;
                            index = i;
                        }
                        break;
                    case "esquerda":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(alvo[i].solidArea)){ 
                            entity.colisaoOn = true;
                            index = i;
                        }
                        break;
                    case "direita":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(alvo[i].solidArea)){ 
                            entity.colisaoOn = true;
                            index = i;
                        }
                        break;
                } 
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                alvo[i].solidArea.x = alvo[i].solidAreaDefaultX;
                alvo[i].solidArea.y = alvo[i].solidAreaDefaultY;
        }
    }
        return index;
        
    }

    public void checkPlayer (Entity entity){

            //obtem a posição da área sólida da entidade
            entity.solidArea.x = entity.worldX + entity.solidArea.x;
            entity.solidArea.y = entity.worldY + entity.solidArea.y;

            //obter a posição da área sólida do alvo
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

            switch (entity.direction) {
                case "subida":
                    entity.solidArea.y -= entity.speed;
                    if(entity.solidArea.intersects(gp.player.solidArea)){ //Checa se os dois "retângulos" se inteceptam
                        entity.colisaoOn = true;
                    break;
                }
                case "descida":
                    entity.solidArea.y += entity.speed;
                    if(entity.solidArea.intersects(gp.player.solidArea)){ 
                        entity.colisaoOn = true;
                    }
                    break;
                case "esquerda":
                    entity.solidArea.x -= entity.speed;
                    if(entity.solidArea.intersects(gp.player.solidArea)){ 
                        entity.colisaoOn = true;
                    }
                    break;
                case "direita":
                    entity.solidArea.x += entity.speed;
                    if(entity.solidArea.intersects(gp.player.solidArea)){ 
                        entity.colisaoOn = true;
                    }
                    break;
            } 
            entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;

    }

}



