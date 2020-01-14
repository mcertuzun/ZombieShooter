package Entities;

import images.LoadResource;
import main.Game;
import main.Handler;
import main.ID;

import javax.swing.*;
import java.awt.*;

public class Player extends GameObject {
	protected JPanel panel;
	private int lineX1, lineY1, lineX2, lineY2;

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
	public void render(Graphics2D graphics) {
		graphics.drawImage(LoadResource.player, x, y, width,height,null);


		if (isAiming){
			aimLine(graphics);
		}

	}

	private void aimLine(Graphics2D graphics) {
		float[] dashingPattern1 = {20f, 20f};
		Stroke stroke = new BasicStroke(5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, dashingPattern1, 2.0f);
		graphics.setStroke(stroke);
		graphics.setColor(Color.red);
		graphics.drawLine(lineX1, lineY1, lineX1 + (lineX1-lineX2)*2 ,lineY1 + (lineY1- lineY2)*2);
	}

	@Override
	public void drawLine(Point pointA, Point pointB) {
		this.lineX1 = pointA.x;
		this.lineY1 = pointA.y;
		this.lineX2 = pointB.x;
		this.lineY2 = pointB.y;
	}

	private void collisionHandler() {
		checkWallCollision(this);
		for (GameObject obj : Handler.gameObjects) {
			if (obj.id != ID.Player && obj.id != ID.UI) {
				checkObjectCollision(this, obj);
			}
			if (checkCollision(this, obj)) {
				if (obj.id == ID.Target) {
					panel.setVisible(true);
					Game.isNext =true;
				}

			}
		}
	}

}
