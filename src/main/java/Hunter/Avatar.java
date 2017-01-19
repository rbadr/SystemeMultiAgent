package Hunter;

import fr.univlille1.m2iagl.sma.environment.Coordinate;
import fr.univlille1.m2iagl.sma.environment.Environment;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Avatar extends HunterAvatarAgent {

    public Avatar(Environment<HunterAvatarAgent> environment) {
        super(environment);
    }

    @Override
    public void decide() {
        List<List<Integer>> dijkstra = new ArrayList<>();
        List<HunterAvatarAgent> agents = environment.getAllAgents();
        Coordinate nextMove = null;
        int nextMoveNumber = Integer.MIN_VALUE;
        List<Coordinate> neighborsCoordinates = environment.mooreNeighborhood(environment.getCoordinateOf(this));
        for(HunterAvatarAgent hunter : agents) {
            if(hunter.canEat()) {
                initDijkstraGrid(dijkstra);
                getShortestPath(dijkstra, hunter);
                for(Coordinate c : neighborsCoordinates) {
                    if(dijkstra.get(c.getY()).get(c.getX()) != 0 && dijkstra.get(c.getY()).get(c.getX()) > nextMoveNumber) {
                        nextMoveNumber = dijkstra.get(c.getY()).get(c.getX());
                        nextMove = c;
                    }
                }
            }
        }
        if(nextMove != null) {
            environment.move(this, nextMove);
        }
    }

    @Override
    public Color getColor() {
        return Color.BLUE;
    }

    @Override
    public boolean canStopSimulation() {
        return false;
    }

    @Override
    public boolean isEatable() {
        return true;
    }

    @Override
    public boolean canEat() {
        return false;
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}