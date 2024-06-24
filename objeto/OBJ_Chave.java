package objeto;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Chave extends SuperObjeto{
    
    public OBJ_Chave(){
        name = "Chave";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/objetos/chave.png"));
            
        } catch (IOException e) {
              e.printStackTrace();
        }

        solidArea.x = 5;
    }
}
