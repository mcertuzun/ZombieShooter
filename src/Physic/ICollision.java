package Physic;

import Entities.GameObject;

public interface ICollision {
    abstract void checkWallCollision(GameObject gameObject);

    abstract boolean checkObjectCollision(GameObject firstObject, GameObject secondObject);
}
