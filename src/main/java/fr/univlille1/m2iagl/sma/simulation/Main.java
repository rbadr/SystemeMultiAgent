package fr.univlille1.m2iagl.sma.simulation;

import fr.univlille1.m2iagl.sma.agents.Agent;
import fr.univlille1.m2iagl.sma.agents.BlueParticle;
import fr.univlille1.m2iagl.sma.agents.RedParticle;
import fr.univlille1.m2iagl.sma.environment.Environment;
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
 
public class Main {
    
      private Main() {
          throw new IllegalAccessError("Main class");
      }
    
    public static void main(String[] args) throws InterruptedException, IOException{
        
        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("config.properties");
        prop.load(stream);
        
        int gridSizeX = Integer.parseInt(prop.getProperty("gridSizeX"));
        int gridSizeY = Integer.parseInt(prop.getProperty("gridSizeY"));
        int numberOfBlueParticles = Integer.parseInt(prop.getProperty("nbParticles"))/2;
        int numberOfGreenParticles = Integer.parseInt(prop.getProperty("nbParticles"))/2;
        int canvasSizeX=Integer.parseInt(prop.getProperty("canvasSizeX"));
        int canvasSizeY=Integer.parseInt(prop.getProperty("canvasSizeY"));
        int delay = Integer.parseInt(prop.getProperty("delay"));
        long seed = Integer.parseInt(prop.getProperty("seed"));
        int boxSize = Integer.parseInt(prop.getProperty("boxSize"));
        int nbTicks = Integer.parseInt(prop.getProperty("nbTicks"));
 
        Environment<Agent> environment = new Environment<>(gridSizeX,gridSizeY);
 
        List<Agent> agents = new LinkedList<>();
        for(int i = 0; i < numberOfBlueParticles; i ++) {
            agents.add(new BlueParticle(environment));
        }
        for(int i = 0; i < numberOfGreenParticles; i++) {
            agents.add(new RedParticle(environment));
        }
        environment.initEnvironment(agents, seed);
 
        final SMA<Agent> sma = new SMA<>(environment, delay, nbTicks);
 
        JFrame frame = new MainView("Système multi-agents", sma, canvasSizeX, canvasSizeY, boxSize);
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