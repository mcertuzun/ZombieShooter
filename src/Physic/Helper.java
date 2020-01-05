package Physic;

import Entities.GameObject;

import java.awt.*;

public class Helper {
    public static Point pointA,pointB;
    public static boolean isMoved;
    public void render(Graphics graphics) {
        if(isMoved){
            graphics.drawLine(pointA.x, pointA.y, pointB.x, pointB.y);
        }
    }

}
