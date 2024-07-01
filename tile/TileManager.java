package tile;

import main.GamePanel;
import main.UtilsFerramentas;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class TileManager {
    // Atributos
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldColuna][gp.maxWorldLargura];

        getTileImage();
        loadMap("/resources/maps/world01.txt");
    }


    public void getTileImage() {
            setup(0, "chao", false);
            setup(1, "pedra", true);
            setup(2, "lava", false);
            setup(3, "gelo", false);
            setup(4, "tijolo", true);
            setup(5, "pedraComGrama", true);
    }

    public void setup (int index, String imageName, boolean colisao){
        UtilsFerramentas utilsF = new UtilsFerramentas();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/"+ imageName +".jpg"));
            tile[index].image = utilsF.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].colisao = colisao;
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            if (is == null) {
                System.out.println("Arquivo de mapa não encontrado: " + filePath);
                return;
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
    
            for (int largura = 0; largura < gp.maxWorldLargura; largura++) {
                String line = br.readLine();
                for (int coluna = 0; coluna < gp.maxWorldColuna; coluna++) {
                    char numChar = line.charAt(coluna);
                    int num = Character.getNumericValue(numChar);
                    mapTileNum[coluna][largura] = num;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public void draw(Graphics2D g2) {
        for (int worldColuna = 0; worldColuna < gp.maxWorldColuna; worldColuna++) {
            for (int worldLargura = 0; worldLargura < gp.maxWorldLargura; worldLargura++) {
                int tileNum = mapTileNum[worldColuna][worldLargura];
    
                int worldX = worldColuna * gp.tileSize;
                int worldY = worldLargura * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;
    
                //Desenha apenas os tiles visíveis na tela
                if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
    
                    g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }

            }
        }
    }
    
}

