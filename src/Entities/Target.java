package Entities;

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
    public void render(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.fillRect(x, y, width, height);
    }
}
