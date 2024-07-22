package objeto;
import entity.*;
import main.GamePanel;

public class OBJ_Martelo extends Entity {
    GamePanel gp;

    public OBJ_Martelo(GamePanel gp){
        super(gp);
        this.gp = gp;

        tipo = tipoMartelo;
        name = "Martelo";
        descida1 = setup("/objetos/martelo", gp.tileSize, gp.tileSize);
        ataqueValor = 2;
        ataqueArea.width = 30;
        ataqueArea.height = 30;

        descrição = "[" + name + "]\nCapaz de esmagar inimigos com sua \ncabeça de aço endurecido.";
    }
    
}
