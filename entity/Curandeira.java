package entity;

import main.GamePanel;
import main.Movimentacao;

public class Curandeira extends Player {
    
    public Curandeira(GamePanel gp, Movimentacao keyH) {
        super(gp, keyH);
        setDefaultValues();
        getPlayerImage();
        getPlayerAtaqueImage();

    }

    @Override
    public void setDefaultValues() {
        super.setDefaultValues();
        this.name = "Curandeira";
        this.maxLife = 10;
        this.maxMana = 5;
        defesa *=4;
    }

    public void getPlayerImage() {
        subida1 = setup("/player/curandeiraSubida1", gp.tileSize, gp.tileSize);
        subida2 = setup("/player/curandeiraSubida2", gp.tileSize, gp.tileSize);
        descida1 = setup("/player/curandeiraDescida1", gp.tileSize, gp.tileSize);
        descida2 = setup("/player/curandeiraDescida2", gp.tileSize, gp.tileSize);
        esquerda1 = setup("/player/curandeiraEsquerda1", gp.tileSize, gp.tileSize);
        esquerda2 = setup("/player/curandeiraEsquerda2", gp.tileSize, gp.tileSize);
        direita1 = setup("/player/curandeiraDireita1", gp.tileSize, gp.tileSize);
        direita2 = setup("/player/curandeiraDireita2", gp.tileSize, gp.tileSize);
    }

    public void getPlayerAtaqueImage(){
        ataqueSubida1 = setup("/player/curandeiraAtaqueSubida", gp.tileSize,gp.tileSize*2);
        ataqueSubida2 = setup("/player/curandeiraAtaqueSubida", gp.tileSize,gp.tileSize*2);
        ataqueDescida1 = setup("/player/curandeiraAtaqueDescida", gp.tileSize,gp.tileSize*2);
        ataqueDescida2 = setup("/player/curandeiraAtaqueDescida", gp.tileSize,gp.tileSize*2);
        ataqueEsquerda1 = setup("/player/curandeiraAtaqueEsquerda", gp.tileSize*2,gp.tileSize);
        ataqueEsquerda2 = setup("/player/curandeiraAtaqueEsquerda",gp.tileSize*2,gp.tileSize);
        ataqueDireita1 = setup("/player/curandeiraAtaqueDireita", gp.tileSize*2,gp.tileSize);
        ataqueDireita2 = setup("/player/curandeiraAtaqueDireita", gp.tileSize*2,gp.tileSize);

    }
}

