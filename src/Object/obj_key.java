package Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class obj_key extends SuperObject{
    public obj_key()  {
        name = "key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Objects_sprites/key.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        collision = true;
    }




}
