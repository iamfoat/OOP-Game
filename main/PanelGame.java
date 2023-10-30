import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;



import javax.swing.*;

public class PanelGame extends JPanel{

    private MouseInput mouseInput;
    private int xDelta = 0 , yDelta = 0;

    private int width;
    private int height;
    private Thread timer;
    private Thread bombb;
    private boolean running = true;
    private int FPS = 60;
    private long TARGET_TIME = 1000000000 / FPS;


    public ArrayList<shoot> shoots = new ArrayList<shoot>();
    public int score = 0;

    public ArrayList<meteor> mtoo = new ArrayList<meteor>();
    URL imagemto = this.getClass().getResource("photo/PNG/Meteors/Meteor_05.png");
    Image imagemto1 = new ImageIcon(imagemto).getImage();
    Iterator<meteor> iterator = mtoo.iterator();


    private int meteorSpacing = 200;
    private int meteorInterval = 100; 
    private int meteorTimer = 0;

    URL imageURL = this.getClass().getResource("photo/bgspace.jpg");
    Image imageBg = new ImageIcon(imageURL).getImage();
    // URL imageActorURL = this.getClass().getResource("photo/rocket.png");        
    // Image imageAc = new ImageIcon(imageActorURL).getImage();


    URL imagebb = this.getClass().getResource("photo/PNG/Meteors/Meteor_05.png");
    Image imageBb = new ImageIcon(imagebb).getImage();
    public ArrayList<bomb> bb = new ArrayList<bomb>();
    public int HP = 3;
    boolean startball = false;

    private Rocket rocket;
    

    public PanelGame() {
        this.setFocusable(true);
        this.setLayout(null);
        mouseInput = new MouseInput(this);
        addKeyListener(new KeyboardInput(this));
        addMouseListener(mouseInput); 
        addMouseMotionListener(mouseInput);    
        bomb();
        rocket = new Rocket();

    }
    public void changeXDelta(int value){
        this.xDelta += value;
        repaint();
    }
    public void changeYDelta(int value){
        this.yDelta += value;
        repaint();
    }


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

    public void bomb(){
        bombb = new Thread(new Runnable() {

            @Override
            public void run() {
                while(true){
                    try {
                        if (startball == false) {
                            Thread.sleep((long) (Math.random() * 10000) + 2000);
                        }
                    } 
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (startball == false) {
                        addBomb(new bomb());
                    }
                }
            }
            
        });
        bombb.start();
    }
    
    

    private void updateGame() {
        meteorTimer++;
        if (meteorTimer >= meteorInterval) {
            meteorTimer = 0;
            createNewMeteor();
        }
    
        for (int i = 0; i < mtoo.size(); i++) {
            meteor mto = mtoo.get(i);
            mto.move(); 
        }

        
    }
    private void createNewMeteor() {
        meteor newMeteor = new meteor();
        newMeteor.x = (int) (Math.random() * (getWidth() - meteorSpacing));
        newMeteor.y = 0;
        Random rand = new Random();
        
        if (rand.nextBoolean()) {
            newMeteor.imagemto1 = new ImageIcon(this.getClass().getResource("photo/PNG/Meteors/Meteor_05.png")).getImage();
        } 
        else {
            newMeteor.imagemto1 = new ImageIcon(this.getClass().getResource("photo/PNG/Meteors/Meteor_07.png")).getImage();
        }
        
        mtoo.add(newMeteor);
        
    }

    public void createShoot() {
        shoot newBullet = new shoot();
        newBullet.x = xDelta+235;
        newBullet.y = 550;
        shoots.add(newBullet);
        newBullet.move();
    }

    

    private void render() {
        repaint();
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void addMeteor(meteor mto) {
        mtoo.add(mto); 
    }

    public void addBomb(bomb bmb) {
        bb.add(bmb); 
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imageBg, 0, 0, getWidth(), getHeight(), this);
        //วาดจรวด
        g.drawImage(rocket.image,xDelta, +310+yDelta, 550, 800, this);

        //ปล่อย meteor
        if(!mtoo.isEmpty()){
            for (int i = 0; i < mtoo.size(); i++) {
                meteor m = mtoo.get(i);
                g.drawImage(m.imagemto1, m.getX(), m.getY(), 50, 50, this);
            
            }
        }

        //สร้างกระสุน
        for (int i = 0; i < shoots.size(); i++) {
            shoot bullet = shoots.get(i);
            g.drawImage(bullet.imagest, bullet.x, bullet.y, 50, 50, null);
            bullet.move();
            bullet.count++;
            if (bullet.y < 0) {
                shoots.remove(i);
                i--;
            }
        }
        //กระสุนชนmeteor
        if (!shoots.isEmpty()){
            for (int i = 0; i < shoots.size(); i++) {
                for (int j = 0; j < mtoo.size(); j++) {
                    if (Intersect(shoots.get(i).getbound(), mtoo.get(j).getbound())) {
                        mtoo.remove(j);
                        shoots.remove(i);
                        i--;
                        score += 10;
                        g.drawString("+10", xDelta+210 , 310);
                    }
                }
            }
        }
        //ปล่อย bomb
        for (int i = 0; i < bb.size(); i++) {
            bomb b = bb.get(i);
            g.drawImage(b.imagebomb, b.getX(), b.getY(), 80, 80, this);

        }

        // bomb ชนกระสุน
        if (!shoots.isEmpty() && !bb.isEmpty()){
            for (int i = 0; i < shoots.size(); i++) {
                for (int j = 0; j < bb.size(); j++) {
                    if (Intersect(shoots.get(i).getbound(), bb.get(j).getbound())) {
                        bb.remove(j);
                        shoots.remove(i);
                        i--;
                        score -= 20;
                        HP = HP - 1;
                        g.drawString("-1HP", xDelta + 210, 310);
                        g.drawString("-20", xDelta + 210, 250);
                    }
                }
            }  
        }
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

