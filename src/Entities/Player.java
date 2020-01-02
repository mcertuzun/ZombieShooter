package Entities;

import main.Game;
import main.Handler;
import main.ID;

import java.awt.*;

public class Player extends GameObject {
    private boolean isCollided;
    public Player(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        checkWallCollision();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillRect(x,y,32,32);
    }


    private void checkWallCollision() {
        if (x < 0) {
            x = 0;
            velX = -velX;
        }
        if (x > Game.WIDTH_-30) {
            x = Game.WIDTH_-30;
            velX = -velX;
        }
        if (y < 0) {
            y = 0;
            velY = -velY;
        }
        if (y > Game.HEIGHT_-30) {
            y = Game.HEIGHT_-30;
            velY = -velY;
        }
    }


}
