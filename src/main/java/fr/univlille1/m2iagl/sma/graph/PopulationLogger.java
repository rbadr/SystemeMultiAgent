package fr.univlille1.m2iagl.sma.graph;

import fr.univlille1.m2iagl.sma.agents.Agent;
import fr.univlille1.m2iagl.sma.sma.SMA;
import java.io.FileNotFoundException;

public class PopulationLogger<T extends Agent> extends CSVLogger<T> {
    public PopulationLogger(SMA<T> sma) throws FileNotFoundException {
        super(sma);
    }

    protected void writeLine() {
        StringBuilder builder = new StringBuilder();
        getPopulation(builder);
        logger.trace(builder.toString());
    }
}
