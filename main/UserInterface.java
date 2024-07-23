package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import objeto.OBJ_Mana;
import objeto.OBJ_Vida;
import entity.*;

public class UserInterface {
    GamePanel gp;
    Graphics2D g2;
    Font fonte10, fonte15, fonte20, fonte30 ,fonte40, fonte80;
    Font fonteTitulo;
    BufferedImage coracaoFull, coracaoMetade, coracaoVazio, cristalFull, cristalBranco;
    public boolean messageOn = false;
    //public String message = "";
    //int contadorMessage =0;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageContador = new ArrayList<>();
    public boolean fimDeJogo = false;
    public String currentDialogo = "";
    public int comandoNum =0;
    Image logo;
    public int menuNum = 0;
    public int telaScreenState = 0; // 0 - primeira tela /menu, 1 - escolhaP  -- 2 - mapa 3/batalha 
    public int slotColuna = 0;
    public int slotLargura = 0;
    int subState = 0;
    //private int contadorMessage = 0;

    public UserInterface(GamePanel gp) {
        this.gp = gp;
        this.fonte15 = new Font("Cambria", Font.BOLD, 10);
        this.fonte15 = new Font("Cambria", Font.BOLD, 15);
        this.fonte20 = new Font("Cambria", Font.BOLD, 20 );
        this.fonte30 = new Font("Cambria", Font.BOLD, 30 ); //Definir a fonte depois
        this.fonte40 = new Font("Cambria", Font.BOLD, 40 );
        this.fonte80 = new Font("Cambria", Font.BOLD, 80);

        logo = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/menu/logo.png"));

        // Cria obj Saude
        Entity coracao = new OBJ_Vida(gp);
        coracaoFull = coracao.image;
        coracaoMetade = coracao.image2;
        coracaoVazio = coracao.image3;
        Entity cristal = new OBJ_Mana(gp);
        cristalFull = cristal.image;
        cristalBranco = cristal.image2;

    }

    public void addMessage(String texto){
        message.add(texto);
        messageContador.add(0);
    }

    public void draw(Graphics2D g2){

        this.g2 = g2;

        g2.setFont(fonte20);
        g2.setColor(Color.WHITE);

        //Menu State
        if(gp.gameState == gp.menuState){
            drawMenuScreen();
        }
        
        // Play State
        if(gp.gameState == gp.playState){
            drawPlayerLife();
            drawMessage();
        }
        // Pause State
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawTelaDePause();
            drawOpcoesSceen();
        }

