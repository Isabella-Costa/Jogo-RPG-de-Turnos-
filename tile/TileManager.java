package tile;

import main.GamePanel;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class TileManager {
    //Atributos
    GamePanel gp;
    Tile[] tile;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenColuna][gp.maxScreenLargura];

        getTileImage();
    }


    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/areia.jpg"));
    
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/pedra.jpg"));
    
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/agua.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(){
        try {
            InputStream is = getClass().getResourceAsStream("resources/maps/map01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int coluna = 0;
            int largura = 0;

            while(coluna  < gp.maxScreenColuna && largura < gp.maxScreenLargura){
                String line = br.readLine();

                while(coluna  < gp.maxScreenColuna){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[coluna]);
                }
            }


            
        } catch (Exception e) {
          
        }
    }
    
    

    public void draw(Graphics2D g2){

     
      int coluna = 0;
      int largura = 0;
      int x = 0;
      int y = 0;


      while(coluna < gp.maxScreenColuna && largura < gp.maxScreenLargura){

        g2.drawImage( tile[0].image, x, y, gp.tileSize, gp.tileSize, null);
        coluna++;
        x +=gp.tileSize;

        if(coluna == gp.maxScreenColuna){
            coluna = 0;
            x = 0;
            largura++;
            y += gp.tileSize;
        }


      }


    }


    
}
