package Physic;

import Entities.GameObject;
import main.Game;

public class CollisionManager implements ICollision {

    @Override
    public void checkWallCollision(GameObject gameObject) {
        if (gameObject.getX() < 0 || gameObject.getX() > Game.WIDTH_ - gameObject.width) {
            gameObject.setVelX(-gameObject.getVelX());
        }
        if (gameObject.getY() < 0 || gameObject.getY() > Game.HEIGHT_ - gameObject.height) {
            gameObject.setVelY(-gameObject.getVelY());
        }
    }

    public boolean checkCollision(GameObject firstObject, GameObject secondObject) {
        if (firstObject.getX() < secondObject.getX() + secondObject.width + 10 &&
                firstObject.getX() + firstObject.width > secondObject.getX() - 10 &&
                firstObject.getY() < secondObject.getY() + secondObject.height + 10 &&
                firstObject.getY() + firstObject.height > secondObject.getY() - 10) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkObjectCollision(GameObject firstObject, GameObject secondObject) {
        //check X movment bounce
        if (firstObject.getX() + firstObject.width + firstObject.getVelX() > secondObject.getX() &&
                firstObject.getX() + firstObject.getVelX() < secondObject.getX() + secondObject.width &&
                firstObject.getY() + firstObject.height > secondObject.getY() &&
                firstObject.getY() < secondObject.getY() + secondObject.height) {
            firstObject.setVelX(-firstObject.getVelX());
            return true;
        }

        //check Y movement bounce
        if (firstObject.getX() + firstObject.width > secondObject.getX() &&
                firstObject.getX() < secondObject.getX() + secondObject.width &&
                firstObject.getY() + firstObject.height + firstObject.getVelY() > secondObject.getY() &&
                firstObject.getY() + firstObject.getVelY() < secondObject.getY() + secondObject.height) {
            firstObject.setVelY(-firstObject.getVelY());
            return true;
        }
        return false;
    }
}
