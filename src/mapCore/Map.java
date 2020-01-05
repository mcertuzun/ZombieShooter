package mapCore;

import Entities.GameObject;
import Entities.Player;
import Entities.Target;
import Entities.Wall;
import main.Handler;
import main.ID;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Map {
    /** The value indicating a clear cell */
    private static final int CLEAR = 0;
    /** The value indicating a blocked cell */
    private static final int BLOCKED = 1;
    public static int currentMap;
    private int width;
    private int height;

    private int tilePerX;
    private int tilePerY;
    /** The rendered size of the tile (in pixels) */
    private int tileSize;

    /** The actual data for our map */
    private GameObject[][] gameObjects;

    /**
     * Create a new map with some default contents
     */
    public Map(int height, int width, int tileSize) {
        this.tileSize = tileSize;
        this.width = width;
        this.height = height;
        init();
    }

    private void init() {
        tilePerX = width / tileSize;
        tilePerY = height / tileSize;
        gameObjects = new GameObject[tilePerY][tilePerX];
    }

    public GameObject[][] loadNextMap() {

        currentMap++;
        try {
            gameObjects = loadMap(
                    "maps/map" + currentMap + ".txt");
            } catch (IOException ex) {
            if (currentMap == 1) {
                // no maps to load!
            }
            currentMap = 0;
        }

        return gameObjects;
    }

    private GameObject[][] loadMap(String fileName) throws IOException {
        GameObject[][] obj = new GameObject[height][width];
        ArrayList lines = new ArrayList();
        // read every line in the text file into the list
        BufferedReader reader = new BufferedReader(new FileReader("src/maps/map1.txt"));
        while (true) {
            String line = reader.readLine();
            // no more lines to read
            if (line == null) {
                reader.close();
                break;
            }
            // add every line except for comments
            else if (!line.startsWith("#")) {
                lines.add(line);
                //width = Math.max(width, line.length());
            }
        }

        int nextHeight = -100;

        for (int y = 0; y < tilePerY; y++) {
            String line = (String) lines.get(y);
            nextHeight += tileSize;
            int nextWidth = -100;
            for (int x = 0; x < line.length(); x++) {
                nextWidth += tileSize;
                char ch = line.charAt(x);
                // check if the char represents a sprite
                switch (ch) {
                    case ('w'):
                        Handler.gameObjects.add(new Wall(nextWidth, nextHeight, ID.Wall, tileSize, tileSize));
                        break;
                    case ('o'):
                        Handler.gameObjects.add(new Target(nextWidth, nextHeight, ID.Target, tileSize, tileSize));
                        break;
                    case ('p'):
                        Handler.gameObjects.add(new Player(nextWidth, nextHeight, ID.Player, tileSize, tileSize));
                        break;
                }
            }
        }
        return gameObjects;
    }


}