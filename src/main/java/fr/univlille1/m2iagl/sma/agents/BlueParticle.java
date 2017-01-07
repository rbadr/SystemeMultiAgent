package fr.univlille1.m2iagl.sma.agents;

import java.awt.*;

public class BlueParticle extends Agent {

    public BlueParticle() {
    }

    @Override
    public void decide() {
        this.move();
    }

    @Override
    public Color getColor() {
        return Color.BLUE;
    }

}