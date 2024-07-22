package entity;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.AlphaComposite;
import java.awt.image.BufferedImage;
import java.awt.Color;
import main.*;

public class Entity {

    GamePanel gp;

    public BufferedImage subida1, subida2, descida1, descida2, esquerda1, esquerda2, direita1, direita2;
    public BufferedImage ataqueSubida1, ataqueSubida2,ataqueDescida1, ataqueDescida2, ataqueEsquerda1, 
    ataqueEsquerda2, ataqueDireita1, ataqueDireita2;
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle ataqueArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;

    
    public boolean invencibilidade = false;
   
    String dialogos[] = new String[50]; // variedade de diálogo melhorar dps
    int dialogosIndex = 0;

    //Estado
    public int spriteNum = 1;
    public int worldX;
    public int worldY;
    public String direction = "descida";
    public boolean colisao = false;
    public boolean colisaoOn = false;
    public boolean atacando = false;
    public boolean vivo = true;
    public boolean morrendo = false;
    boolean hpBarOn = false;

    //Contadores
    public int spriteCounter = 0;
    public int bloqueioDeAcaoContador =0;
    public int invencibilidadeContador = 0;
    int morteCounter = 0;
    public int hpBarContador = 0;

    //Caracteristicas Atributos
    public String name; 
    public int speed;
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int level;
    public int força; 
    public int destreza; 
    public int ataque;
    public int defesa;
    public int exp;
    public int proxLevelExp;
    public int coin;
    public Entity currentbArma;
    public Entity currentEscudo;
    public Projectiles projectiles;

    // ITEM Atributos 
    public int ataqueValor;
    public int defesaValor;
    public String descrição = "";

    //TIPO
    public int tipo; // 0 - player, 1 - npc,   2 - monstro
    public final int tipoPlayer = 0;
    public final int tipoNPC = 1;
    public final int tipoMonstro = 2;
    public final int tipoEspada = 3;
    public final int tipoMartelo = 4;
    public final int tipoEscudo = 5;
    public final int tipoConsumivel = 0;


    
    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void use(Entity entity){
        
    }

    public void setAction(){

    }

    public void danoReacao(){

    }
    public void speak(){
        if(dialogos[dialogosIndex] == null){
            dialogosIndex = 0;
        }

        gp.ui.currentDialogo = dialogos[dialogosIndex];
        dialogosIndex++;

        switch (gp.player.direction) {
            case "subida":
                direction = "descida";
                break;
            case "descida":
                direction = "subida";
                break;
            case "esquerda":
                direction = "direita";
                break;
            case "direita":
                direction = "esquerda";
                break;  
        }
        
    }

    public void update(){
        setAction();

        colisaoOn = false;
        gp.cCheck.checkTile(this);
        gp.cCheck.checkObjeto(this, false);
        gp.cCheck.checkEntity(this, gp.npc);
        gp.cCheck.checkEntity(this, gp.monster);
        boolean contatoPlayer = gp.cCheck.checkPlayer(this);

        if(this.tipo == tipoMonstro && contatoPlayer == true){
            danoPlayer(ataque);
        }

        if(!colisaoOn){
                
            switch (direction) {
                case "subida": worldY -= speed; break;
                case "descida": worldY += speed; break;
                case "esquerda": worldX -= speed; break;
                case "direita": worldX += speed; break;
            }
            spriteCounter++;
            if (spriteCounter > 15) {
              if (spriteNum == 1) {
                  spriteNum = 2;
              } else if (spriteNum ==2){
                  spriteNum = 1;
              }
              spriteCounter =0;
            } 

            if (invencibilidade) {
                invencibilidadeContador++;
                if (invencibilidadeContador > 40) {
                    invencibilidade = false;
                    invencibilidadeContador = 0;
                }
            }
        }
    }

    public void danoPlayer(int ataque){
        if(gp.player.invencibilidade == false){
            //Podemos causar danos
            gp.player.life -= 1;
            gp.player.invencibilidade = true;
        }

    }
    
    public void draw(Graphics2D g2){

        BufferedImage imagem = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
    
            //Desenha apenas os tiles visíveis na tela
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            
            switch (direction) {
                case "subida":
                    if (spriteNum == 1){ imagem = subida1;  }
                    if (spriteNum == 2){ imagem = subida2; }
                    break;
                case "descida": 
                    if (spriteNum == 1){ imagem = descida1; }
                    if (spriteNum == 2){ imagem = descida2; }
                    break;
                case "esquerda":
                    if (spriteNum == 1){ imagem = esquerda1; }
                    if (spriteNum == 2){ imagem = esquerda2; }
                    break;
                case "direita":
                    if (spriteNum == 1){ imagem = direita1; }
                    if (spriteNum == 2){ imagem = direita2; }
                    break;
            }

            //Monstro HP bar
            if(tipo == 2 && hpBarOn  == true){

                double oneScale = (double)gp.tileSize/maxLife;
                double hpBarValor = oneScale*life;

                g2.setColor(new Color(35,35,35));
                g2.fillRect(screenX - 1, screenY - 16, gp.tileSize + 2, 7);

                g2.setColor(new Color(255, 0,30));
                g2.fillRect(screenX, screenY - 15, (int)hpBarValor, 5); 

                hpBarContador++;

                if(hpBarContador > 600){
                    hpBarContador = 0;
                    hpBarOn = false;
                }
            }

            if(invencibilidade == true){
                hpBarOn = true;
                hpBarContador = 0;
                mudarAlpha(g2, 0.4f);
            }
            if(morrendo == true){
                morrendoAnimacao(g2);
            }
    
            g2.drawImage(imagem, screenX, screenY, gp.tileSize, gp.tileSize, null);

            mudarAlpha(g2, 1f);
            }
    
    }

    public void morrendoAnimacao(Graphics2D g2){
        morteCounter++;
        int i = 5;

        if(morteCounter <= i){ mudarAlpha(g2, 0f); }
        if(morteCounter > i && morteCounter <= i*2) { mudarAlpha(g2, 1f);}
        if(morteCounter > i*2 && morteCounter <= i*3){ mudarAlpha(g2, 0f);}
        if(morteCounter > i*3 && morteCounter <= i*4){ mudarAlpha(g2, 1f);}
        if(morteCounter > i*4 && morteCounter <= i*5){ mudarAlpha(g2, 0f);}
        if(morteCounter > i*5 && morteCounter <= i*6){ mudarAlpha(g2, 1f);}
        if(morteCounter > i*6 && morteCounter <= i*7){ mudarAlpha(g2, 0f);}
        if(morteCounter > i*7 && morteCounter <= i*8){ mudarAlpha(g2, 1f);}
        if(morteCounter > i*8){
            vivo = false;
        }
    }

    public void mudarAlpha(Graphics2D g2, float alphaValor){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValor));

    }
    
    public BufferedImage setup(String imagePath, int largura, int altura) {
        UtilsFerramentas utilsF = new UtilsFerramentas();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/" + imagePath + ".png"));
            image = utilsF.scaleImage(image, largura, altura);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    
}
    


