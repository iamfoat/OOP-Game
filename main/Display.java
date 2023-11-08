import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Display extends JFrame implements ActionListener{
    private PanelGame panelgame = new PanelGame();
    private homepage homepage1 = new homepage();


    public Display(){        
        init();
    }

    public void init(){
        add(panelgame);
        panelgame.start();
        panelgame.requestFocus();
        meteor mto = new meteor();
        panelgame.addMeteor(mto);
        bomb bmb = new bomb();
        panelgame.addBomb(bmb);
        this.setSize(550, 800);
        this.add(homepage1);
        homepage1.BExit1.addActionListener(this);
        homepage1.BStart.addActionListener(this);
        panelgame.BPause.addActionListener(this);
        panelgame.Bresum.addActionListener(this);
        panelgame.Brep.addActionListener(this);
        

        
    }
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == homepage1.BStart) {
            this.setLocationRelativeTo(null);
            this.remove(homepage1);
            this.add(panelgame);
            this.setSize(550, 800);
            panelgame.requestFocusInWindow();
            panelgame.timestart = false;
            panelgame.score = 0;
            panelgame.HP = 3;
            panelgame.startball = false;
            panelgame.timestart = false;
            revalidate();
        } else if (e.getSource() == homepage1.BExit1) {
            System.exit(0);
        } else if (e.getSource() == panelgame.BPause) {
            this.setLocationRelativeTo(null);
            this.setSize(550, 800);
            this.add(panelgame);
            panelgame.requestFocusInWindow();
            revalidate();
        }
        else if(e.getSource() == panelgame.Brep){
            panelgame.Brep.setVisible(false);            
            this.remove(panelgame);
            this.add(homepage1);
            this.remove(panelgame.Brep);
            homepage1.revalidate();
            homepage1.repaint();

        }
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
