package objeto;

import entity.*;
import main.GamePanel;

public class OBJ_Porta extends Entity{
    GamePanel gp;
    
    public OBJ_Porta(GamePanel gp){
        super(gp);
        name = "Porta";
        descida1 = setup("objetos/porta", gp.tileSize,gp.tileSize);
        colisao = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
