package Entities;

import images.LoadResource;
import main.ID;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class restartButton extends GameObject {

    public restartButton(int x, int y, ID id, int height, int width) {
        super(x, y, id, height, width);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.drawImage(LoadResource.restartButton, x+10, y+25, 60,60,null);
    }
}
