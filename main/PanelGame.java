import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
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

    private int meteorSpacing = 200;
    private int meteorReleaseInterval = 100; 
    private int meteorReleaseTimer = 0;

    URL imageURL = this.getClass().getResource("photo/bgspace.jpg");
    Image imageBg = new ImageIcon(imageURL).getImage();
    URL imageActorURL = this.getClass().getResource("photo/rocket.png");        
    Image imageAc = new ImageIcon(imageActorURL).getImage();

    public ArrayList<bomb> bb = new ArrayList<bomb>();

    
    

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
        newMeteor.x = (int) (Math.random() * (getWidth() - meteorSpacing));
        newMeteor.y = 0;
        Random rand = new Random();
        
        if (rand.nextBoolean()) {
            newMeteor.imagemto1 = new ImageIcon(this.getClass().getResource("photo/PNG/Meteors/Meteor_05.png")).getImage();
        } else {
            newMeteor.imagemto1 = new ImageIcon(this.getClass().getResource("photo/PNG/Meteors/Meteor_07.png")).getImage();
        }
        
        mtoo.add(newMeteor);
        
    }

    public void createShoot() {
         // สร้างกระสุนใหม่
        shoot newBullet = new shoot();
        // ตำแหน่ง x ของกระสุนใหม่เท่ากับตำแหน่ง x ของจรวด
        newBullet.x = xDelta+235;
        // ตำแหน่ง y ให้กระสุนเริ่มต้นจากส่วนล่างของหน้าจอ
        newBullet.y = 550;
        // เพิ่มกระสุนใหม่ลงในรายการ shoots
        shoots.add(newBullet);
        // อัปเดตกระสุนเคลื่อนที่
        newBullet.move();
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
            meteor m = mtoo.get(i);
            g.drawImage(m.imagemto1, m.getX(), m.getY(), 50, 50, this);
        }
        for (int i = 0; i < shoots.size(); i++) {
            shoot bullet = shoots.get(i);
            g.drawImage(bullet.imagest, bullet.x, bullet.y, 50, 50, null);
            bullet.move();
            bullet.count++;
            if (bullet.y < 0) {
                shoots.remove(i);
            }
        }
        for (int i = 0; i < shoots.size(); i++) {
            for (int j = 0; j < mtoo.size(); j++) {
                if (Intersect(shoots.get(i).getbound(), mtoo.get(j).getbound())) {
                    mtoo.remove(j);
                    shoots.remove(i);
                    score += 10;
                    g.drawString("+10", xDelta+210 , 310);
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
