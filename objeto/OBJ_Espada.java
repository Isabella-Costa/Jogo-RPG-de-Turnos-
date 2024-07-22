package objeto;

import entity.Entity;
import main.GamePanel;


public class OBJ_Espada extends Entity {

    public OBJ_Espada(GamePanel gp) {
        super(gp);

        tipo = tipoEspada;
        name = "Espada";
        descida1 = setup("/objetos/espada", gp.tileSize, gp.tileSize);
        ataqueValor = 1;
        ataqueArea.width = 36;
        ataqueArea.height = 36;

        descrição = "[" + name + "]\nUma espada antiga.";

    }

    
}
