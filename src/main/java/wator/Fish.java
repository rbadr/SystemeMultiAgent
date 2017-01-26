package wator;

import core.Agent;
import core.Coordinate;
import core.Environment;
import java.awt.*;

public class Fish extends Agent {
    
    private static int fishBreedTime = 2;

    public Fish(Environment<Agent> environment) {
        super(environment);
    }

    @Override
    public void decide() {
        super.decide();
        this.tryToMove();
        this.tryToGiveBirth();
    }

    @Override
    public Color getColor() {
        if(age < 2)
            return Color.GREEN;
        return Color.BLUE;
    }

    @Override
    public void addChild(Coordinate childCoordinate) {
        Fish fishAgent = new Fish(environment);
        environment.addAgent(fishAgent, childCoordinate);
    }

    @Override
    public boolean canGiveBirth() {
        return breedTime == fishBreedTime;
    }

    @Override
    public boolean isEatable() {
        return true;
    }
    
    @Override
    public void removeFromEnvironment() {
        environment.removeAgent(this);
    }

    public static void setGestationDuration(int gestationDuration) {
        fishBreedTime = gestationDuration;
    }

    @Override
    public boolean canEat() {
        return false;
    }
}