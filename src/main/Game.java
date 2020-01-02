package main;

import Entities.GameObject;
import Entities.Player;
import Entities.Target;
import mapCore.Map;
import mapCore.ResourceManager;
import mapCore.TileMap;
import mapCore.TileMapRenderer;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{
    public static final int WIDTH_ = 600, HEIGHT_= 600;
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private int tick=0;
    Map map;
    private GameObject player;

    public Game(){

        handler = new Handler();
        this.addKeyListener(new InputManager(handler));
        this.addMouseListener(new InputManager(handler));
        this.addMouseMotionListener(new InputManager(handler));
        map = new Map();

        new Window(WIDTH_,HEIGHT_,"The Game",this);
        player = new Player(10,500, ID.Player);
        handler.addGameObject(player);
        handler.addGameObject(new Target(500,20, ID.Target));
        handler.addGameObject(new Target(500,80, ID.Target));
        handler.addGameObject(new Target(400,100, ID.Target));
        handler.addGameObject(new Target(100,20, ID.Target));
        handler.addGameObject(new Target(300,400, ID.Target));
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
        map.paint((Graphics2D) graphics);
        handler.render(graphics);

        graphics.dispose();
        bs.show();
    }

    private void tick(){
        handler.tick();
        tick++;
    }
    private void collisionDetection() {
    	for (GameObject obj :Handler.gameObjects) {
    		if(obj.getId()==ID.Player) {
    			player = obj;
    		}else {
    			
    			if(player.getX() == obj.getX() - 35) {
    				player.setVelX(-player.getVelX());
        			System.out.println("interSol");
        		}
    			if(player.getX() == obj.getX() + 35) {
    				player.setVelX(-player.getVelX());
        			System.out.println("interSað");
        		}
    			if(player.getY() == obj.getY() - 35) {
    				player.setVelY(-player.getVelY());
        			System.out.println("interAþaðý");
        		}
    			if(player.getY() == obj.getY() + 35) {
    				player.setVelY(-player.getVelY());
        			System.out.println("interYukarý");
        		}
    			
    			
    			 

    			
    		}
    				        
		
    	}
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

            //We dont wanna overload the system. It delays a little bit but not a good solution.
           /*try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

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
