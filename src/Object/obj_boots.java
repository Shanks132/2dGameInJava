package Object;

import javax.imageio.ImageIO;

public class obj_boots extends SuperObject{
    public obj_boots()  {
        name = "boots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Objects_sprites/boots.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
