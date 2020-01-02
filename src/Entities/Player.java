package Entities;

import main.Game;
import main.Handler;
import main.ID;

import java.awt.*;
import java.util.ArrayList;

public class Player extends GameObject {
	private boolean isCollided;
	ArrayList<GameObject> gameObj;

	public Player(int x, int y, ID id) {
		super(x, y, id);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		checkWallCollision();
		otherCol();
	}

	@Override
	public void render(Graphics graphics) {
		graphics.setColor(Color.black);
		graphics.fillRect(x, y, 32, 32);
	}

	private void otherCol() {
		for (GameObject obj : Handler.gameObjects) {

			if (obj.id != ID.Player) {

				collisionManagement(obj);
			}

		}
	}

	int height = 32;
	int width = 32;

	
	private void collisionManagement(GameObject gameObject) {
		if(checkCollision(gameObject)) {
			if(x < gameObject.x + width && x + width > gameObject.x ) {
				velX = -velX;

			}else if(y < gameObject.y + height && y + height > gameObject.y){
				velY = -velY;
			}
		}
	}
	
	private boolean checkCollision(GameObject gameObject) {
		if (x < gameObject.x + width && x + width > gameObject.x && y < gameObject.y + height
				&& y + height > gameObject.y) {
			return true;
		}
		return false;
	}

	private void checkWallCollision() {
		if (x < 0) {
			x = 0;
			velX = -velX;
		}
		if (x > Game.WIDTH_ - 30) {
			x = Game.WIDTH_ - 30;
			velX = -velX;
		}
		if (y < 0) {
			y = 0;
			velY = -velY;
		}
		if (y > Game.HEIGHT_ - 30) {
			y = Game.HEIGHT_ - 30;
			velY = -velY;
		}
	}

}
