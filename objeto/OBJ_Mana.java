package objeto;

import entity.Entity;
import main.GamePanel;

public class OBJ_Mana extends Entity {

    GamePanel gp;

    public OBJ_Mana(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Mana Cristal";
        image = setup("/objetos/manaCristalFull", gp.tileSize, gp.tileSize);
        image2 = setup("/objetos/manaCristalBranco", gp.tileSize, gp.tileSize);
    }
    
}
