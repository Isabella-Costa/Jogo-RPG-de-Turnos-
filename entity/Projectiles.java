package entity;

import main.GamePanel;

public class Projectiles extends Entity{

    Entity user; 

    public Projectiles(GamePanel gp){
        super(gp);

    }
    public void set(int worldX, int worldY, String direction, boolean vivo, Entity user){
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.vivo = vivo;
        this.user = user;
        this.life= this.maxLife;

    }

    public void update(){

        if(user == gp.player){
            int monsterIndex = gp.cCheck.checkEntity(this, gp.monster);
            if(monsterIndex != 999) {
                gp.player.danoMonster(monsterIndex, ataque);
                vivo = false;
            }
        }

        if(user != gp.player){
            boolean contatoPlayer = gp.cCheck.checkPlayer(this);
            if(gp.player.invencibilidade == false && contatoPlayer == true){
                danoPlayer(ataque);
                vivo = false;
            }

        }
        switch (direction) {
            case "subida": worldY -= speed; break;
            case "descida": worldY += speed; break;
            case "esquerda": worldX -= speed; break;
            case "direita": worldX += speed; break;
        }

        life--;
        if(life <= 0){
            vivo = false;
        }

        spriteCounter++;
        if(spriteCounter > 12){
            if(spriteNum == 1){
                spriteNum = 2;
            }else if(spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public boolean temRecursos(Entity user){
        boolean temRecursos = false;
        return temRecursos;

    }

    public void subtraiRecurso(Entity user){
    }
}
