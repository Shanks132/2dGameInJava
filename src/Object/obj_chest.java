package Object;

import javax.imageio.ImageIO;

public class obj_chest extends SuperObject{
    public obj_chest()  {
        name = "chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Objects_sprites/chest.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        collision = true;
    }
}
