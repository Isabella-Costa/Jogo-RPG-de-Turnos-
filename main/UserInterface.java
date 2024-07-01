package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;



public class UserInterface {
    GamePanel gp;
    Graphics2D g2;
    Font fonte20, fonte80;
    //BufferedImage chaveImagem;
    public boolean messageOn = false;
    public String message = "";
    public boolean fimDeJogo = false;
    //private int contadorMessage = 0;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.000");

    public UserInterface(GamePanel gp) {
        this.gp = gp;
        this.fonte20 = new Font("Times New Roman", Font.BOLD, 20 ); //Definir a fonte depois
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

        if(gp.gameState == gp.playState){
            //fzr as coisas dos jogadores mais tarde
        }
        if(gp.gameState == gp.pauseState){
            drawTelaDePause();
        }

    }

    public void drawTelaDePause(){

        g2.setFont(fonte80);
        String texto = "PAUSED";
        int x = getXparaCentralizarTexto(texto);
        int y = gp.screenHeigth/2;

        g2.drawString(texto, x, y);
    }

    public int getXparaCentralizarTexto(String texto){
        int length = (int)g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;    
    }

}
