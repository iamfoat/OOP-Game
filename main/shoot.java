import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.net.URL;

import javax.swing.*;

class shoot extends JPanel{
    URL imageshoot = this.getClass().getResource("photo/PNG/bomb/shoot.png");
    Image imagest = new ImageIcon(imageshoot).getImage();
    public int y;
    public int x;
    public int count=0;


    public void move(){
	this.y-=1;
    }
    public Rectangle2D getbound(){
    	return (new Rectangle2D.Double(x,y,25,25));
    }
}