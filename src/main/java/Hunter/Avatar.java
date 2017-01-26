package Hunter;

import core.HunterAvatarAgent;
import core.Environment;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Avatar extends HunterAvatarAgent implements KeyListener {

    private int dirX,dirY;
    
    public Avatar(Environment<HunterAvatarAgent> environment) {
        super(environment);
    }

    @Override
    public void decide() {
        this.moveman(dirX,dirY);
    }

    @Override
    public Color getColor() {
        return Color.BLUE;
    }

    @Override
    public boolean canStopSimulation() {
        return false;
    }

    @Override
    public boolean isEatable() {
        return true;
    }

    @Override
    public boolean canEat() {
        return false;
    }
    
    @Override
     public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                dirY = 0;
                dirX = -1;
                break;
            case KeyEvent.VK_RIGHT:
                dirY = 0;
                dirX = 1;
                break;
            case KeyEvent.VK_UP:
                dirX = 0;
                dirY = -1;
                break;
            case KeyEvent.VK_DOWN:
                dirX = 0;
                dirY = 1;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
          }

    @Override
    public void keyReleased(KeyEvent e) {
         }
}