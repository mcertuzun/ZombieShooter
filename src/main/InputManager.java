package main;


import Entities.GameObject;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InputManager extends KeyAdapter implements MouseListener, MouseMotionListener {

    private Handler handler;
    private Point pointA, pointB;

    public InputManager(Handler handler) {
        this.handler = handler;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        for (int i = 0; i < handler.gameObjects.size() ; i++) {
            GameObject tempObject = handler.gameObjects.get(i);
            if(tempObject.getId()== ID.UI){
                Game.isRestart=true;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (int i = 0; i < handler.gameObjects.size() ; i++) {
            GameObject tempObject = handler.gameObjects.get(i);
            if(tempObject.getId()== ID.Player){
                Point pointA = new Point(tempObject.getX() + tempObject.width / 2, tempObject.getY() + tempObject.height / 2);
                Point pointB = e.getPoint();
                if (pointA.x < pointB.x + tempObject.width &&
                        pointA.x + tempObject.width > pointB.x &&
                        pointA.y < pointB.y + tempObject.height &&
                        pointA.y + tempObject.height > pointB.y) {
                    tempObject.onPlayer = true;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (int i = 0; i < handler.gameObjects.size() ; i++) {
            GameObject tempObject = handler.gameObjects.get(i);
            if (tempObject.getId() == ID.Player && tempObject.onPlayer) {
                pointA = new Point(tempObject.getX() + tempObject.width / 2, tempObject.getY() + tempObject.height / 2);
                pointB = e.getPoint();
                tempObject.setVelX((int) (pointA.x - pointB.getX()) / 10);
                tempObject.setVelY((int) (pointA.y - pointB.getY()) / 10);
                tempObject.isAiming = false;
                tempObject.onPlayer = false;
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        for (int i = 0; i < handler.gameObjects.size(); i++) {
            GameObject tempObject = handler.gameObjects.get(i);
            if (tempObject.getId() == ID.Player && tempObject.onPlayer) {
                tempObject.isAiming = true;
                Point pointA = new Point(tempObject.getX() + tempObject.width / 2, tempObject.getY() + tempObject.height / 2);
                Point pointB = e.getPoint();
                tempObject.drawLine(pointA, pointB);
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {


    }

}
