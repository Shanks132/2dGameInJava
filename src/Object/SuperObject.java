package Object;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int WorldX,WorldY;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;


    public void draw(Graphics2D g2, GamePanel gp){
        int screenX = WorldX - gp.player.worldx + gp.player.screenX;
        int screenY = WorldY - gp.player.worldy + gp.player.screenY;
        //next line is to make sure that we don't draw tiles that are outside the render distance and save resources
        if(     (WorldX + gp.tileSize > gp.player.worldx - gp.player.screenX) &&
                (WorldX - gp.tileSize < gp.player.worldx + gp.player.screenX) &&
                (WorldY + gp.tileSize > gp.player.worldy - gp.player.screenY) &&
                (WorldY - gp.tileSize < gp.player.worldy + gp.player.screenY))
        {
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

}
