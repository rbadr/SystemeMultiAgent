package fr.univlille1.m2iagl.sma.view;

import fr.univlille1.m2iagl.sma.sma.SMA;
import java.awt.*;
import javax.swing.*;

public class MainView extends JFrame {
    
    public MainView(String title, SMA mas, int canvasSizeX, int canvasSizeY, int boxSize){
        super(title);

        EnvironmentView environmentView = new EnvironmentView(mas.getEnvironment(), boxSize);
        environmentView.setPreferredSize(new Dimension(canvasSizeX, canvasSizeY));

        JPanel myJPanel = new JPanel();
        myJPanel.setLayout(new BorderLayout());

        myJPanel.add(environmentView, BorderLayout.CENTER);

        this.setContentPane(myJPanel);
        this.setSize(new Dimension(canvasSizeX, canvasSizeY));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
