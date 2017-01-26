package vue;

import core.SMA;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class TimePanel extends JPanel {

	private final SMA sma;

    public TimePanel(SMA sma) {
        super();
        this.sma = sma;

        final JButton startPause = new JButton("Pause");
        startPause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(TimePanel.this.sma.simulationEnded()) {
                    try {
                        TimePanel.this.sma.run();
                        startPause.setText("Pause");
                    } catch (InterruptedException e) {
                        System.exit(-1);
                    }
                }
                else {
                    TimePanel.this.sma.endSimulation(true);
                    startPause.setText("Start");
                }
            }
        });

        JButton stop = new JButton("Stop");
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TimePanel.this.sma.endSimulation(true);
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(TimePanel.this);
                topFrame.dispatchEvent(new WindowEvent(topFrame, WindowEvent.WINDOW_CLOSING));
            }
        });

        this.add(startPause);
        this.add(stop);
    }
}
