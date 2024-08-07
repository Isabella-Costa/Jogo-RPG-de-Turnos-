package entity;

import main.GamePanel;
import main.Movimentacao;

public class Soldado extends Player {
    
    public Soldado(GamePanel gp, Movimentacao keyH) {
        super(gp, keyH);
        setDefaultValues();
        getPlayerAtaqueImage();
        getPlayerImage();
    }

    @Override
    public void setDefaultValues() {
        super.setDefaultValues();
        speed +=1;
        maxLife = 6;
        this.name = "Soldado";
        this.força += 1;
        this.destreza += 2;
        maxMana = 4;
        defesa *=2;

    }

    public void getPlayerImage() {
        subida1 = setup("/player/soldadoSubida1", gp.tileSize, gp.tileSize);
        subida2 = setup("/player/soldadoSubida2", gp.tileSize, gp.tileSize);
        descida1 = setup("/player/soldadoDescida1", gp.tileSize, gp.tileSize);
        descida2 = setup("/player/soldadoDescida2", gp.tileSize, gp.tileSize);
        esquerda1 = setup("/player/soldadoEsquerda1", gp.tileSize, gp.tileSize);
        esquerda2 = setup("/player/soldadoEsquerda2", gp.tileSize, gp.tileSize);
        direita1 = setup("/player/soldadoDireita1", gp.tileSize, gp.tileSize);
        direita2 = setup("/player/soldadoDireita2", gp.tileSize, gp.tileSize);
    }

    public void getPlayerAtaqueImage(){
        ataqueSubida1 = setup("/player/soldadoAtaqueSubida", gp.tileSize,gp.tileSize*2);
        ataqueSubida2 = setup("/player/soldadoAtaqueSubida", gp.tileSize,gp.tileSize*2);
        ataqueDescida1 = setup("/player/soldadoAtaqueDescida", gp.tileSize,gp.tileSize*2);
        ataqueDescida2 = setup("/player/soldadoAtaqueDescida", gp.tileSize,gp.tileSize*2);
        ataqueEsquerda1 = setup("/player/soldadoAtaqueEsquerda", gp.tileSize*2,gp.tileSize);
        ataqueEsquerda2 = setup("/player/soldadoAtaqueEsquerda",gp.tileSize*2,gp.tileSize);
        ataqueDireita1 = setup("/player/soldadoAtaqueDireita", gp.tileSize*2,gp.tileSize);
        ataqueDireita2 = setup("/player/soldadoAtaqueDireita", gp.tileSize*2,gp.tileSize);

    }
    
}

