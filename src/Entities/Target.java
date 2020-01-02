package Entities;

import main.ID;

import java.awt.*;

public class Target extends GameObject {

    public Target(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.fillRect(x,y,32,32);
    }
}
