package objeto;

import entity.*;
import main.GamePanel;

public class OBJ_BolaDeFogo extends Projectiles{

    GamePanel gp;

    public OBJ_BolaDeFogo(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = "Bola De Fogo";
        speed = 10;
        maxLife = 80;
        life = maxLife;
        ataque = 2;
        useCusto =1;
        vivo = false;
        getImage();

    }
    public void getImage(){
        subida1 = setup("/projeteis/bolaDeFogo_subida1", gp.tileSize, gp.tileSize);
        subida2 = setup("/projeteis/bolaDeFogo_subida2", gp.tileSize, gp.tileSize);
        descida1 = setup("/projeteis/bolaDeFogo_decida1", gp.tileSize, gp.tileSize);
        descida2 = setup("/projeteis/bolaDeFogo_decida2", gp.tileSize, gp.tileSize);
        esquerda1 = setup("/projeteis/bolaDeFogo_esquerda2", gp.tileSize, gp.tileSize);
        esquerda2 = setup("/projeteis/bolaDeFogo_esquerda2", gp.tileSize, gp.tileSize);
        direita1 = setup("/projeteis/bolaDeFogo_direita1", gp.tileSize, gp.tileSize);
        direita2= setup("/projeteis/bolaDeFogo_direita2", gp.tileSize, gp.tileSize);


    }

    public boolean temRecursos(Entity user){
        boolean temRecursos = false;
        if(user.mana >= useCusto){
            temRecursos = true;
        }
        return temRecursos;

    }
    public void subtraiRecurso(Entity user){
        user.mana -= useCusto;
    }
    
}
