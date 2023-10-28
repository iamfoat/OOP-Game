import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Rocket {
    private int x;
    private int y;
    private Image image;
    private boolean shooting;
    private ArrayList<shoot> shoots;

    public Rocket(PanelGame panelGame) {
        URL imageurl = this.getClass().getResource("photo/rocket.png");
        this.image = new ImageIcon(imageurl).getImage();
        this.x = 310; // ตำแหน่ง x ที่ถูกต้อง (เปลี่ยนเป็นค่าที่คุณต้องการ)
        this.y = 200; // ตำแหน่ง y ที่ถูกต้อง (เปลี่ยนเป็นค่าที่คุณต้องการ)
        this.shooting = false;
        this.shoots = new ArrayList<>();
    }

    public void shoot() {
        shoot newShoot = new shoot();
        newShoot.x = this.x + 40;
        newShoot.y = this.y;
        shoots.add(newShoot);
    }
    

    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, null);

        for (shoot s : shoots) {
            s.draw(g2);
        }
    }

}
