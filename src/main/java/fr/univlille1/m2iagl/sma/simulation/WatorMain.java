package fr.univlille1.m2iagl.sma.simulation;

import fr.univlille1.m2iagl.sma.agents.Agent;
import fr.univlille1.m2iagl.sma.agents.Fish;
import fr.univlille1.m2iagl.sma.agents.Shark;
import fr.univlille1.m2iagl.sma.environment.Environment;
import fr.univlille1.m2iagl.sma.graph.PopulationLogger;
import fr.univlille1.m2iagl.sma.graph.TimeLogger;
import fr.univlille1.m2iagl.sma.sma.SMA;
import fr.univlille1.m2iagl.sma.view.MainView;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import javax.swing.JFrame;
 
public class WatorMain {
    
      private WatorMain() {
          throw new IllegalAccessError("Main class");
      }
    
    public static void main(String[] args) throws InterruptedException, IOException{
        
        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("config.properties");
        prop.load(stream);
        
        int gridSizeX = Integer.parseInt(prop.getProperty("gridSizeX"));
        int gridSizeY = Integer.parseInt(prop.getProperty("gridSizeY"));
        int numberOfParticles = Integer.parseInt(prop.getProperty("nbParticles"));
        int numberOfSharks = Integer.parseInt(prop.getProperty("numberOfSharks"));
        int numberOfFish = Integer.parseInt(prop.getProperty("numberOfFish"));
        int canvasSizeX=Integer.parseInt(prop.getProperty("canvasSizeX"));
        int canvasSizeY=Integer.parseInt(prop.getProperty("canvasSizeY"));
        int delay = Integer.parseInt(prop.getProperty("delay"));
        long seed = Integer.parseInt(prop.getProperty("seed"));
        int boxSize = Integer.parseInt(prop.getProperty("boxSize"));
        int nbTicks = Integer.parseInt(prop.getProperty("nbTicks"));
        int fishBreedTime = Integer.parseInt(prop.getProperty("fishBreedTime")); 
        int sharkBreedTime = Integer.parseInt(prop.getProperty("sharkBreedTime")); 
        int sharkStarveTime = Integer.parseInt(prop.getProperty("sharkStarveTime")); 
 
        Fish.setGestationDuration(fishBreedTime);
        Shark.setGestationDuration(sharkBreedTime);
        Shark.setStarvationDuration(sharkStarveTime);
        
        Environment<Agent> environment = new Environment<>(gridSizeX,gridSizeY);
 
        List<Agent> agents = new LinkedList<>();
        for(int i = 0; i < numberOfFish; i ++) {
            agents.add(new Fish(environment));
        }
        for(int i = 0; i < numberOfSharks; i++) {
            agents.add(new Shark(environment));
        }
        environment.initEnvironment(agents, seed);
 
        final SMA<Agent> sma = new SMA<>(environment, delay, nbTicks);
        
        new TimeLogger<Agent>(sma);
        new PopulationLogger<Agent>(sma);
 
        JFrame frame = new MainView("Wator Simulation", sma, canvasSizeX, canvasSizeY, boxSize);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
 
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                sma.endSimulation(true);
            }
        });
        sma.run();
    }
}