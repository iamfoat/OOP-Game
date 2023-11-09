import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import java.util.Random;

public class bomb {
    public Image imagebomb;
    public int y = 0;
    public int x = (int) ((Math.random() * 300) + 40);

    public bomb() {
        String imageLocation = "photo/PNG/Sprites/Bombs/bomb1.png";
        URL imageURL1 = this.getClass().getResource(imageLocation);
        imagebomb = Toolkit.getDefaultToolkit().getImage(imageURL1);
        runner.start();
    }
    Thread runner = new Thread(new Runnable() {
        public void run() {
            while (true) {
                y += 1;
                if (y >= 1000 || x < 0 || x > 550) {
                    resetBomb();
                }
                try {
                    Thread.sleep(10); // เพิ่มหน่วงเวลานี้เพื่อทำให้ bomb ปล่อยออกมาช้าลง
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });
    

    private void resetBomb() {
        Random rand = new Random();
        x = rand.nextInt(550); 
        y = 0; 
    }
    public Image getImage() {
        return imagebomb;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle2D getbound() {
        return (new Rectangle2D.Double(x, y, 50, 50));
    }
}
