package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import main.GamePanel;

public class Inimigo extends Entity {
    public BufferedImage inimigo1;
    private int habilidadeUsada;
    private boolean escudoAtivo = false;
    private int escudoTurnos = 0;

    public Inimigo(GamePanel gp) {
        super(gp);
        setDefaultValues();
        getInimigoImage();
    }

    public void setDefaultValues() {
        name = "Inimigo";
        worldX = gp.tileSize * 10;
        worldY = gp.tileSize * 10;
        direction = "inimigo1";
        speed = 0;
        maxLife = 50;
        life = maxLife;
        ataque = 10;
        defesa = 5;
        vivo = true;
        morrendo = false;
        
    }

    public void getInimigoImage() {
        inimigo1 = setup("/inimigo/inimigo1", gp.tileSize, gp.tileSize);
    
    }

    public void executeSkill(int skillNumber, Entity alvo) {
        switch (skillNumber) {
            case 1:
                powerfulAttack(alvo);
                break;
            case 2:
                areaAttack();
                break;
            case 3:
                defenseBuff();
                break;
            case 4:
                heal();
                break;
        }
    }

    private void powerfulAttack(Entity alvo) {
        int dano = ataque * 2 - alvo.defesa;
        alvo.life -= (dano > 0) ? dano : 0;
        if (alvo.life < 0) alvo.life = 0;
    }

    private void areaAttack() {
        for (Entity entity : gp.monster) {
            int dano= ataque - entity.defesa;
            entity.life -= (dano > 0) ? dano : 0;
            if (entity.life < 0) entity.life = 0;
        }
    }

    private void defenseBuff() {
        defesa += 5;
    }

    private void heal() {
        life += 20;
        if (life > maxLife) life = maxLife;
    }

    @Override
    public void setAction() {
        habilidadeUsada = (int)(Math.random() * 4) + 1;
    }

    @Override
    public void update() {
        setAction();
        usarHabilidade();

        if (invencibilidade) {
            invencibilidadeContador++;
            if (invencibilidadeContador > 40) {
                invencibilidade = false;
                invencibilidadeContador = 0;
            }
        }

        if (escudoAtivo) {
            escudoTurnos--;
            if (escudoTurnos <= 0) {
                escudoAtivo = false;
                defesa -= 10; // Remove o bônus de defesa
            }
        }

        if (life <= 0) {
            morrendo = true;
        }
    }

    public void usarHabilidade() {
        switch (habilidadeUsada) {
            case 1: habilidadeGolpeDevastador(); break;
            case 2: habilidadeEscudoDeSombras(); break;
            case 3: habilidadeChamaInfernal(); break;
            case 4: habilidadeCurarFeridas(); break;
        }
    }

    public void habilidadeGolpeDevastador() {
        int dano = 20;
        gp.player.receberDano(dano);
        System.out.println("Golpe Devastador usado! Causou " + dano + " de dano.");
    }

    public void habilidadeEscudoDeSombras() {
        escudoAtivo = true;
        escudoTurnos = 3;
        defesa += 10; // Aumenta a defesa em 10
        System.out.println("Escudo de Sombras usado! Defesa aumentada por " + escudoTurnos + " turnos.");
    }

    public void habilidadeChamaInfernal() {
        int dano = 15;
        gp.player.receberDano(dano);
        System.out.println("Chama Infernal usada! Causou " + dano + " de dano ao jogador.");
    }

    public void habilidadeCurarFeridas() {
        int cura = 20;
        life += cura;
        if (life > maxLife) {
            life = maxLife;
        }
        System.out.println("Curar Feridas usado! Curou " + cura + " de vida.");
    }

    public void draw(Graphics2D g2) {
        BufferedImage imagem = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        
        if (vivo) {
            switch (spriteNum) {
                case 1: imagem = inimigo1; break;

            }

            g2.drawImage(imagem, screenX, screenY, gp.tileSize, gp.tileSize, null);

            
        }
    }

}

