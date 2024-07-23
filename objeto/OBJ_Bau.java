package objeto;

import entity.*;
import main.GamePanel;

public class OBJ_Bau extends Entity{
    GamePanel gp;
    
    public OBJ_Bau(GamePanel gp){
        super(gp);

        name = "Bau";
        descida1 = setup("objetos/portal", gp.tileSize,gp.tileSize);
    }
}
