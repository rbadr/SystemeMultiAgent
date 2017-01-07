package fr.univlille1.m2iagl.sma.agents;

import fr.univlille1.m2iagl.sma.environment.Coordinate;
import fr.univlille1.m2iagl.sma.environment.Environment;
import java.awt.*;

public abstract class Agent implements IAgent {

    protected Environment<Agent> environment;
    
    public Agent(Environment<Agent> environment) {
        this. environment = environment;
    }

    protected void move() {
        Coordinate newPosition = environment.findFreeBox(this);
        if (newPosition != null) {
            environment.move(this, newPosition);
        }
    }
    
    public abstract Color getColor();
}