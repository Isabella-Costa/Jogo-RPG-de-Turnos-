package objeto;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Porta extends SuperObjeto {
    public OBJ_Porta(){
        name = "Porta";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/objetos/porta.png"));
            
        } catch (IOException e) {
              e.printStackTrace();
        }

        colisao = true;
    }
}