        // Dialogo State
        if(gp.gameState == gp.dialogoState){
            drawPlayerLife();
            drawTelaDeDialogo();
        }
        //  Personagem State
        if(gp.gameState == gp.personagemState){
            drawPersonagemScreen();
            drawInventory();

        }
        if(gp.gameState == gp.gameOverState){
            drawGameOverScreen();

        }
    }

    public void drawMessage(){
        int messageX = gp.tileSize;
        int messageY = gp.tileSize*4;
        g2.setFont(fonte40);

        for(int i = 0; i < message.size(); i++){
            if(message.get(i) != null){
                g2.setColor(Color.BLACK);
                g2.drawString(message.get(i), messageX-1, messageY-1);
                g2.setColor(Color.WHITE);
                g2.drawString(message.get(i), messageX, messageY);

                int contador = messageContador.get(i) + 1; //messageContador++;
                messageContador.set(i, contador);// define-moddifca o contador do array
                messageY +=50;

                if(messageContador.get(i) > 180) {
                    message.remove(i);
                    messageContador.remove(i);

                }

            }
        }
    }

    public void drawGameOverScreen(){
        g2.setColor(new Color (0,0,0, 150));
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeigth);

        int x;
        int y;
        String text;
        g2.setFont(fonte80);
        //  Sombra
        text = "Game Over";
        g2.setColor(Color.black);
        x = getXparaCentralizarTexto(text);
        y = gp.tileSize*4;
        g2.drawString(text, x, y);
        //  Texto
        g2.setColor(Color.WHITE);
        g2.drawString(text, x-3, y-3);

        //  TENTE NOVAMENTE
        g2.setFont(fonte20);
        text = "TENTE NOVAMENTE";
        x = getXparaCentralizarTexto(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        if(comandoNum == 0){
            g2.drawString(">", x- 40, y);
        }

        // VOLTAR TELA MENU
        text = "VOLTAR";
        x = getXparaCentralizarTexto(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(comandoNum == 1){
            g2.drawString(">", x- 40, y);
        }


    }

    public void drawPlayerLife(){

        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;
        
        //Desenha  Vida Max
        while(i < gp.player.maxLife/2){
            g2.drawImage(coracaoVazio, x, y, null);
            i++;
            x+=gp.tileSize;
        }
        //Reset
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        //Desenha vida atual
        while(i < gp.player.life){
            g2.drawImage(coracaoMetade, x, y, null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(coracaoFull, x, y, null);
            }
            i++;
            x+=gp.tileSize;
        }
        // Desenha Max Mana
        x = (gp.tileSize/2) - 5;
        y = (int) (gp.tileSize*1.5);
        i =0;
        while(i < gp.player.maxMana){
            g2.drawImage(cristalBranco, x, y, null);
            i++;
            x+= 35;
        }

        // Draw Mana
        x = gp.tileSize/2 - 5;
        y = (int) (gp.tileSize*1.5);
        i =0;
        while(i < gp.player.mana){
            g2.drawImage(cristalFull, x, y, null);
            i++;
            x+= 35;
        }

    }

    public void drawMenuScreen(){

        if(telaScreenState == 0){
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeigth);
    
            //Nome do Jogo
            g2.setFont(fonte40);
            String texto = "Ascenção dos Guerreiros Alados";
            int x = getXparaCentralizarTexto(texto);
            int y = gp.tileSize * 3; 
    
            //Sombreado
            g2.setFont(fonte40);
            g2.setColor(Color.DARK_GRAY);
            g2.drawString(texto, x+1, y+1);
    
            //Main color
            g2.setColor(Color.white);
            g2.drawString(texto, x, y);
    
            // Logo jogo
            x = gp.screenWidth/2 - (gp.tileSize*2)/2;
            y += gp.tileSize*2;
            g2.drawImage(logo, x, y, gp.tileSize*2, gp.tileSize*2,null);
    
            //Menu
            g2.setFont(fonte20);
    
            texto = "|           Jogar           |";
            x = getXparaCentralizarTexto(texto);
            y += gp.tileSize *3;
            g2.drawString(texto, x, y);
            if(menuNum == 0){
                g2.drawString(">", x - gp.tileSize, y);
            }
    
            texto = "|             Sair            |";
            x = getXparaCentralizarTexto(texto);
            y += gp.tileSize;
            g2.drawString(texto, x, y);
            if(menuNum == 1){
                g2.drawString(">", x - gp.tileSize, y);
            }
        }
        else if (telaScreenState == 1) {
            // Tela de seleção
            g2.setColor(Color.WHITE);
            g2.setFont(fonte30);
        
            String texto = "Selecione seu Personagem";
            int x = getXparaCentralizarTexto(texto);
            int y = gp.tileSize * 3;
            g2.drawString(texto, x, y);
        
            // Alex
            g2.setFont(fonte20);
            texto = "Alex - GUERREIRO";
            x = getXparaCentralizarTexto(texto);
            y += gp.tileSize;
            g2.drawString(texto, x, y);
            if (gp.ui.menuNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        
            // Tharok
            g2.setFont(fonte20);
            texto = "Tharok - SOLDADO";
            x = getXparaCentralizarTexto(texto);
            y += gp.tileSize;
            g2.drawString(texto, x, y);
            if (gp.ui.menuNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        
            // Athea
            g2.setFont(fonte20);
            texto = "Athea - CURANDEIRA";
            x = getXparaCentralizarTexto(texto);
            y += gp.tileSize;
            g2.drawString(texto, x, y);
            if (gp.ui.menuNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        
            // Voltar
            g2.setFont(fonte20);
            texto = "|     Voltar    |";
            x = getXparaCentralizarTexto(texto);
            y += gp.tileSize;
            g2.drawString(texto, x, y);
            if (gp.ui.menuNum == 3) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        
            g2.setFont(fonte10);
            texto = "DICA: Se você ainda não conhece os comandos do jogo, clique ESC quando ele começar";
            x = getXparaCentralizarTexto(texto);
            y = gp.tileSize;
            g2.drawString(texto, x, y);

            int infoY = gp.screenHeigth - (gp.tileSize*2);
        
            if (gp.ui.menuNum == 0) {
                g2.setFont(fonte10);
                String historia = "História: Alex é um jovem guerreiro que sempre sonhou em se tornar um herói. ";
                String motivacao = "Motivação: Provar seu valor e proteger seu lar das garras de Zargath.";
        
                x = gp.tileSize;
                y = infoY;
                g2.drawString(historia, x, y);
                y += gp.tileSize;
                g2.drawString(motivacao, x, y);
            } else if (gp.ui.menuNum == 1) {
                g2.setFont(fonte10);
                String historia = "História: Tharok é um veterano de guerra, conhecido por sua bravura no campo de batalha. ";
                String motivacao = "Motivação: Defender seu reino e seu povo da destruição.";
        
                x = gp.tileSize;
                y = infoY;
                g2.drawString(historia, x, y);
                y += gp.tileSize;
                g2.drawString(motivacao, x, y);
            } else if (gp.ui.menuNum == 2) {
                g2.setFont(fonte10);
                String historia = "História: Athea é uma curandeira habilidosa, têm o poder de curar feridas físicas e emocionais.";
                String motivacao = "Motivação: Seu maior desejo é restaurar a esperança onde ela foi perdida.";
        
                x = gp.tileSize;
                y = infoY;
                g2.drawString(historia, x, y);
                y += gp.tileSize;
                g2.drawString(motivacao, x, y);
            }
        }        
          
    }

    

    public void drawTelaDePause(){

        g2.setFont(fonte40);
        g2.setColor(Color.WHITE);
        String texto = "PAUSED";
        int x = getXparaCentralizarTexto(texto);
        int y = gp.screenHeigth/6;

        g2.drawString(texto, x, y);
    }

    public void drawTelaDeDialogo(){
        //Window
        int x = gp.tileSize * 2;
        int y = gp.tileSize/ 2;
        int largura  = gp.screenWidth - (gp.tileSize*4);
        int altura = gp.tileSize*3;

        g2.setFont(fonte15);
        drawSubwindow(x, y, largura, altura);
        int textoX = x + gp.tileSize / 2;
        int textoY = y + gp.tileSize;

        for (String line : currentDialogo.split("\n")) {
            g2.drawString(line, textoX , textoY);
            textoY+= 40;
            
        }
    }

    public void drawPersonagemScreen(){
        //Cria a "janela/frame"
        final int frameX = gp.tileSize;
        final int frameY = gp.tileSize;
        final int frameLargura = gp.tileSize*5;
        final int frameAltura = gp.tileSize*10;

        drawSubwindow(frameX, frameY, frameLargura, frameAltura);

        //TEXTO
        g2.setColor(Color.WHITE);
        g2.setFont(fonte20);

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int alturaDaLinha = 35;

        //NOMES
        g2.drawString("Level", textX, textY);
        textY += alturaDaLinha;
        g2.drawString("Life", textX, textY);
        textY += alturaDaLinha;
        g2.drawString("Mana", textX, textY);
        textY += alturaDaLinha;
        g2.drawString("Força", textX, textY);
        textY += alturaDaLinha;
        g2.drawString("Destreza", textX, textY);
        textY += alturaDaLinha;
        g2.drawString("Ataque", textX, textY);
        textY += alturaDaLinha;
        g2.drawString("Defesa", textX, textY);
        textY += alturaDaLinha;
        g2.drawString("Exp", textX, textY);
        textY += alturaDaLinha;
        g2.drawString("Next Level", textX, textY);
        textY += alturaDaLinha;
        g2.drawString("Coin", textX, textY);
        textY += alturaDaLinha + 10;
        g2.drawString("Arma", textX, textY);
        textY += alturaDaLinha + 15;
        g2.drawString("Escudo", textX, textY);
        textY += alturaDaLinha;

        //VALORES 
        int tailX = (frameX + frameLargura) - 30;
        //Reset textY
        textY = frameY + gp.tileSize;
        String valor;

        valor = String.valueOf(gp.player.level);
        textX = getXparaAlinharTextoTexto(valor, tailX);
        g2.drawString(valor, textX, textY);
        textY += alturaDaLinha;

        valor = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXparaAlinharTextoTexto(valor, tailX);
        g2.drawString(valor, textX, textY);
        textY += alturaDaLinha;

        valor = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
        textX = getXparaAlinharTextoTexto(valor, tailX);
        g2.drawString(valor, textX, textY);
        textY += alturaDaLinha;

        valor = String.valueOf(gp.player.força);
        textX = getXparaAlinharTextoTexto(valor, tailX);
        g2.drawString(valor, textX, textY);
        textY += alturaDaLinha;

        valor = String.valueOf(gp.player.destreza);
        textX = getXparaAlinharTextoTexto(valor, tailX);
        g2.drawString(valor, textX, textY);
        textY += alturaDaLinha;

        valor = String.valueOf(gp.player.ataque);
        textX = getXparaAlinharTextoTexto(valor, tailX);
        g2.drawString(valor, textX, textY);
        textY += alturaDaLinha;

        valor = String.valueOf(gp.player.defesa);
        textX = getXparaAlinharTextoTexto(valor, tailX);
        g2.drawString(valor, textX, textY);
        textY += alturaDaLinha;

        valor = String.valueOf(gp.player.exp);
        textX = getXparaAlinharTextoTexto(valor, tailX);
        g2.drawString(valor, textX, textY);
        textY += alturaDaLinha;

        valor = String.valueOf(gp.player.proxLevelExp);
        textX = getXparaAlinharTextoTexto(valor, tailX);
        g2.drawString(valor, textX, textY);
        textY += alturaDaLinha;

        valor = String.valueOf(gp.player.coin);
        textX = getXparaAlinharTextoTexto(valor, tailX);
        g2.drawString(valor, textX, textY);
        textY += alturaDaLinha;

        g2.drawImage(gp.player.currentbArma.descida1, tailX - gp.tileSize, textY - 24, null);
        textY += gp.tileSize;
        g2.drawImage(gp.player.currentEscudo.descida1, tailX - gp.tileSize, textY -24, null);

    }

    public void drawInventory(){
        //Cria a "janela/frame"
        int frameX = gp.tileSize *9;
        int frameY = gp.tileSize;
        int frameLargura = gp.tileSize*6;
        int frameAltura = gp.tileSize*5;

        drawSubwindow(frameX, frameY, frameLargura, frameAltura);

        // SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize + 3;
        int slotsPerLargura = 5; // Número de slots por largura

        // Desenha itens do jogador
        for (int i = 0; i < gp.player.inventario.size(); i++) {

            // Equipa Cursor
            if (gp.player.inventario.get(i) == gp.player.currentbArma ||
                gp.player.inventario.get(i) == gp.player.currentEscudo) {
                    g2.setColor(new Color(240, 190, 90));
                    g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            
            }

            g2.drawImage(gp.player.inventario.get(i).descida1, slotX, slotY, null);
            slotX += slotSize;
            
            
            if ((i + 1) % slotsPerLargura == 0) {
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        // CURSOR
        int cursorX = slotXstart + (slotSize * slotColuna);
        int cursorY = slotYstart + (slotSize * slotLargura);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        // DRAW CURSOR
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

        // Descrição Frame
        int dFrameX = frameX;
        int dFrameY = frameY + frameAltura;
        int dFrameLargura = frameLargura;
        int dFrameAltura = gp.tileSize*3;


        // Desenha Descrição Texto
        int textX = dFrameX + 20;
        int textY = dFrameY + gp.tileSize;
        g2.setFont(fonte20);

        int itemIndex = getItemIndexOnSlot();
        if(itemIndex < gp.player.inventario.size()){
            drawSubwindow(dFrameX, dFrameY, dFrameLargura, dFrameAltura);
            for (String line : gp.player.inventario.get(itemIndex).descrição.split("\n")){
                
                g2.drawString(line, textX, textY);
                textY += 32;
            }
        }
    }

    public int getItemIndexOnSlot() {
        int itemIndex = (slotColuna + (slotLargura * 5));
        return itemIndex;
    }
    
    public void drawOpcoesSceen(){
        g2.setColor(Color.white);
        g2.setFont(fonte20);

        //  SUB WINDOW
        int frameX = gp.tileSize *6;
        int frameY = gp.tileSize;
        int frameLargura = gp.tileSize*8;
        int frameAltura = gp.tileSize*10;

        drawSubwindow(frameX, frameY, frameLargura, frameAltura);

        switch (subState) {
            case 0:
                opcoesTopo(frameX, frameY);
                break;
            case 1:
                opcoesControle(frameX, frameY);
                break;
            
            
        
        }
    } 

    public void opcoesTopo(int frameX, int frameY){

        int textX;
        int textY;

        textX = frameX + gp.tileSize;
        textY = gp.tileSize*4;
        g2.drawString("Controles", textX, textY);
        if(comandoNum == 0){
            g2.drawString(">", textX -25 , textY);
            if(gp.keyH.enterPressed == true){
                subState = 1;
            }
        }

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Mudar de Personagem", textX, textY);
        if(comandoNum == 1){
            g2.drawString(">", textX -25 , textY);
            if(gp.keyH.enterPressed == true){
                gp.gameState = gp.menuState;
            }
        }

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Sair do Jogo", textX, textY);
        if(comandoNum == 2){
            g2.drawString(">", textX -25 , textY);
            if(gp.keyH.enterPressed == true){
                System.exit(0);
            }
        }

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Voltar", textX, textY);
        if(comandoNum == 3){
            g2.drawString(">", textX -25 , textY);
            if(gp.keyH.enterPressed == true){
                gp.gameState = gp.playState;
            }
        }

    }

    public void opcoesControle(int frameX, int frameY){
        int textX;
        int textY;

        //Titulo
        String texto = "CONTROLE";
        textX = getXparaCentralizarTexto(texto);
        textY = frameY + gp.tileSize * 2;
        g2.drawString(texto, textX, textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Mover", textX, textY); textY += gp.tileSize;
        g2.drawString("Confirma/Ataque", textX, textY); textY += gp.tileSize;
        g2.drawString("Atirar/Lançar", textX, textY); textY += gp.tileSize;
        g2.drawString("Personagem Screen", textX, textY); textY += gp.tileSize;
        g2.drawString("Pause", textX, textY); textY += gp.tileSize;

        textX = frameX + gp.tileSize*6;
        textY = frameY + gp.tileSize*3;
        g2.drawString("WASD/SETAS", textX - 45, textY); textY += gp.tileSize;
        g2.drawString("ENTER", textX, textY); textY += gp.tileSize;
        g2.drawString("F", textX, textY); textY += gp.tileSize;
        g2.drawString("ESPAÇO", textX, textY); textY += gp.tileSize;
        g2.drawString("ESC", textX, textY); textY += gp.tileSize;

        //VOLTAR
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize * 9;
        g2.drawString("Voltar - ESC", textX, textY);
        if(comandoNum == 0){
            g2.drawString(">", textX -25 , textY);
            if(gp.keyH.enterPressed == true){
                //gp.gameState = gp.pauseState;
            }
        }
        
    }

    public void drawSubwindow(int x, int y, int largura, int altura){
            Color c = new Color(0,0,0, 210);
            g2.setColor(c);
            g2.fillRoundRect(x, y, largura, altura,35 ,35);

            c = new Color(255,255, 255);
            g2.setColor(c);
            g2.setStroke(new BasicStroke(5));
            g2.drawRoundRect(x+5, y+5,largura - 10, altura - 10, 25, 25);
    }

    public int getXparaCentralizarTexto(String texto){
        int length = (int)g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;    
    }

    public int getXparaAlinharTextoTexto(String texto, int tailX){
        int length = (int)g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
        int x =tailX- length;
        return x;    
    }


}
