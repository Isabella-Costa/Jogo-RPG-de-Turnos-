package objeto;


import entity.*;
import main.GamePanel;

public class OBJ_Chave extends Entity{
    GamePanel gp;
    
    public OBJ_Chave(GamePanel gp){
        super(gp);
        
        name = "Chave";
        descida1 = setup("objetos/chave", gp.tileSize,gp.tileSize);
        descrição = "[" + name + "]\nAbre uma porta.";
    }
}
