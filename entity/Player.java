package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.*;
import objeto.OBJ_Escudo;
import objeto.OBJ_Espada;


public class Player extends Entity {
    
    Movimentacao keyH;
    public int temChave = 0;

    public final int screenX; 
    public final int screenY;

    public boolean ataqueCancelado = false;

    

    public Player(GamePanel gp, Movimentacao keyH) {
        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth / 2  - (gp.tileSize/2);
        screenY = gp.screenHeigth / 2 - (gp.tileSize/2);

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        ataqueArea.width =36;
        ataqueArea.height = 36;


        setDefaultValues();
        getPlayerImage(); 
        getPlayerAtaqueImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "direita";

        //Player VIDA
        level = 1;
        maxLife = 6;
        life = maxLife;
        força = 1; // quanto mais força tem , mas dano ele causa
        destreza =1; // quanto mais destreza tem , mas dano ele recebe
        exp=0;
        proxLevelExp = 5; // quanto de exp precisa
        coin = 0;
        currentbArma = new OBJ_Espada(gp);
        currentEscudo = new OBJ_Escudo(gp);
        ataque = getAtaque(); //O valor total do ataque é decidido pela força e arma
        defesa = getDefesa();  // O valor total da defesa é descidido pela destreza e escudo 

    }

    public int getAtaque(){
    return ataque = força * currentbArma.ataqueValor;
    }
    
    public int getDefesa(){
        return defesa = destreza * currentEscudo.defesaValor;
    }

    public void getPlayerImage() {
        subida1 = setup("/player/aventureiroSubida1", gp.tileSize,gp.tileSize);
        subida2 = setup("/player/aventureiroSubida2", gp.tileSize,gp.tileSize);
        descida1 = setup("/player/aventureiroDescida1", gp.tileSize,gp.tileSize);
        descida2 = setup("/player/aventureiroDescida2", gp.tileSize,gp.tileSize);
        esquerda1 = setup("/player/aventureiroEsquerda1", gp.tileSize,gp.tileSize);
        esquerda2 = setup("/player/aventureiroEsquerda2", gp.tileSize,gp.tileSize);
        direita1 = setup("/player/aventureiroDireita2", gp.tileSize,gp.tileSize);
        direita2 = setup("/player/aventureiroDireita1", gp.tileSize,gp.tileSize);
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

    public void update() {
        if (atacando) {
            atacando();
        } else if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed) {
            if (keyH.upPressed) {
                direction = "subida";
            } else if (keyH.downPressed) {
                direction = "descida";
            } else if (keyH.leftPressed) {
                direction = "esquerda";
            } else if (keyH.rightPressed) {
                direction = "direita";
            }
    
            // Check tile Colisão
            colisaoOn = false;
            gp.cCheck.checkTile(this);
    
            // Check colisão obj
            int objIndex = gp.cCheck.checkObjeto(this, true);
            pegarObjeto(objIndex);
    
            // Check colisão NPC
            int npcIndex = gp.cCheck.checkEntity(this, gp.npc);
            interacaoNPC(npcIndex);
    
            // Check Colisão com Monstro
            int monsterIndex = gp.cCheck.checkEntity(this, gp.monster);
            contatoMonstro(monsterIndex);
    
            // Check Evento
            gp.eManipuladorDeEventos.checkEvent();
    
            // Se colisão for false, player consegue mover
            if (!colisaoOn && !keyH.enterPressed) {
                switch (direction) {
                    case "subida": worldY -= speed; break;
                    case "descida": worldY += speed; break;
                    case "esquerda": worldX -= speed; break;
                    case "direita": worldX += speed; break;
                }
            }

            if(keyH.enterPressed == true && ataqueCancelado == false){
                atacando = true;
                spriteCounter = 0;
            }

            ataqueCancelado = false;

            keyH.enterPressed = false;
    
            spriteCounter++;
            if (spriteCounter > 15) {
                spriteNum = (spriteNum == 1) ? 2 : 1;
                spriteCounter = 0;
            }
        }

        if (invencibilidade) {
            invencibilidadeContador++;
            if (invencibilidadeContador > 60) {
                invencibilidade = false;
                invencibilidadeContador = 0;
            }
        }
    }
    

