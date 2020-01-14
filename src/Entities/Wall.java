package Entities;

import images.LoadResource;
import main.ID;

import java.awt.*;

public class Wall extends GameObject {

    public Wall(int x, int y, ID id, int height, int width) {
        super(x, y, id, height, width);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.drawImage(LoadResource.wall, x, y, width,height,null);


    }
}
