package fr.univlille1.m2iagl.sma.graph;


import fr.univlille1.m2iagl.sma.agents.Agent;
import fr.univlille1.m2iagl.sma.sma.SMA;
import java.io.FileNotFoundException;

public class TimeLogger<T extends Agent> extends CSVLogger<T> {
     public TimeLogger(SMA<T> sma) throws FileNotFoundException {
        super(sma);
    }

    protected void writeLine() {
        long chronons = sma.getNbTicks();
        StringBuilder builder = new StringBuilder(chronons + " ");
        getPopulation(builder);
        logger.trace(builder.toString());
    }
}
