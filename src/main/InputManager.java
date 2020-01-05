package main;


import Entities.GameObject;
import Physic.Helper;

import java.awt.*;
import java.awt.event.*;

public class InputManager extends KeyAdapter implements MouseListener, MouseMotionListener {

    private Handler handler;
    private Point pointA, pointB;

    public InputManager(Handler handler) {
        this.handler = handler;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        for (int i = 0; i < handler.gameObjects.size() ; i++) {
            GameObject tempObject = handler.gameObjects.get(i);
            if(tempObject.getId()== ID.Player){
                 pointA = e.getLocationOnScreen();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (int i = 0; i < handler.gameObjects.size() ; i++) {
            GameObject tempObject = handler.gameObjects.get(i);
            if(tempObject.getId()== ID.Player){
                pointB = e.getLocationOnScreen();
                int velx = (int) (pointA.getX() - pointB.getX());
                int vely = (int) (pointA.getY() - pointB.getY());
                tempObject.setVelX(velx/10);
                tempObject.setVelY(vely/10);
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

    }
    boolean aiming = true;
    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
