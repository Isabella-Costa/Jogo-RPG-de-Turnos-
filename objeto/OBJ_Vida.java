package objeto;

import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class OBJ_Vida extends SuperObjeto{
        GamePanel gp;
    
    public OBJ_Vida(GamePanel gp){
        this.gp = gp;
        
        name = "Vida";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/objetos/coracaoCompleto.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/resources/objetos/coracaoMetate.png"));
            image3= ImageIO.read(getClass().getResourceAsStream("/resources/objetos/coracaoVazio.png"));
            image = utilsF.scaleImage(image, gp.tileSize, gp.tileSize);
            image2 = utilsF.scaleImage(image2, gp.tileSize, gp.tileSize);
            image3 = utilsF.scaleImage(image3, gp.tileSize, gp.tileSize);
            
        } catch (IOException e) {
              e.printStackTrace();
        }
    }
}
