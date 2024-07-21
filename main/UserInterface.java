package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import objeto.OBJ_Vida;
import entity.*;

public class UserInterface {
    GamePanel gp;
    Graphics2D g2;
    Font fonte15, fonte20, fonte30 ,fonte40, fonte80;
    Font fonteTitulo;
    BufferedImage coracaoFull, coracaoMetade, coracaoVazio;
    public boolean messageOn = false;
    public String message = "";
    public boolean fimDeJogo = false;
    public String currentDialogo = "";
    Image logo;
    public int menuNum = 0;
    public int telaScreenState = 0; // 0 - primeira tela, 1 - segunda tela..  -- 2 - batalha 
    //private int contadorMessage = 0;

    public UserInterface(GamePanel gp) {
        this.gp = gp;
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

    }

    public void showMessage(String texto){
        this.message = texto;
        this.messageOn = true;
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
        }
        // Pause State
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawTelaDePause();
        }

        // Dialogo State
        if(gp.gameState == gp.dialogoState){
            drawPlayerLife();
            drawTelaDeDialogo();
        }
        //  Personagem State
        if(gp.gameState == gp.personagemState){
            drawPersonagemScreen();
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
    
            texto = "|   Carregar Jogo   |";
            x = getXparaCentralizarTexto(texto);
            y += gp.tileSize;
            g2.drawString(texto, x, y);
            if(menuNum == 1){
                g2.drawString(">", x - gp.tileSize, y);
            }
    
            texto = "| História do Jogo |";
            x = getXparaCentralizarTexto(texto);
            y += gp.tileSize;
            g2.drawString(texto, x, y);
            if(menuNum == 2){
                g2.drawString(">", x - gp.tileSize, y);
            }
    
            texto = "|             Sair            |";
            x = getXparaCentralizarTexto(texto);
            y += gp.tileSize;
            g2.drawString(texto, x, y);
            if(menuNum == 3){
                g2.drawString(">", x - gp.tileSize, y);
            }
        }
        else if (telaScreenState == 1){
            //Tela de seleção
            g2.setColor(Color.WHITE);
            g2.setFont(fonte30);

            String texto = "Selecione seu Personagem";
            int x = getXparaCentralizarTexto(texto);
            int y = gp.tileSize*3;
            g2.drawString(texto, x, y);
            
            g2.setFont(fonte20);
            texto = "Alex - GUERREIRO";
            x = getXparaCentralizarTexto(texto);
            y += gp.tileSize;
            g2.drawString(texto, x, y);
            if(menuNum == 0){
                g2.drawString(">", x - gp.tileSize, y);
            }
            
            // Colocar a foto dos personagens embaixo de cada
            
            g2.setFont(fonte20);
            texto = "Tharok - SOLDADO";
            x = getXparaCentralizarTexto(texto);
            y += gp.tileSize;
            g2.drawString(texto, x, y);
            if(menuNum == 1){
                g2.drawString(">", x - gp.tileSize, y);
            }

            g2.setFont(fonte20);
            texto = "|     Voltar    |";
            x = getXparaCentralizarTexto(texto);
            y += gp.tileSize;
            g2.drawString(texto, x, y);
            if(menuNum == 2){
                g2.drawString(">", x - gp.tileSize, y);
            }

        }
        
    }
    

    public void drawTelaDePause(){

        g2.setFont(fonte40);
        g2.setColor(Color.WHITE);
        String texto = "PAUSED";
        int x = getXparaCentralizarTexto(texto);
        int y = gp.screenHeigth/2;

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
        textY += alturaDaLinha + 20;
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

        g2.drawImage(gp.player.currentbArma.descida1, tailX - gp.tileSize, textY - 15, null);
        textY += gp.tileSize;
        g2.drawImage(gp.player.currentEscudo.descida1, tailX - gp.tileSize, textY -15, null);

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
