package main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable{
    public static  final int WIDTH_ = 640, HEIGHT_= WIDTH_/12*9;
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private int tick=0;

    private BufferedImage image = new BufferedImage(WIDTH_,HEIGHT_,BufferedImage.TYPE_INT_RGB);
    private int pixels[] = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    public Game(){
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        new Window(WIDTH_,HEIGHT_,"The Game",this);

        handler.addGameObject(new Player(WIDTH_/2-32,HEIGHT_/2-32, ID.Player));
    }

    public synchronized void start(){
        thread = new Thread(this);
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

        graphics.drawImage(image,0,0,WIDTH_,HEIGHT_,null);

        handler.render(graphics);

        //Free graphics
        graphics.dispose();
        bs.show();
    }

    private void tick(){
        tick++;
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = i+tick;
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
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >=1)
            {
                tick();
                delta--;
            }

            //We dont wanna overload the system. It delays a little bit but not a good solution.
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(running)
                render();
            frames++;
            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                System.out.println("FPS: "+ frames+ "Tick" + tick);
                frames = 0;
            }
        }
        stop();
    }

    public static void main(String args[]){
        new Game();
    }
}
