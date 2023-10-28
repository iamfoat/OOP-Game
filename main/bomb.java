import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.net.URL;

public class bomb {
    Image imagebomb;
    public int x = 0;
    public int y = (int) ((Math.random() * 300) + 40);
    public int count = 0;

    bomb() {
        String imageLocation = "photo/PNG/Sprites/Bombs/bomb1.png";
        URL imageURL1 = this.getClass().getResource(imageLocation);
        imagebomb = Toolkit.getDefaultToolkit().getImage(imageURL1);
        runner.start();
    }
    Thread runner = new Thread(new Runnable() {
        public void run() {
            while (true) {
                x += 1;
                if (x >= 1100) {
                    imagebomb = null;
                    runner = null;
                    x = 10;
                    y = 10;
                }
                try {
                    runner.sleep(1);
                } catch (InterruptedException e) {
                }
            }
        }
    });

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
        return (new Rectangle2D.Double(x, y, 45, 45));
    }
}
