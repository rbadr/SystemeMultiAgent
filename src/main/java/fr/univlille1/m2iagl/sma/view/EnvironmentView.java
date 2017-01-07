package fr.univlille1.m2iagl.sma.view;

import fr.univlille1.m2iagl.sma.agents.IAgent;
import fr.univlille1.m2iagl.sma.environment.Environment;
import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

public class EnvironmentView extends JPanel implements Observer {

    private Environment environment;

    public EnvironmentView(Environment environment) {
        super();
        this.environment = environment;
        environment.addObserver(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        List<List<IAgent>> board = environment.getBoard();

        int height = board.size();
        int width = board.get(0).size();

        int boxHeight = getHeight() / height;
        int boxWidth = getWidth() / width;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (board.get(y).get(x) != null) {
                    g.setColor(board.get(y).get(x).getColor());
                } else {
                	g.setColor(Color.LIGHT_GRAY);
                }
                g.fillOval(x * boxWidth, y * boxHeight, boxWidth, boxHeight);
            }
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        this.repaint();
    }
}
