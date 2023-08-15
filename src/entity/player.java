package entity;

import Main.GamePanel;
import Main.keyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class player extends Entity{
    GamePanel gp;
    keyHandler keyH;
    public final int screenX;  // where player is on the screen so now the player will be ons the same spot and
    public final int screenY; // we change the map in the back
    int hasKey = 0; //how many keys' player has


    public player(GamePanel gp , keyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth/2 -(gp.tileSize/2) ;
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8,16, 24,24);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {
        worldy = gp.tileSize * 20;  // these are players position in world map
        worldx = gp.tileSize * 23;
        speed = 4;
        direction = "down";
    }

        public void getPlayerImage(){ //loads images into set variables
            try{

                up1 = ImageIO.read(getClass().getResourceAsStream("/player_sprites/boy_up_1.png"));
                up2 = ImageIO.read(getClass().getResourceAsStream("/player_sprites/boy_up_2.png"));
                down1 = ImageIO.read(getClass().getResourceAsStream("/player_sprites/boy_down_1.png"));
                down2 = ImageIO.read(getClass().getResourceAsStream("/player_sprites/boy_down_2.png"));
                left1 = ImageIO.read(getClass().getResourceAsStream("/player_sprites/boy_left_1.png"));
                left2 = ImageIO.read(getClass().getResourceAsStream("/player_sprites/boy_left_2.png"));
                right1 = ImageIO.read(getClass().getResourceAsStream("/player_sprites/boy_right_1.png"));
                right2 = ImageIO.read(getClass().getResourceAsStream("/player_sprites/boy_right_2.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
    }


    public void update(){
        // in java the upper left corner of the screen is 0, 0
        //X increases as they go right
        // y increases as they go downwards

        //if one the keys is pressed only then will the animations start changing
        //else the animations will be as last set
        // default animation set above as down
        if(keyH.downPressed|| keyH.upPressed || keyH.leftPressed || keyH.rightPressed ){
            if (keyH.upPressed == true){
                direction = "up";

           }
            if (keyH.downPressed == true){
                direction = "down";

            }
            if (keyH.leftPressed== true){
                direction = "left";

            }
            if (keyH.rightPressed == true){
                direction = "right";

            }
            //object collision


            // check tile collision, if collision is false player can move

            collisinOn = false;
            gp.cChecker.checkTile(this);
            int ObjIndex = gp.cChecker.checkObject(this,true);
            pickUpObject(ObjIndex);
            // i.e. after every 15 frames sprite num will change which changes corresponding
            // animation in the walking direction. Spite counter goes up with each frame
            if (collisinOn == false){
                switch (direction){
                    case "up":worldy -= speed;break;
                    case "down":worldy += speed;break;
                    case "left":worldx -= speed;break;
                    case "right":worldx += speed;break;
                }
            }
            spriteCounter++;
            if (spriteCounter>15){
                if (spriteNum == 1){
                    spriteNum = 2;
                }
                else if (spriteNum == 2){
                    spriteNum = 1 ;
                }
                spriteCounter = 0;
            }
        }
    }
    public void pickUpObject(int index){
        if(index != 999){
            String objectName = gp.obj[index].name;
            switch (objectName){
                case "key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[index] = null;
                    System.out.println("Keys:" + hasKey);
                    break;
                case "door":
                    gp.playSE(3);
                    if (hasKey > 0) {
                        hasKey--;
                        System.out.println("Keys:" + hasKey);
                        gp.obj[index] = null;
                    }
                    break;
                case "boots":
                    gp.playSE(2);
                    speed += 2;
                    gp.obj[index] = null;
                    break;


            }
        }

    }
    public void draw(Graphics2D g2){
        /*
        g2.setColor(Color.white);
        g2.fillRect(x,y,tileSize,tileSize);
         */

        BufferedImage image = null;
        switch (direction) {
            //let's say direction is up and spriteNum(sprite state lets say is "up" and 1
            //then up 1 image is shown
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            }
        }
        g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);
    }

}
