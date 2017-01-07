package fr.univlille1.m2iagl.sma.simulation;

import fr.univlille1.m2iagl.sma.agents.Agent;
import fr.univlille1.m2iagl.sma.agents.BlueParticle;
import fr.univlille1.m2iagl.sma.agents.RedParticle;
import fr.univlille1.m2iagl.sma.environment.Environment;
import fr.univlille1.m2iagl.sma.sma.SMA;
import fr.univlille1.m2iagl.sma.view.MainView;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
 
public class Main {
    
    public static void main(String[] args) throws InterruptedException{
         int gridSizeX = 100;
         int gridSizeY = 100;
         int numberOfBlueParticles = 50;
         int numberOfGreenParticles = 50;
         int canvasSizeX=800;
         int canvasSizeY=600;
         int delay = 50;
 
         Environment<Agent> environment = new Environment<>(gridSizeX,gridSizeY);
 
         List<Agent> agents = new LinkedList<>();
         for(int i = 0; i < numberOfBlueParticles; i ++) {
             agents.add(new BlueParticle(environment));
         }
         for(int i = 0; i < numberOfGreenParticles; i++) {
             agents.add(new RedParticle(environment));
         }
         environment.initEnvironment(agents);
 
         final SMA<Agent> sma = new SMA<>(environment, delay);
 
         JFrame frame = new MainView("Système multi-agents", sma, canvasSizeX, canvasSizeY);
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