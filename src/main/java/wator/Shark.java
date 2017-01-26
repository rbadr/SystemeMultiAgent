package wator;

import core.Agent;
import core.Coordinate;
import core.Environment;
import java.awt.*;
import java.util.Map;

public class Shark extends Agent {
    
    private static int sharkStarveTime = 3;
    private static int sharkBreedTime = 8;
    private int starvation;

    public Shark(Environment<Agent> environment) {
        super(environment);
    }

    @Override
    public void decide() {
        super.decide();
        if (starvation >= sharkStarveTime) {
            environment.removeAgent(this);
        } else {
            tryToEat();
            tryToGiveBirth();
            tryToMove();
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
        if(age < 2)
            return Color.PINK;
        return Color.RED;
    }

    @Override
    protected void addChild(Coordinate childCoordinate) {
        Shark shark = new Shark(environment);
        environment.addAgent(shark, childCoordinate);
    }

    @Override
    protected boolean canGiveBirth() {
        return breedTime == sharkBreedTime;
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
    
    public static void setGestationDuration(int gestationDuration) {
        Shark.sharkBreedTime = gestationDuration;
    }

    public static void setStarvationDuration(int starvationDuration) {
        Shark.sharkStarveTime = starvationDuration;
    }
}