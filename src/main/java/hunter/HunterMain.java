package hunter;

import core.Agent;
import core.Environment;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.JFrame;
import vue.MainView;

public class HunterMain {

    public static void main(String[] args) throws InterruptedException, IOException {
                
        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("config.properties");
        prop.load(stream);
        
        int gridSizeX = Integer.parseInt(prop.getProperty("gridSizeX"));
        int gridSizeY = Integer.parseInt(prop.getProperty("gridSizeY"));
        int canvasSizeX=Integer.parseInt(prop.getProperty("canvasSizeX"));
        int canvasSizeY=Integer.parseInt(prop.getProperty("canvasSizeY"));
        int delay = Integer.parseInt(prop.getProperty("delay"));
        int boxSize = Integer.parseInt(prop.getProperty("boxSize"));
	int NbHunters = Integer.parseInt(prop.getProperty("NbHunters"));
	int WallsPercent = Integer.parseInt(prop.getProperty("WallsPercent"));
        int SpeedAvatar = Integer.parseInt(prop.getProperty("SpeedAvatar"));
        int SpeedHunter = Integer.parseInt(prop.getProperty("SpeedHunter"));


        Environment<Agent> environment = new Environment<>(gridSizeX,gridSizeY);
        final PacManSMA sma = new PacManSMA(environment, delay);
        sma.init(NbHunters, WallsPercent, SpeedAvatar/SpeedHunter);

        JFrame frame = new MainView("Système multi-agents", sma, canvasSizeX, canvasSizeY, boxSize);
        frame.addKeyListener(sma.getAvatar());
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
