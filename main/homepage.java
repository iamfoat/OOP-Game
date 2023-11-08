import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class homepage extends JPanel{
    
    private ImageIcon feild = new ImageIcon(this.getClass().getResource("photo/bg.png"));
	private ImageIcon starts = new ImageIcon(this.getClass().getResource("photo/start.png"));
	private ImageIcon exit = new ImageIcon(this.getClass().getResource("photo/exist.png"));
	public JButton BStart = new JButton(starts);
	public JButton BExit1  = new JButton(exit);

	public homepage(){
            setLayout(null);
            BExit1.setBounds(180, 550, 170,90);
            add(BExit1);
            BExit1.setBackground(new Color(0, 0, 0, 0));
            BExit1.setBorderPainted(false); // ปิดการวาดเส้นขอบ
            BExit1.setFocusPainted(false);
            BExit1.setContentAreaFilled(false);
            add(BStart);
            BStart.setBackground(new Color(0, 0, 0, 0));
            BStart.setBorderPainted(false); // ปิดการวาดเส้นขอบ
            BStart.setFocusPainted(false);
            BStart.setContentAreaFilled(false); 
            BStart.setBounds(180,350,170,90);     
	}
	public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(feild.getImage(),0,0,550,800,this);
            g.setColor(Color.WHITE);
            g.setFont(new Font("2005_iannnnnTKO",Font.CENTER_BASELINE,60));		
	}
}