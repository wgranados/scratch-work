import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
 
public class Grader extends Canvas implements Runnable{
    //screen dimensions and variables
    static final int WIDTH = 1024;
    static final int HEIGHT = WIDTH / 4 * 3; //4:3 aspect ratio
    private JFrame frame;
 
    //game updates per second
    static final int UPS = 60;
     
    //variables for the thread
    private Thread thread;
    private boolean running;
     
    //used for drawing items to the screen
    private Graphics2D graphics;
                 
    //initialize game objects, load media(pics, music, etc)
    public void init() {
         
    }
     
    //update game objects
    public void update() {
         
    }
     
    //draw things to the screen
    public void draw() {
         
    }
     
    public static void main(String[] args) {
        Grader grader = new Grader();
        grader.frame.setResizable(false);
        grader.frame.add(grader); //grader is a component because it extends Canvas
        grader.frame.pack();
        grader.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        grader.frame.setLocationRelativeTo(null);
        grader.frame.setVisible(true);
        grader.start();
    }
     
    public Grader() {
        Dimension size = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(size);
        frame = new JFrame();
    }
 
    //starts a new thread for the game
    public synchronized void start() {
        thread = new Thread(this, "Game");
        running = true;
        thread.start();
    }
     
    //main game loop
    public void run() {
        init();
        long startTime = System.nanoTime();
        double ns = 1000000000.0 / UPS;
        double delta = 0;
        int frames = 0;
        int updates = 0;
         
        long secondTimer = System.nanoTime();
        while(running) {
            long now = System.nanoTime();
            delta += (now - startTime) / ns;
            startTime = now;
            while(delta >= 1) {
                update();
                delta--;
                updates++;
            }
            render();
            frames++;
            if(System.nanoTime() - secondTimer > 1000000000) {
                this.frame.setTitle(updates + " ups  ||  " + frames + " fps");
                secondTimer += 1000000000;
                frames = 0;
                updates = 0;
            }
        }
        System.exit(0);
    }
     
    public void render() {
        BufferStrategy bs = getBufferStrategy(); //method from Canvas class
         
        if(bs == null) {
            createBufferStrategy(3); //creates it only for the first time the loop runs (trip buff)
            return;
        }
         
        graphics = (Graphics2D)bs.getDrawGraphics();
        draw();
        graphics.dispose();
        bs.show();
    }
     
    //stops the game thread and quits
        public synchronized void stop() {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
}