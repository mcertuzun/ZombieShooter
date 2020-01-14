package main;

import org.ietf.jgss.GSSManager;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {

    public Window(int width, int height, String title,Game game){
        JFrame window = new JFrame(title);


        window.setPreferredSize(new Dimension(width,height));
        window.setMaximumSize(new Dimension(width,height));
        window.setMinimumSize(new Dimension(width,height));

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);

        window.add(game);
        window.setVisible(true);
        game.start();
    }
}
