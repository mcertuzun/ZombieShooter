package Entities;

import main.Handler;
import main.ID;

import javax.swing.*;
import java.awt.*;

public class Player extends GameObject {
	JPanel panel;

	public Player(int x, int y, ID id, int height, int width) {
		super(x, y, id, height, width);
		panel = new JPanel();
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		collisionHandler();
	}

	@Override
	public void render(Graphics graphics) {
		graphics.setColor(Color.black);
		graphics.fillOval(x, y, width, height);
	}

	private void collisionHandler() {
		checkWallCollision(this);
		for (GameObject obj : Handler.gameObjects) {
			if (obj.id != ID.Player) {
				checkObjectCollision(this, obj);
			}
			if (checkCollision(this, obj)) {
				if (obj.id == ID.Target) {
					panel.setVisible(true);
					JOptionPane.showMessageDialog(panel, "You did it");
				}

			}
		}
	}

}
