package objeto;
import entity.Entity;
import main.GamePanel;

public class OBJ_EscudoAzul extends Entity {


    public OBJ_EscudoAzul(GamePanel gp) {
        super(gp);

        tipo = tipoEscudo;
        name = "Escudo Azul";
        descida1 = setup("/objetos/escudoAzul", gp.tileSize, gp.tileSize);
        defesaValor = 2;
        descrição = "[" + name + "]\nUm escudo brilhante Azul";
    
    }
}
