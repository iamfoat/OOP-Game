import java.awt.*;
import java.net.URL;
import javax.swing.ImageIcon;
import java.awt.geom.Rectangle2D;


public class Rocket {
    public int x = 0; // กำหนดค่าเริ่มต้นของ x
    public int y = 0; // กำหนดค่าเริ่มต้นของ y
    public Image image;

    public Rocket() {
        URL imageurl = this.getClass().getResource("photo/rocket.png");
        this.image = new ImageIcon(imageurl).getImage();
    }
   

    public Rectangle getbound() {
        return new Rectangle(x, 310 + y, 65, 92); // ใช้ x และ y ใน Rectangle
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
