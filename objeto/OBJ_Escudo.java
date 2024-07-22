package objeto;

import entity.Entity;
import main.GamePanel;

public class OBJ_Escudo extends Entity {

    public OBJ_Escudo(GamePanel gp) {
        super(gp);

        tipo = tipoEscudo;
        name = "Escudo";
        descida1 = setup("/objetos/escudo", gp.tileSize, gp.tileSize);
        defesaValor = 1;
        descrição = "[" + name + "]\nFeito de madeira.";
    
    }
}
