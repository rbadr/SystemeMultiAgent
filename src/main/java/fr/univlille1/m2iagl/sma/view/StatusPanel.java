package fr.univlille1.m2iagl.sma.view;

import fr.univlille1.m2iagl.sma.agents.Agent;
import fr.univlille1.m2iagl.sma.environment.Environment;
import fr.univlille1.m2iagl.sma.sma.SMA;
import java.awt.*;
import java.util.*;
import javax.swing.*;


public class StatusPanel extends JPanel implements Observer {

        private SMA sma;
        private JLabel chrononsLabel;
        private Map<Color, JLabel> counterLabels;

    public StatusPanel(SMA sma) {
        super();
        this.sma = sma;

        // Subscribe to Environment Observation
        Environment<Agent> environment = sma.getEnvironment();
        environment.addObserver(this);

        // Change layout
        GridLayout layout = new GridLayout(0, 2);
        setLayout(layout);

        // Create Labels
        add(new JLabel("Thicks : ")); // Add empty column
        chrononsLabel = new JLabel(String.valueOf(sma.getNbTicks()));
        add(chrononsLabel);


        // Compute colors
        Set<Color> colors = new HashSet<>();
        for(Agent agent : environment.getAllAgents()) {
            colors.add(agent.getColor());
        }

        // Create a label for each color
        Map<Color, JLabel> colorLabels = new HashMap<>();
        counterLabels = new HashMap<>();
        for(Color color : colors) {
            colorLabels.put(color, new JLabel());
            counterLabels.put(color, new JLabel());
        }

        // Add labels to Panel
        JLabel colorLabel;
        for(Color color : colors) {
            colorLabel = colorLabels.get(color);
            colorLabel.setOpaque(true);
            colorLabel.setBackground(color);
            add(colorLabel);
            add(counterLabels.get(color));
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        Environment<Agent> environment = sma.getEnvironment();
        chrononsLabel.setText(String.valueOf(sma.getNbTicks()));

        Map<Color, Integer> counter = environment.getAgentGroupedByColor();

        // Update each JLabel
        for(Color color: counter.keySet()) {
            counterLabels.get(color).setText(String.valueOf(counter.get(color)));
        }
    }
}
