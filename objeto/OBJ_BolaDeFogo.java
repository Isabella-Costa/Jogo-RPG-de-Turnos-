package objeto;

import entity.Projectiles;
import main.GamePanel;

public class OBJ_BolaDeFogo extends Projectiles{

    GamePanel gp;

    public OBJ_BolaDeFogo(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = "Bola De Fogo";
        speed = 5;
        maxLife = 80;
        life = maxLife;
        ataque = 2;

    }
    
}
