package hunter;

import core.Agent;
import core.Coordinate;
import core.Environment;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Hunter extends Agent {

    int thicks = 0;
    int speedRatio;
    public Hunter(Environment<Agent> environment,int speedRatio) {
        super(environment);
        this.speedRatio = (int) Math.floor(speedRatio);
    }

    @Override
    public boolean canStopSimulation() {
        return true;
    }

    @Override
    public void decide() {
        thicks=thicks+1;
        if(thicks%speedRatio==0){
            List<List<Integer>> dijkstra = new ArrayList<>();
        List<Agent> agents = environment.getAllAgents();
        Coordinate nextMove = null;
        int nextMoveNumber = Integer.MAX_VALUE;
        List<Coordinate> neighborsCoordinates = environment.mooreNeighborhood(environment.getCoordinateOf(this));

        for(Agent avatar : agents) {
            if(avatar.isEatable()) {
                initDijkstraGrid(dijkstra);
                getShortestPath(dijkstra, avatar);
                for(Coordinate c : neighborsCoordinates) {
                    if(dijkstra.get(c.getY()).get(c.getX()) != 0 && dijkstra.get(c.getY()).get(c.getX()) < nextMoveNumber) {
                        nextMoveNumber = dijkstra.get(c.getY()).get(c.getX());
                        nextMove = c;
                    }
                }
            }
        }
        if(nextMove != null) {
            environment.move(this, nextMove);
            
            for(Agent neighbor : environment.getNeighbors(this).values()) {
                if(neighbor.isEatable()) {
                    environment.removeAgent(neighbor);
                }
            }
        }
        }
        
        
    }

    @Override
    public Color getColor() {
        return Color.RED;
    }

    @Override
    public boolean isEatable() {
        return false;
    }

    @Override
    public boolean canEat() {
        return true;
    }

    @Override
    protected void addChild(Coordinate childCoordinate) {
          }

    @Override
    protected boolean canGiveBirth() {
        return false;
            }

    @Override
    public void removeFromEnvironment() {
            }

}