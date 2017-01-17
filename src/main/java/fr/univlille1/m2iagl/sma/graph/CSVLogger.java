package fr.univlille1.m2iagl.sma.graph;

import fr.univlille1.m2iagl.sma.agents.Agent;
import fr.univlille1.m2iagl.sma.environment.Environment;
import fr.univlille1.m2iagl.sma.sma.SMA;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class CSVLogger<T extends Agent> implements Observer {

    protected final SMA<T> sma;
    protected final Logger logger;

    public CSVLogger(SMA<T> sma) throws FileNotFoundException {
        this.sma = sma;
        sma.getEnvironment().addObserver(this);
        logger = LogManager.getLogger(this.getClass());
    }

    @Override
    public void update(Observable observable, Object o) {
        writeLine();
    }

    protected void getColorPopulation(StringBuilder builder) {
        Environment environment = sma.getEnvironment();
        Map<Color, Integer> agentColors = environment.getAgentGroupedByColor();
        int nb;
        for(Color color : agentColors.keySet()) {
            nb = agentColors.get(color);
            builder.append(nb).append(" ");
        }
    }

    protected abstract void writeLine();
}