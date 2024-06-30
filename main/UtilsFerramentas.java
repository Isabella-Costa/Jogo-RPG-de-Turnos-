package main;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class UtilsFerramentas {
    
    public BufferedImage scaleImage(BufferedImage original, int largura, int altura ){


        BufferedImage scaleImage = new BufferedImage(largura, altura, original.getType());
        Graphics2D g2 = (Graphics2D) scaleImage.createGraphics();
        g2.drawImage(original,0, 0, largura, altura, null);
        g2.dispose();

        return scaleImage;

    }
}
