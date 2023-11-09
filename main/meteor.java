import java.awt.*;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import java.net.URL;
import java.util.Random;

public class meteor {
    public URL imagemto = this.getClass().getResource("photo/PNG/Meteors/Meteor_05.png");
    public Image imagemto1 = new ImageIcon(imagemto).getImage();

    public int y = 0;
    public int x = (int) ((Math.random() * 300) + 20);

    public meteor(){
        x = (int) ((Math.random() * 300) + 20);
        y = 0;
        runner.start();
        
    }
    
    private final int speed = 8;
    public void move() {
        y+=speed;
        if (y >= 1000 || x < 0 || x > 550) { 
            resetMeteor();
        }
    }
    Thread runner = new Thread(new Runnable() {
        public void run() {
            long lastTime = System.currentTimeMillis();
            while (true) {
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - lastTime;
                lastTime = currentTime;
    
                y += (speed * elapsedTime / 1000); // คำนวณความเร็วใหม่
    
                if (y >= 1000) {
                    resetMeteor();
                }
    
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
});
private void resetMeteor() {
    Random rand = new Random();
    x = rand.nextInt(550); 
    y = 0; 
    }
    

    public Image getImage() {
        return imagemto1;
    }
    

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public Rectangle2D getbound() {
        return (new Rectangle2D.Double(x, y, 45, 45));
    }
}

    


