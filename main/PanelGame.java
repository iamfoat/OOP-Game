import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;
import java.awt.geom.Rectangle2D;


import javax.swing.*;

public class PanelGame extends JPanel{

    private MouseInput mouseInput;
    private int xDelta = 0 , yDelta = 0;

    private int width;
    private int height;
    private Thread timer;
    private boolean running = true;
    private int FPS = 60;
    private long TARGET_TIME = 1000000000 / FPS;

    public ArrayList<shoot> shoots = new ArrayList<shoot>();
    public int score = 0;

    public ArrayList<meteor> mtoo = new ArrayList<meteor>();
    URL imagemto = this.getClass().getResource("photo/PNG/Meteors/Meteor_05.png");
    Image imagemto1 = new ImageIcon(imagemto).getImage();
    URL imagemtoo = this.getClass().getResource("photo/PNG/Meteors/Meteor_05.png");
    Image imagemto2 = new ImageIcon(imagemtoo).getImage();
    private int meteorReleaseInterval = 100; 
    private int meteorReleaseTimer = 0;

    URL imageURL = this.getClass().getResource("photo/bgspace.jpg");
    Image imageBg = new ImageIcon(imageURL).getImage();
    URL imageActorURL = this.getClass().getResource("photo/rocket.png");        
    Image imageAc = new ImageIcon(imageActorURL).getImage();

    
    

    public PanelGame() {
        this.setFocusable(true);
        this.setLayout(null);
        mouseInput = new MouseInput(this);
        addKeyListener(new KeyboardInput(this));
        addMouseListener(mouseInput); 
        addMouseMotionListener(mouseInput);
        
    

    }
    public void changeXDelta(int value){
        this.xDelta += value;
        repaint();
    }
    public void changeYDelta(int value){
        this.yDelta += value;
        repaint();
    }

    // Thread paralyze = new Thread(new Runnable() {
    //     public void run() {
    //         while (true) {
    //             if (time_paralyze < 1) {
    //                 paralyze1 = false;
    //                 time_paralyze = 5;
    //             }
    //             try {
    //                 Thread.sleep(5000);
    //             } catch (InterruptedException e) {
    //                 e.printStackTrace();
    //             }
    //         }
    //     }
    // });

    public void start() {
        width = getWidth();
        height = getHeight();
        
        timer = new Thread(() -> {
            while (running) {
                long startTime = System.nanoTime();
                updateGame();
                render();
                long time = System.nanoTime() - startTime;
                long sleepTime = (TARGET_TIME - time) / 1000000;
                if (sleepTime > 0) {
                    sleep(sleepTime);
                    // System.out.println(sleepTime);
                }
            }
        });
        timer.start();
    }

    private void updateGame() {
        meteorReleaseTimer++;
        if (meteorReleaseTimer >= meteorReleaseInterval) {
            meteorReleaseTimer = 0;
            createNewMeteor();
        }
    
        for (int i = 0; i < mtoo.size(); i++) {
            meteor mto = mtoo.get(i);
            mto.move(); 
        }
    }
    private void createNewMeteor() {
        meteor newMeteor = new meteor();
        mtoo.add(newMeteor); 
    }

    private void render() {
        repaint();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void addMeteor(meteor mto) {
        mtoo.add(mto); 
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imageBg, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(imageAc,xDelta, +310+yDelta, getWidth(), getHeight(), this);
        
        for (int i = 0; i < mtoo.size(); i++) {
            g.drawImage(mtoo.get(i).getImage(), mtoo.get(i).getX(), mtoo.get(i).getY(), 50, 50, this);
        }
        // for (int i = 0; i < shoots.size(); i++) {
        //     for (int j = 0; j < mto.size(); j++) {
        //         if (Intersect(shoots.get(i).getbound(), mto.get(j).getbound())) {
        //             mto.remove(j);
        //             shoots.remove(i);
        //             score += 10;
        //             g.drawString("+10", xDelta + 100, 650);
        //         }
        //     }
        // }

        
    }
    public boolean Intersect(Rectangle2D a, Rectangle2D b) {
        return (a.intersects(b));
    }

    public static void sleep(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }
}
