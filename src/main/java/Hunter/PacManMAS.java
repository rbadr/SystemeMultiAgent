package Hunter;

import fr.univlille1.m2iagl.sma.environment.Coordinate;
import fr.univlille1.m2iagl.sma.environment.Environment;
import java.util.LinkedList;
import java.util.List;


public class PacManMAS extends HunterAvatarSMA<HunterAvatarAgent> {
    
    public PacManMAS(Environment<HunterAvatarAgent> environment, int delay) {
        super(environment, delay);
    }

    public void init(int nbPreys, int nbPredators, int percentageObstacles) {
        List<HunterAvatarAgent> agents = new LinkedList<>();
        for (int i = 0; i < nbPreys; i++) {
            agents.add(new Avatar(environment));
        }
        for (int i = 0; i < nbPredators; i++) {
            agents.add(new Hunter(environment));
        }

        int nbObstacles = (int) Math.pow(environment.getBoard().size(), 2) * percentageObstacles / 100;
        Mur obstacle;
        Coordinate obstacleCoordinate;

        for(int i = 0; i < nbObstacles; i++) {
            obstacle = new Mur(environment);
            obstacleCoordinate = environment.findFreeBox(0);
            environment.addAgent(obstacle, obstacleCoordinate);
        }
        environment.initEnvironment(agents,0);
    }
}
