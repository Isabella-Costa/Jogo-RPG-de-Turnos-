package objeto;

import entity.*;
import main.GamePanel;

public class OBJ_Vida extends Entity{
    
    public OBJ_Vida(GamePanel gp){
        super(gp);

        name = "Coracao";
        image = setup("objetos/coracaoFull");
        image2 = setup("objetos/coracaoMetade");
        image3 = setup("objetos/coracaoVazio");

    }
}
