import java.awt.*;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import java.net.URL;
import java.util.Random;

class meteor{
    URL imagemto = this.getClass().getResource("photo/PNG/Meteors/Meteor_05.png");
    Image imagemto1 = new ImageIcon(imagemto).getImage();
    URL imagemtoo = this.getClass().getResource("photo/PNG/Meteors/Meteor_05.png");
    Image imagemto2 = new ImageIcon(imagemtoo).getImage();

    public int y = 0;
    public int x = (int) ((Math.random() * 300) + 20);
    public int count = 0;

    meteor(){
        x = (int) ((Math.random() * 300) + 20);
        y = 0;
        runner.start();
        
    }
    private final int speed = 4;
    public void move() {
        y += speed;
        if (y >= 1000) {
            resetMeteor();
        }
    }
    Thread runner = new Thread(new Runnable() {
        public void run() {
        while (true) {
            y += speed;
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
        x = -rand.nextInt(1000);  
        y = rand.nextInt(600);    
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

    


