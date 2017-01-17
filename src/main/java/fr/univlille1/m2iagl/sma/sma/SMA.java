package fr.univlille1.m2iagl.sma.sma;

import fr.univlille1.m2iagl.sma.agents.IAgent;
import fr.univlille1.m2iagl.sma.environment.Environment;
import java.util.List;


public class SMA<T extends IAgent> {
    private final int delay;
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
}
