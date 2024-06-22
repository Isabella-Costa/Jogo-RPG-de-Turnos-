package tile;

import main.GamePanel;

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
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/terra.jpg"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/pedra.jpg"));
            tile[1].colisao = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/lava.jpg"));


            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/gelo.jpg"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/tijolo.jpg"));
            tile[4].colisao = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/pedraComGrama.jpg"));
            tile[5].colisao = true;

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

