package Entities;

import images.LoadResource;
import main.ID;

import java.awt.*;

public class Target extends GameObject {

    public Target(int x, int y, ID id, int height, int width) {
        super(x, y, id, height, width);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.drawImage(LoadResource.enemy, x, y, width,height,null);
    }

    @Override
    public void drawLine(Point pointA, Point pointB) {

    }
}
