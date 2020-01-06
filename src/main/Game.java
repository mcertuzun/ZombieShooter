package main;

import Entities.GameObject;
import Physic.Helper;
import mapCore.Map;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{
    public static final int WIDTH_ = 1000, HEIGHT_ = 1000;
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private int tick=0;
    private Map map;
    public static Window win;
    private GameObject player;
    public Helper helper;
    public Game(){
        init();
    }

    private synchronized void init() {
        handler = new Handler();
        this.addMouseListener(new InputManager(handler));
        this.addMouseMotionListener(new InputManager(handler));
        new Menu();
        win = new Window(WIDTH_, HEIGHT_, "The Game", this);
        loadMap();

    }

    private void loadMap() {
        map = new Map(WIDTH_, HEIGHT_, 100);
        map.loadNextMap();
    }

    public synchronized void start(){
        thread = new Thread(this,"threadOne");
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void render(){
        //If you see blank canvas so you need more buffer strategy
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics graphics =  bs.getDrawGraphics();
        graphics.setColor(Color.white);
        graphics.fillRect(0,0,WIDTH_,HEIGHT_);
        handler.render((Graphics2D)graphics);
        graphics.dispose();
        bs.show();
    }

    private void tick(){
        handler.tick();
        tick++;
    }


    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(running)
        {
            long elapsedTime = System.currentTimeMillis() - timer;

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >=1)
            {
                timer += elapsedTime;
                update(elapsedTime);
                tick();
                delta--;
            }

            if(running){
                render();
            }

            frames++;
            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                System.out.println("FPS: "+ frames+ "Tick " + tick);
                frames = 0;
            }
        }
        stop();
    }

    private void update(long elapsedTime) {
    }

    public static void main(String args[]){
        new Game();
    }
}
