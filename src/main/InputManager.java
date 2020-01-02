package main;


import Entities.GameObject;

import java.awt.*;
import java.awt.event.*;

public class InputManager extends KeyAdapter implements MouseListener, MouseMotionListener {

    private Handler handler;
    private Point pointA, pointB;

    public InputManager(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.gameObjects.size() ; i++) {
            GameObject tempObject = handler.gameObjects.get(i);
            if(tempObject.getId()== ID.Player){
                if(key== KeyEvent.VK_W) tempObject.setVelY(-5);
                if(key== KeyEvent.VK_A) tempObject.setVelX(-5);
                if(key== KeyEvent.VK_D) tempObject.setVelX(5);
                if(key== KeyEvent.VK_S) tempObject.setVelY(5);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.gameObjects.size() ; i++) {
            GameObject tempObject = handler.gameObjects.get(i);
            if(tempObject.getId()== ID.Player){
                if(key== KeyEvent.VK_W) tempObject.setVelY(0);
                if(key== KeyEvent.VK_A) tempObject.setVelX(0);
                if(key== KeyEvent.VK_D) tempObject.setVelX(0);
                if(key== KeyEvent.VK_S) tempObject.setVelY(0);
            }
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        for (int i = 0; i < handler.gameObjects.size() ; i++) {
            GameObject tempObject = handler.gameObjects.get(i);
            if(tempObject.getId()== ID.Player){
                 System.out.print("goood job");
                 pointA = e.getLocationOnScreen();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (int i = 0; i < handler.gameObjects.size() ; i++) {
            GameObject tempObject = handler.gameObjects.get(i);
            if(tempObject.getId()== ID.Player){
                System.out.print("goood job");
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

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
