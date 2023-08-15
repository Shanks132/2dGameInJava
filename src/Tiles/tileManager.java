package Tiles;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class tileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];


    public tileManager(GamePanel gp){
        this.gp = gp;
        this.tile = new Tile[10];
        mapTileNum = new int [gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/World01.txt");
    }


    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/TilePics/grass00.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/TilePics/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/TilePics/water00.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/TilePics/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/TilePics/tree.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/TilePics/road00.png"));
             
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;

            while (col<gp.maxWorldCol && row<gp.maxWorldRow){
                String line = br.readLine();

                while (col<gp.maxWorldCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void draw(Graphics2D g2){
        /*
        g2.drawImage(tile[0].image,0,0,gp.tileSize,gp.tileSize,null);
        g2.drawImage(tile[1].image,48,0,gp.tileSize,gp.tileSize,null);
        g2.drawImage(tile[2].image,96,0,gp.tileSize,gp.tileSize,null);
         */
        int WorldCol = 0;
        int WorldRow = 0;
        while(WorldCol< gp.maxWorldCol && WorldRow < gp.maxWorldRow){
            int tileNum = mapTileNum[WorldCol][WorldRow];
            int WorldX = WorldCol * gp.tileSize;
            int WorldY = WorldRow * gp.tileSize;
            int screenX = WorldX - gp.player.worldx + gp.player.screenX;
            int screenY = WorldY - gp.player.worldy + gp.player.screenY;
            //next line is to make sure that we don't draw tiles that are outside the render distance and save resources
            if(     (WorldX + gp.tileSize > gp.player.worldx - gp.player.screenX) &&
                    (WorldX - gp.tileSize < gp.player.worldx + gp.player.screenX) &&
                    (WorldY + gp.tileSize > gp.player.worldy - gp.player.screenY) &&
                    (WorldY - gp.tileSize < gp.player.worldy + gp.player.screenY))
            {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            WorldCol++;
            if(WorldCol == gp.maxWorldCol) {
                WorldCol = 0;
                WorldRow++;
            }
        }
    }
}

