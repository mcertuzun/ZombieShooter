package images;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoadResource {
    public static BufferedImage restartButton;
    public static BufferedImage player;
    public static BufferedImage wall;
    public static BufferedImage background;
    public static BufferedImage enemy;
    public LoadResource() {
        File path = new File("src/images/");
        BufferedImage img = null;
        try {
            restartButton = ImageIO.read(new File(path, "restart.png"));
            wall = ImageIO.read(new File(path, "wall.png"));
            player= ImageIO.read(new File(path, "player.png"));
            background = ImageIO.read(new File(path, "background.png"));
            enemy = ImageIO.read(new File(path, "enemy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
