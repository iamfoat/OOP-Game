import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.net.URL;
import javax.swing.*;

public class PanelGame extends JPanel {

    private MouseInput mouseInput;
    private int xDelta = 0 , yDelta = 0;

    private int width;
    private int height;
    private Thread timer;
    private boolean running = true;
    private int FPS = 60;
    private long TARGET_TIME = 1000000000 / FPS;
    URL imageURL = this.getClass().getResource("photo/bgspace.jpg");
    Image imageBg = new ImageIcon(imageURL).getImage();
    URL imageActorURL = this.getClass().getResource("photo/rocket.png");        
    Image imageAc = new ImageIcon(imageActorURL).getImage();
    

    public PanelGame() {
        this.setFocusable(true);
        this.setLayout(null);
        mouseInput = new MouseInput(this);
        addKeyListener(new KeyboardInput(this));
        addMouseListener(mouseInput); 
        addMouseMotionListener(mouseInput);

    }
    public void changeXDelta(int value){
        this.xDelta += value;
        repaint();
    }
    public void changeYDelta(int value){
        this.yDelta += value;
        repaint();
    }

    public void start() {
        width = getWidth();
        height = getHeight();
        
        timer = new Thread(() -> {
            while (running) {
                long startTime = System.nanoTime();
                updateGame();
                render();
                long time = System.nanoTime() - startTime;
                long sleepTime = (TARGET_TIME - time) / 1000000;
                if (sleepTime > 0) {
                    sleep(sleepTime);
                }
            }
        });
        timer.start();
    }

    private void updateGame() {

    }

    private void render() {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imageBg, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(imageAc,xDelta, +310+yDelta, getWidth(), getHeight(), this);
        
        
    }

    private void sleep(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }
}
