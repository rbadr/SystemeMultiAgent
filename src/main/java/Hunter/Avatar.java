package Hunter;

import fr.univlille1.m2iagl.sma.environment.Environment;
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
        environment.moveNewPosition(this, dirX, dirY);
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

    public void run() {
           }
    
    @Override
     public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                dirY = -1;
                dirX = 0;
                break;
            case KeyEvent.VK_RIGHT:
                dirY = 1;
                dirX = 0;
                break;
            case KeyEvent.VK_UP:
                dirX = -1;
                dirY = 0;
                break;
            case KeyEvent.VK_DOWN:
                dirX = 1;
                dirY = 0;
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