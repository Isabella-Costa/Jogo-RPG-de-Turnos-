package objeto;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Raio extends SuperObjeto{
        public OBJ_Raio(){
        name = "Raio";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/objetos/raios.png"));
        } catch (IOException e) {
              e.printStackTrace();
        }
    }
    
}
