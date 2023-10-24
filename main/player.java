import java.awt.*;
import java.net.URL;

import javax.swing.*;

public class player {
    private double x;
    private double y;
    private Image image;
    public player(){
        this.image = new ImageIcon(getClass().getResource("/photo/rocket.png")).getImage();
    }
    public void draw(Graphics2D g2){
        
    }
}
