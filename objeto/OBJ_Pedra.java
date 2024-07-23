package objeto;

import entity.*;
import main.GamePanel;

public class OBJ_Pedra extends Projectiles{

    GamePanel gp;

    public OBJ_Pedra(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = "Pedra";
        speed = 6;
        maxLife = 80;
        life = maxLife;
        ataque = 2;
        useCusto =1;
        vivo = false;
        getImage();

    }
    public void getImage(){
        subida1 = setup("/projeteis/pedra_descida", gp.tileSize, gp.tileSize);
        subida2 = setup("/projeteis/pedra_descida", gp.tileSize, gp.tileSize);
        descida1 = setup("/projeteis/pedra_descida", gp.tileSize, gp.tileSize);
        descida2 = setup("/projeteis/pedra_descida", gp.tileSize, gp.tileSize);
        esquerda1 = setup("/projeteis/pedra_descida", gp.tileSize, gp.tileSize);
        esquerda2 = setup("/projeteis/pedra_descida", gp.tileSize, gp.tileSize);
        direita1 = setup("/projeteis/pedra_descida", gp.tileSize, gp.tileSize);
        direita2= setup("/projeteis/pedra_descida", gp.tileSize, gp.tileSize);


    }
    public boolean temRecursos(Entity user){
        boolean temRecursos = false;
        if(user.municao >= useCusto){
            temRecursos = true;
        }
        return temRecursos;

    }
    public void subtraiRecurso(Entity user){
        user.municao -= useCusto;
    }
}
