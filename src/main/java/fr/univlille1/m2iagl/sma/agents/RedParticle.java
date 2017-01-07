package fr.univlille1.m2iagl.sma.agents;

import java.awt.*;

public class RedParticle extends Agent {

    public RedParticle() {
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