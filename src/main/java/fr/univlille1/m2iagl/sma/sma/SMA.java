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
        if(nbTicks==0){
            while (!simulationEnded) {
            Thread.sleep(delay);
            SMA.this.startAgentTour();
        }
        }
        for(int i=0; i<nbTicks;i++){
            Thread.sleep(delay);
            SMA.this.startAgentTour();
        }
    }

    public void startAgentTour() {
        List<T> agents = environment.getAllAgents();
        agents.forEach(T::decide); 
        environment.notifyObservers();
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
