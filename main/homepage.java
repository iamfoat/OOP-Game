import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class homepage extends JPanel{
    
    // private ImageIcon headIcon = new ImageIcon(this.getClass().getResource("photo/head.png"));
    private ImageIcon feild = new ImageIcon(this.getClass().getResource("photo/bg.png"));
	private ImageIcon starts = new ImageIcon(this.getClass().getResource("photo/start.png"));
	private ImageIcon exit = new ImageIcon(this.getClass().getResource("photo/exist.png"));
	public JButton BStart = new JButton(starts);
	public JButton BExit1  = new JButton(exit);
    // public JButton head = new JButton(headIcon);
	homepage(){
            setLayout(null);
            BExit1.setBounds(180, 550, 170,90);
            add(BExit1);
            add(BStart);
            BStart.setBounds(180,350,170,90);     
	}
	public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(feild.getImage(),0,0,550,800,this);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Hobo Std",Font.CENTER_BASELINE,60));		
            // g.drawString("Desty Space",100,100);
	}
}