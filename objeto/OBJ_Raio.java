package objeto;

import entity.*;
import main.GamePanel;

public class OBJ_Raio extends Entity{
    GamePanel gp;
    
    public OBJ_Raio(GamePanel gp){
        super(gp);
        
        name = "Raio";
        descida1 = setup("objetos/raios", gp.tileSize,gp.tileSize);
    }
    
}
