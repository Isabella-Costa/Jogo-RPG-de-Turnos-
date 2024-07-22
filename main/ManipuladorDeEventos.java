package main;

import java.awt.Rectangle;

import entity.Inimigo;

public class ManipuladorDeEventos {
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;
    boolean playerOnLava;
    boolean playerOnAgua;

    public ManipuladorDeEventos(GamePanel gp) {
        this.gp = gp;

        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;

        playerOnLava = false;
        playerOnAgua = false;
    }

    public void checkEvent() {
        //checkLava();
        //checkAgua(gp.dialogoState);

        if (hit(30, 21, "direita")) {
            danoPoco(gp.dialogoState);
        }
        
        if (hit(2, 13, "any")) {
            fonteDECura(gp.dialogoState);
        }

        if (hit(3, 3, "any")) { // Suponha que a posição do baú é (5, 10)
            eventoBatalha(gp.batalhaState);
        }
    }

    public boolean hit(int eventColuna, int eventLargura, String reqDirection) {
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect.x = eventColuna * gp.tileSize + eventRect.x;
        eventRect.y = eventLargura * gp.tileSize + eventRect.y;

        if (gp.player.solidArea.intersects(eventRect)) {
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;
    }

    public void danoPoco(int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogo = "Você caiu em um buraco :(";
        gp.player.life -= 1;
    }

    public void fonteDECura(int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogo = "Você achou a cura!\nSua vida foi recuperada";
        gp.player.life = gp.player.maxLife;
    }
    public void eventoBatalha(int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogo = "Você encontrou um baú! Prepare-se para a batalha!";
        Inimigo inimigo = new Inimigo(gp); 
        gp.startCombat(inimigo);
}



}


