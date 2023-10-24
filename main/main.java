import java.awt.*;
import java.net.URL;

import javax.swing.*;

public class main extends JFrame{


    main(){
        add(new d());
    }

    class d extends JPanel{
        URL imageURL = this.getClass().getResource("photo/bgspace.jpg");
        Image image = new ImageIcon(imageURL).getImage();

        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
    public static void main(String[] args){
        main frame = new main();
        frame.setTitle("Desty Space");
        frame.setSize(550, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
