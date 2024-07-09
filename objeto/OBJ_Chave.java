package objeto;


import entity.*;
import main.GamePanel;

public class OBJ_Chave extends Entity{
    GamePanel gp;
    
    public OBJ_Chave(GamePanel gp){
        super(gp);
        
        name = "Chave";
        descida1 = setup("objetos/chave");
    }
}
