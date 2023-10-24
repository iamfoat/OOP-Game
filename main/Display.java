import java.awt.*;

import java.net.URL;

import javax.swing.*;

public class Display extends JFrame{


    Display(){
        init();
        
        
    }

    public void init(){
        PanelGame panelgame = new PanelGame();
        add(panelgame);
        panelgame.start();
        panelgame.requestFocus();
        
    }
    public static void main(String[] args){
        Display frame = new Display();
        frame.setTitle("Desty Space");
        frame.setSize(550, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    
        
    }
}