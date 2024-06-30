package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import objeto.OBJ_Chave;


public class UserInterface {
    GamePanel gp;
    Font fonte20, fonte80;
    BufferedImage chaveImagem;
    public boolean messageOn = false;
    public String message = "";
    public boolean fimDeJogo = false;
    private int contadorMessage = 0;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.000");

    public UserInterface(GamePanel gp) {
        this.gp = gp;
        this.fonte20 = new Font("Times New Roman", Font.BOLD, 20 ); //Definir a fonte depois
        this.fonte80 = new Font("Times New Roman", Font.BOLD, 80);

        OBJ_Chave chave = new OBJ_Chave(gp);
        chaveImagem = chave.image;
    }

    public void showMessage(String texto){
        this.message = texto;
        this.messageOn = true;
    }

    public void draw(Graphics2D g2){

        if(fimDeJogo == true){
            String texto;
            int textoLength;
            int x, y;

            g2.setFont(fonte20); 
            g2.setColor(Color.white);
            texto = "VOCÊ ACABOU COM O MONSTRO E ENCONTROU O TESOURO!";
            textoLength = (int) g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
            x = gp.screenWidth/2 - textoLength/2;
            y = gp.screenHeigth/2 - (gp.tileSize*5);
            g2.drawString(texto, x, y);

            g2.setFont(fonte20); 
            g2.setColor(Color.white);
            texto = "Time final: " + dFormat.format(playTime) + "segundos!";
            textoLength = (int) g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
            x = gp.screenWidth/2 - textoLength/2;
            y = gp.screenHeigth/2 - (gp.tileSize*4);
            g2.drawString(texto, x, y);

            g2.setFont(fonte80); 
            g2.setColor(Color.yellow);
            texto = "VENCEU!";
            textoLength = (int) g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
            x = gp.screenWidth/2 - textoLength/2;
            y = gp.screenHeigth/2 + (gp.tileSize*2);
            g2.drawString(texto, x, y);

            gp.gameThread = null;

        } 
        else {
            g2.setFont(fonte20); 
            g2.setColor(Color.WHITE);
            g2.drawImage( chaveImagem, gp.tileSize/2,gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.temChave, 74, 65 );

            //Time
            playTime += (double)1/60;
            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize*13, 65);

        //Message
            if(messageOn == true){
              g2.setFont(g2.getFont().deriveFont(20F));
              g2.drawString(message, gp.tileSize/2, gp.tileSize/2);

              contadorMessage++;

              if(contadorMessage > 120){
                contadorMessage = 0;
                messageOn = false;
              }
            }
        }
    }

}
