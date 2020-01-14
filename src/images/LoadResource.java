package images;

import com.sun.tools.javac.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LoadResource {
    public static BufferedImage restartButton;
    public static BufferedImage player;
    public static BufferedImage wall;
    public static BufferedImage background;
    public static BufferedImage enemy;
    public static BufferedImage playButton;
    public static Font font;
    public LoadResource() {
        File path = new File("src/images/");
        File pathFont = new File("src/fonts");

        BufferedImage img = null;
        try {
            restartButton = ImageIO.read(new File(path, "restart.png"));
            wall = ImageIO.read(new File(path, "wall.png"));
            player= ImageIO.read(new File(path, "player.png"));
            background = ImageIO.read(new File(path, "background.png"));
            enemy = ImageIO.read(new File(path, "enemy.png"));
            playButton =ImageIO.read(new File(path, "playButton.png"));
            InputStream is = Main.class.getResourceAsStream( pathFont+ "cloud.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, new File(pathFont, "Gom.otf"));

        } catch (IOException e) {
            e.printStackTrace();
        }
       catch (FontFormatException e) {
            e.printStackTrace();
        }
    }
}
