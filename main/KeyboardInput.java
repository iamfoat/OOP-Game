import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {
    private PanelGame panelgame;
    public KeyboardInput(PanelGame panelgame){
        this.panelgame = panelgame;
    }
    @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_A:
                    panelgame.changeXDelta(-10);
                    break;
                case KeyEvent.VK_S:
                    panelgame.changeYDelta(10);
                    break;
                case KeyEvent.VK_D:
                    panelgame.changeXDelta(10);
                    break;
                case KeyEvent.VK_W:
                    panelgame.changeYDelta(-10);
                    break;
            
                default:
                    break;
            }
            
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
        }
}
