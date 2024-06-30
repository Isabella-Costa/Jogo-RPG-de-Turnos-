package objeto;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Porta extends SuperObjeto {
    GamePanel gp;

    public OBJ_Porta(GamePanel gp){
        this.gp = gp;
        
        name = "Porta";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/objetos/porta.png"));
            utilsF.scaleImage(image, gp.tileSize, gp.tileSize);
            
        } catch (IOException e) {
              e.printStackTrace();
        }

        colisao = true;
    }
}
