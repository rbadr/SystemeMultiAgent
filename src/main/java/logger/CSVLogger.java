package logger;

import core.Agent;
import core.Environment;
import core.SMA;
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

    protected void getPopulation(StringBuilder builder) {
        Environment environment = sma.getEnvironment();
        Map<Color, Integer> agentColors = environment.getAgentGroupedByColor();
        int nbFishs;
        int nbBornFishs;
        int nbSharks;
        int nbBornSharks;
        
        if(!agentColors.containsKey(Color.BLUE)){
            nbFishs = 0;
        } else nbFishs = agentColors.get(Color.BLUE); 
        
        if(!agentColors.containsKey(Color.GREEN)){
            nbBornFishs = 0;
        } else nbBornFishs = agentColors.get(Color.GREEN);
        
        if(!agentColors.containsKey(Color.RED)){
            nbSharks = 0;
        } else nbSharks = agentColors.get(Color.RED);
        
        if(!agentColors.containsKey(Color.PINK)){
            nbBornSharks = 0;
        } else nbBornSharks = agentColors.get(Color.PINK); 

        builder.append(nbFishs+nbBornFishs).append(" ").append(nbSharks+nbBornSharks);

    }

    protected abstract void writeLine();
}