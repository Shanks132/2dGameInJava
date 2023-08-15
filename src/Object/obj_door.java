package Object;

import javax.imageio.ImageIO;

public class obj_door extends SuperObject {
    public obj_door()  {
        name = "door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Objects_sprites/door.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        collision = true;
    }
}
