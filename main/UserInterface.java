package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;




public class UserInterface {
    GamePanel gp;
    Graphics2D g2;
    Font fonte15, fonte20, fonte80;
    //BufferedImage chaveImagem;
    public boolean messageOn = false;
    public String message = "";
    public boolean fimDeJogo = false;
    public String currentDialogo = "";
    //private int contadorMessage = 0;

    public UserInterface(GamePanel gp) {
        this.gp = gp;
        this.fonte20 = new Font("Times New Roman", Font.BOLD, 20 ); //Definir a fonte depois
        this.fonte15 = new Font("Times New Roman", Font.BOLD, 15 );
        this.fonte80 = new Font("Times New Roman", Font.BOLD, 80);

        //OBJ_Chave chave = new OBJ_Chave(gp);
        //chaveImagem = chave.image;
    }

    public void showMessage(String texto){
        this.message = texto;
        this.messageOn = true;
    }

    public void draw(Graphics2D g2){

        this.g2 = g2;

        g2.setFont(fonte20);
        g2.setColor(Color.WHITE);
        
        // Play State
        if(gp.gameState == gp.playState){
            //fzr as coisas dos jogadores mais tarde
        }
        // Pause State
        if(gp.gameState == gp.pauseState){
            drawTelaDePause();
        }

        // Dialogo State
        if(gp.gameState == gp.dialogoState){
            drawTelaDeDialogo();
        }
    

    }

    public void drawTelaDePause(){

        g2.setFont(fonte80);
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

}
