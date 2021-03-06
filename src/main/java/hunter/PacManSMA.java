package hunter;

import core.Agent;
import core.Coordinate;
import core.Environment;
import java.util.LinkedList;
import java.util.List;


public class PacManSMA extends HunterAvatarSMA<Agent> {
    private Avatar avatar;
    public PacManSMA(Environment<Agent> environment, int delay) {
        super(environment, delay);
    }

    public void init(int nbPredators, int percentageObstacles, int SpeedHunter) {
        List<Agent> agents = new LinkedList<>();
        this.avatar=new Avatar(environment);
        for (int i = 0; i < 1; i++) {
            agents.add(this.avatar);
        }
        for (int i = 0; i < nbPredators; i++) {
            agents.add(new Hunter(environment,SpeedHunter));
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
    
    public Avatar getAvatar(){
        return this.avatar;
    }
}
