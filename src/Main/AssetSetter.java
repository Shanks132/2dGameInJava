package Main;
import Object.*;

import java.io.IOException;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject() throws IOException {
        gp.obj[0] = new obj_key();
        gp.obj[0].WorldX = 23 * gp.tileSize;
        gp.obj[0].WorldY = 7 * gp.tileSize;

        gp.obj[1] = new obj_key() ;
        gp.obj[1].WorldX = 23 * gp.tileSize;
        gp.obj[1].WorldY = 40 * gp.tileSize;

        gp.obj[2] = new obj_key() ;
        gp.obj[2].WorldX = 37 * gp.tileSize;
        gp.obj[2].WorldY = 7 * gp.tileSize;

        gp.obj[3] = new obj_door() ;
        gp.obj[3].WorldX = 10 * gp.tileSize;
        gp.obj[3].WorldY = 11 * gp.tileSize;

        gp.obj[4] = new obj_door() ;
        gp.obj[4].WorldX = 8 * gp.tileSize;
        gp.obj[4].WorldY = 28 * gp.tileSize;

        gp.obj[5] = new obj_door() ;
        gp.obj[5].WorldX = 12 * gp.tileSize;
        gp.obj[5].WorldY = 22 * gp.tileSize;

        gp.obj[6] = new obj_chest() ;
        gp.obj[6].WorldX = 10 * gp.tileSize;
        gp.obj[6].WorldY = 7 * gp.tileSize;

        gp.obj[7] = new obj_boots() ;
        gp.obj[7].WorldX = 37 * gp.tileSize;
        gp.obj[7].WorldY = 42 * gp.tileSize;



    }

}
