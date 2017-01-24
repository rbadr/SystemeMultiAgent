package Hunter;

import fr.univlille1.m2iagl.sma.environment.Environment;
import fr.univlille1.m2iagl.sma.view.MainView;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.JFrame;

public class HunterMain {

    public static void main(String[] args) throws InterruptedException, IOException {
        int numberOfPreys = 5;
	int numberOfPredators = 1;
	int percentageOfObstacles = 1;
                
        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("config.properties");
        prop.load(stream);
        
        int gridSizeX = Integer.parseInt(prop.getProperty("gridSizeX"));
        int gridSizeY = Integer.parseInt(prop.getProperty("gridSizeY"));
        int canvasSizeX=Integer.parseInt(prop.getProperty("canvasSizeX"));
        int canvasSizeY=Integer.parseInt(prop.getProperty("canvasSizeY"));
        int delay = Integer.parseInt(prop.getProperty("delay"));
        long seed = Integer.parseInt(prop.getProperty("seed"));
        int boxSize = Integer.parseInt(prop.getProperty("boxSize"));
        int nbTicks = Integer.parseInt(prop.getProperty("nbTicks"));


        Environment<HunterAvatarAgent> environment = new Environment<>(gridSizeX,gridSizeY);
        final PacManMAS sma = new PacManMAS(environment, delay);
        sma.init(numberOfPreys, numberOfPredators, percentageOfObstacles);

        JFrame frame = new MainView("Système multi-agents", sma, canvasSizeX, canvasSizeY, boxSize);
        frame.setVisible(true);

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
