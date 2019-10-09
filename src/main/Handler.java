package main;
import java.awt.*;
import java.util.LinkedList;

public class Handler {
    LinkedList<GameObject> gameObjects = new LinkedList<>();

    public void tick(){
        for(int i=0; i<gameObjects.size(); i++){
            GameObject tempGameObject = gameObjects.get(i);

            tempGameObject.tick();
        }
    }

    public void render(Graphics graphics){
        for(int i=0; i<gameObjects.size(); i++){
            GameObject tempGameObject = gameObjects.get(i);

            tempGameObject.render(graphics);
        }
    }

    public void addGameObject(GameObject gameObject){
        this.gameObjects.add(gameObject);
    }

    public void removeGameObject(GameObject gameObject){
        this.gameObjects.remove(gameObject);
    }
}
