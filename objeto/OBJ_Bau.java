package objeto;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Bau extends SuperObjeto{
    
    public OBJ_Bau(){
        name = "Bau";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/objetos/bau.png"));
            
        } catch (IOException e) {
              e.printStackTrace();
        }
    }
}
