package Main;

import Tiles.tileManager;
import entity.player;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import Object.*;

public class GamePanel extends JPanel implements Runnable{

    //Screen Settings


    public final int originalTileSize = 16 ;   //16*16 tile
    public final int scale = 3 ;
    public final int tileSize = originalTileSize * scale ; //48*48 tiles

    public final int maxScreenCol = 16 ;
    public final int maxScreenRow = 12 ;

    public final int screenWidth = tileSize * maxScreenCol; // 16*3*16 =768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 16*3*12 = 576 pixels
    //WORLD MAP SETTINGS
    public final int maxWorldCol   = 50;
    public final int maxWorldRow   = 50;
   // public final int maxWorldWidth   = tileSize * maxWorldCol;
   // public final int maxWorldHeight   = tileSize * maxWorldRow;



    //setting FPS
    double fps = 60;

    keyHandler keyH = new keyHandler();
    Thread gameThread;
    tileManager tileM = new tileManager(this);
    sound sound = new sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public SuperObject[] obj = new SuperObject[10]; // 10 object slots prepared for objects such as keys and all that
    //up to 10 objects can be displayed at the same time and replaced later
    public AssetSetter aSetter = new AssetSetter(this);
    public player player = new player(this,keyH);
    //set player's position

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH); //checks what keyboard keys are pressed and released
        this.setFocusable(true);
    }
     public void setupObjects() throws IOException {
        aSetter.setObject();

        playMusic(0); //  main theme
     }

    public void startGameThread(){
        gameThread = new Thread(this); // passes this class to this thread
        gameThread.start();
    }
    /*
    @Override
    public void run(){  // we will create game loop inside this

        double drawInterval = 1000000000/fps;   //draws the screen every = 0.01666667 seconds
        double nextDrawTime = System.nanoTime()+drawInterval;

        while(gameThread != null){
            System.out.println(System.nanoTime());
            // update 1.update camera position
            // 2. draw screen with updated position
            update();
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000d; //converts  nanoseconds to milliseconds
                if (remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep( (long)remainingTime);
                nextDrawTime += drawInterval ;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }*/
    public void run(){
        double drawInterval = 1000000000/fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime ;
        long timer = 0 ;
        int drawCount = 0;
        while(gameThread != null){
            currentTime = System.nanoTime();
            delta +=( currentTime-lastTime)/drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;
            if (delta>= 1){
                update();
                repaint();
                delta --;
                drawCount++;
            }
            if (timer >= 1000000000){
                //System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){
        player.update();

    }
    public void paintComponent(Graphics g){  //standard method to draw on jpanel
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g ;
        //TILE DRAWING
        tileM.draw(g2);

        //OBJECT DRAWING
        for(int i = 0 ; i< obj.length ; i++){
            if(obj[i] != null){
                obj[i].draw(g2,this);
            }
        }
        //PLAYER DRAWING
        player.draw(g2);
        g2.dispose();
    }
    public void playMusic(int i ){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }
    public void stopMusic(int i){
        sound.stop();
    }



}
