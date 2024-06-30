package objeto;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Raio extends SuperObjeto{
        GamePanel gp;

        public OBJ_Raio(GamePanel gp){
        this.gp = gp;
        name = "Raio";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/objetos/raios.png"));
            utilsF.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
              e.printStackTrace();
        }
    }
    
}
