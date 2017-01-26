package Hunter;


import core.IAgentEndSimulation;
import core.Environment;
import core.SMA;
import java.util.List;

public class HunterAvatarSMA<T extends IAgentEndSimulation> extends SMA<T> {

	public HunterAvatarSMA(Environment<T> environment, int delay) {
		super(environment, delay,0);
	}

	@Override
	public void startAgentTour() {
        super.startAgentTour();
        if(!simulationEnded) {
            List<T> agents = environment.getAllAgents();
            boolean canStop = true;
            int i = 0;
            int size = agents.size();
            T agent;
            while (i < size && canStop) {
                agent = agents.get(i);
                if (!agent.canStopSimulation()) {
                    canStop = false;
                }
                i++;
            }
            simulationEnded = canStop;
        }
	}
}
