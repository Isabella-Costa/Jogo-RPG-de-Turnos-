package objeto;

import entity.*;
import main.GamePanel;

public class OBJ_Vida extends Entity{
    
    public OBJ_Vida(GamePanel gp){
        super(gp);

        name = "Coracao";
        image = setup("objetos/coracaoFull", gp.tileSize,gp.tileSize);
        image2 = setup("objetos/coracaoMetade", gp.tileSize,gp.tileSize);
        image3 = setup("objetos/coracaoVazio", gp.tileSize,gp.tileSize);

    }
}
