package objeto;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Chave extends SuperObjeto{
    GamePanel gp;
    
    public OBJ_Chave(GamePanel gp){
        this.gp = gp;
        
        name = "Chave";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/objetos/chave.png"));
            utilsF.scaleImage(image, gp.tileSize, gp.tileSize);
            
        } catch (IOException e) {
              e.printStackTrace();
        }

        solidArea.x = 5;
    }
}
