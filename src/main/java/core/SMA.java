package core;

import core.IAgent;
import core.Environment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;


public class SMA<T extends IAgent> implements KeyListener {
    private int delay;
    protected boolean simulationEnded = false;
    private int nbTicks;
    
    protected Environment<T> environment;

    public SMA(Environment<T> environment, int delay, int nbTicks) {
        this.environment = environment;
        this.delay = delay;
        this.nbTicks= nbTicks;
    }

    public void run() throws InterruptedException {
        simulationEnded = false;
        try {
            while (!simulationEnded) {
                Thread.sleep(delay);
                SMA.this.startAgentTour();
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void startAgentTour() {
        List<T> agents = environment.getAllAgents();
        for (T agent : agents) {
             if (!environment.isDead(agent)) {
                 agent.decide();
             }
         }
        nbTicks++;
        environment.clearDead();
        environment.notifyObservers();
    }
    
    public long getNbTicks() {
        return nbTicks;
    }

    public synchronized void endSimulation(boolean ended) {
        this.simulationEnded = ended;
    }

    public Environment<T> getEnvironment() {
        return environment;
    }
    
    public boolean simulationEnded() {
        return simulationEnded;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int id = e.getID();
        switch (id) {
            case KeyEvent.VK_SPACE:
                break;
            case KeyEvent.VK_W:
                delay = delay - 50;
                break;
            case KeyEvent.VK_X:
                delay = delay + 50;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
