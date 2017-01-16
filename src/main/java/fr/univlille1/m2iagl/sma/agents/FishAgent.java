package fr.univlille1.m2iagl.sma.agents;

import fr.univlille1.m2iagl.sma.environment.Coordinate;
import fr.univlille1.m2iagl.sma.environment.Environment;
import java.awt.*;

public class FishAgent extends Agent {
    
    private static int GESTATION_DURATION = 2;

    public FishAgent(Environment<Agent> environment) {
        super(environment);
    }

    @Override
    public void decide() {
        super.decide();
        this.move();
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
        FishAgent fishAgent = new FishAgent(environment);
        environment.addAgent(fishAgent, childCoordinate);
    }

    @Override
    public boolean canGiveBirth() {
        return gestation == GESTATION_DURATION;
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
        GESTATION_DURATION = gestationDuration;
    }

    @Override
    public boolean canEat() {
        return false;
    }
}