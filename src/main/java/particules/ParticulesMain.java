package particules;

import core.Agent;
import core.Environment;
import core.SMA;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import javax.swing.JFrame;
import vue.MainView;
 
public class ParticulesMain {
    
      private ParticulesMain() {
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
        int canvasSizeX=Integer.parseInt(prop.getProperty("canvasSizeX"));
        int canvasSizeY=Integer.parseInt(prop.getProperty("canvasSizeY"));
        int delay = Integer.parseInt(prop.getProperty("delay"));
        long seed = Integer.parseInt(prop.getProperty("seed"));
        int boxSize = Integer.parseInt(prop.getProperty("boxSize"));
        int nbTicks = Integer.parseInt(prop.getProperty("nbTicks"));
        
        Environment<Agent> environment = new Environment<>(gridSizeX,gridSizeY);
 
        List<Agent> agents = new LinkedList<>();
        for(int i = 0; i < numberOfParticles; i ++) {
            agents.add(new Particule(environment));
        }
        environment.initEnvironment(agents, seed);
 
        final SMA<Agent> sma = new SMA<>(environment, delay, nbTicks);
 
        JFrame frame = new MainView("Particules Simulation", sma, canvasSizeX, canvasSizeY, boxSize);
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