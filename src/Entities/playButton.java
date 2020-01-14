package Entities;

import com.sun.tools.javac.Main;
import images.LoadResource;
import main.ID;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class playButton extends GameObject {
    Font font;
    public playButton(int x, int y, ID id, int height, int width) {
        super(x, y, id, height, width);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics2D graphics) {


        graphics.setFont(LoadResource.font.deriveFont(130F));
        graphics.setColor(Color.black);
        graphics.drawString("Hit the Zombie", x-250, y-200);

        graphics.drawImage(LoadResource.playButton, x, y, 170,170,null);
    }
}
