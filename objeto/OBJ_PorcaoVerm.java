package objeto;
import entity.Entity;
import main.GamePanel;

public class OBJ_PorcaoVerm extends  Entity{

    int valor = 5;
    GamePanel gp;

    public OBJ_PorcaoVerm(GamePanel gp) {
        super(gp);
        this.gp = gp;

        tipo = tipoConsumivel;
        name = "Porçao Vermelha";
        descida1 = setup("/objetos/porcao_vermelha", gp.tileSize, gp.tileSize);
        defesaValor = 1;
        descrição = "[" + name + "]\nCura sua vida por $" + valor + ".";
    
 
   }
   public void use(Entity entity){
    
        gp.gameState = gp.dialogoState;
        gp.ui.currentDialogo = "Você bebeu a " + name +"!\n" + 
                "Sua vida foi recuperada por " + valor;
        if(gp.player.life > gp.player.maxLife){
            gp.player.life = gp.player.maxLife;
        }
        entity.life += valor;
   }
}
