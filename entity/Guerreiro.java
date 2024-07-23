package entity;

import main.GamePanel;
import main.Movimentacao;

public class Guerreiro extends Player {
    
    public Guerreiro(GamePanel gp, Movimentacao keyH) {
        super(gp, keyH);
        setDefaultValues();
        getPlayerImage();
        getPlayerAtaqueImage();
    }

    @Override
    public void setDefaultValues() {
        super.setDefaultValues();
        this.name = "Guerreiro";
        maxLife = 8;
        this.força += 2;
        this.destreza += 1;
        maxMana = 3;
        defesa *=2;

    }

    public void getPlayerImage() {
        subida1 = setup("/player/aventureiroSubida1", gp.tileSize, gp.tileSize);
        subida2 = setup("/player/aventureiroSubida2", gp.tileSize, gp.tileSize);
        descida1 = setup("/player/aventureiroDescida1", gp.tileSize, gp.tileSize);
        descida2 = setup("/player/aventureiroDescida2", gp.tileSize, gp.tileSize);
        esquerda1 = setup("/player/aventureiroEsquerda1", gp.tileSize, gp.tileSize);
        esquerda2 = setup("/player/aventureiroEsquerda2", gp.tileSize, gp.tileSize);
        direita1 = setup("/player/aventureiroDireita1", gp.tileSize, gp.tileSize);
        direita2 = setup("/player/aventureiroDireita2", gp.tileSize, gp.tileSize);
    }

    public void getPlayerAtaqueImage(){
        ataqueSubida1 = setup("/player/aventureiroAtaqueSubida", gp.tileSize,gp.tileSize*2);
        ataqueSubida2 = setup("/player/aventureiroAtaqueSubida", gp.tileSize,gp.tileSize*2);
        ataqueDescida1 = setup("/player/aventureiroAtaqueDescida", gp.tileSize,gp.tileSize*2);
        ataqueDescida2 = setup("/player/aventureiroAtaqueDescida", gp.tileSize,gp.tileSize*2);
        ataqueEsquerda1 = setup("/player/aventureiroAtaqueEsquerda", gp.tileSize*2,gp.tileSize);
        ataqueEsquerda2 = setup("/player/aventureiroAtaqueEsquerda",gp.tileSize*2,gp.tileSize);
        ataqueDireita1 = setup("/player/aventureiroAtaqueDireita", gp.tileSize*2,gp.tileSize);
        ataqueDireita2 = setup("/player/aventureiroAtaqueDireita", gp.tileSize*2,gp.tileSize);

    }
}

