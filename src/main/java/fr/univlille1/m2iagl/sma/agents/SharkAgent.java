package fr.univlille1.m2iagl.sma.agents;

import fr.univlille1.m2iagl.sma.environment.Coordinate;
import fr.univlille1.m2iagl.sma.environment.Environment;
import java.awt.*;
import java.util.Map;

public class SharkAgent extends Agent {
    
    private static int STARVATION_DURATION = 3;
    private static int GESTATION_DURATION = 8;
    private int starvation;

    public SharkAgent(Environment<Agent> environment) {
        super(environment);
    }

    @Override
    public void decide() {
        super.decide();
        if (starvation >= STARVATION_DURATION) {
            environment.removeAgent(this);
        } else {
            tryToEat();
            tryToGiveBirth();
            move();
        }
    }
    
    private void tryToEat() {
        Map<Coordinate, Agent> neighbors = environment.getNeighbors(this);
        Agent neighbor;
        for (Coordinate neighborCoordinate : neighbors.keySet()) {
            neighbor = neighbors.get(neighborCoordinate);
            if (neighbor.isEatable()) {
                eat(neighbor);
                starvation = 0;
                return;
            }
        }
        starvation++;
    }
    
    private void eat(Agent fish) {
        fish.removeFromEnvironment();
    }

    @Override
    public Color getColor() {
        return Color.RED;
    }

    @Override
    protected void addChild(Coordinate childCoordinate) {
        SharkAgent shark = new SharkAgent(environment);
        environment.addAgent(shark, childCoordinate);
    }

    @Override
    protected boolean canGiveBirth() {
        return gestation == GESTATION_DURATION;
    }

    @Override
    public void removeFromEnvironment() {
        environment.removeAgent(this);
    }

    @Override
    public boolean isEatable() {
        return false;
    }

    @Override
    public boolean canEat() {
        return true;
    }
}