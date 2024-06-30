package objeto;

import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;


public class OBJ_Bau extends SuperObjeto{
    GamePanel gp;
    
    public OBJ_Bau(GamePanel gp){
        this.gp = gp;
        
        name = "Bau";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/objetos/bau.png"));
            utilsF.scaleImage(image, gp.tileSize, gp.tileSize);
            
        } catch (IOException e) {
              e.printStackTrace();
        }
    }
}