    public void atacando() {
        spriteCounter++;
        if (spriteCounter <= 5) {
            spriteNum = 1;
        } 
        else if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;

            //Salva o atual worldX/Y, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //Ajusta players worldX/Y para a "Area de Ataque"
            switch (direction) {
                case "subida": worldY -= ataqueArea.height; break;
                case "descida": worldY += ataqueArea.height; break;
                case "esquerda": worldX -= ataqueArea.width; break;
                case "direita": worldX += ataqueArea.width; break;
            }
            //ataqueArea torna-se solidArea
            solidArea.width = ataqueArea.width;
            solidArea.height = ataqueArea.height;

            //Check colisão do montros com as atualizações worldX/Y, solidarea
            int monsterIndex = gp.cCheck.checkEntity(this, gp.monster);
            danoMonster(monsterIndex);

            //Depois de verificar a colisão, volta aos dados originais
            worldX= currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }
        else {
            spriteNum = 1;
            spriteCounter = 0;
            atacando = false;
        }
    }
    

    public void pegarObjeto(int i){
        
        if ( i != 999){
            String objetoName = gp.obj[i].name;
            switch (objetoName) {
                case "Chave": temChave++; gp.obj[i] = null;
                    break;
                case "Porta":
                    if(temChave>0){
                        gp.obj[i] = null;
                        temChave++;
                    }
                    break;
                case "Raio":
                    speed +=1;
                    gp.obj[i] = null;
                    break;
            }
        }

    }
    
    public void interacaoNPC(int i) {
        if (gp.keyH.enterPressed) {
            if (i != 999) {
                ataqueCancelado = true;
                gp.gameState = gp.dialogoState;
                gp.npc[i].speak();
            } else {
                atacando = true;
            }
        }
    }
    
    public void contatoMonstro(int i){
        if ( i != 999){
            if(invencibilidade== false){
            life -=1;
            invencibilidade = true;
            }
        }
    }

    public void danoMonster(int i){
        if(i != 999){
            if(gp.monster[i].invencibilidade == false){
                gp.monster[i].life -= 1;
                gp.monster[i].invencibilidade = true;  
                gp.monster[i].danoReacao();
                
                if(gp.monster[i].life <= 0){
                    gp.monster[i].morrendo = true;
                    
                }
            }
        }

    }

    public void draw(Graphics2D g2) {

        BufferedImage imagem = null;

        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "subida":
                if(atacando == false){
                    if (spriteNum == 1){ imagem = subida1;}
                    if (spriteNum == 2){imagem = subida2; }
                }
                if (atacando == true) {
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1){ imagem = ataqueSubida1;}
                    if (spriteNum == 2){imagem = ataqueSubida2; }
                }
                break;
            case "descida":
                if(atacando == false){
                    if (spriteNum == 1){ imagem = descida1; }
                    if (spriteNum == 2){ imagem = descida2;}
                }
                if (atacando == true) {
                    if (spriteNum == 1){ imagem = ataqueDescida1;}
                    if (spriteNum == 2){ imagem = ataqueDescida2; }
                }
                break;
            case "esquerda":
                if(atacando == false){
                    if (spriteNum == 1){ imagem = esquerda1; }
                    if (spriteNum == 2){ imagem = esquerda2; }
                }
                if (atacando == true) {
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1){ imagem = ataqueEsquerda1; }
                    if (spriteNum == 2){ imagem = ataqueEsquerda2; }
                }
                break;
            case "direita":
                if(atacando == false){
                    if (spriteNum == 1){ imagem = direita1; }
                    if (spriteNum == 2){ imagem = direita2; }
                }
                if (atacando == true) {
                    if (spriteNum == 1){ imagem = ataqueDireita1; }
                    if (spriteNum == 2){ imagem = ataqueDireita2; }
                }
                break;
        }

        if(invencibilidade == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(imagem, tempScreenX, tempScreenY, null);
        
        //Reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        //
    }
}


