package objeto;

import entity.Entity;
import main.GamePanel;


public class OBJ_Espada extends Entity {

    public OBJ_Espada(GamePanel gp) {
        super(gp);

        name = "Espada";
        descida1 = setup("/objetos/espada", gp.tileSize, gp.tileSize);
        ataqueValor = 1;

    }

    
}
