package fr.univlille1.m2iagl.sma.agents;

import fr.univlille1.m2iagl.sma.environment.Environment;
import java.awt.*;

public class RedParticle extends Agent {

    public RedParticle(Environment<Agent> environment) {
        super(environment);
    }

    @Override
    public void decide() {
        this.move();
    }

    @Override
    public Color getColor() {
        return Color.RED;
    }
}