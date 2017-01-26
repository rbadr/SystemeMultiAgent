package Hunter;

import core.HunterAvatarAgent;
import core.Coordinate;
import core.Environment;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Hunter extends HunterAvatarAgent {

    int t = 0;
    public Hunter(Environment<HunterAvatarAgent> environment) {
        super(environment);
    }

    @Override
    public boolean canStopSimulation() {
        return true;
    }

    @Override
    public void decide() {
        t=t+1;
        if(t%2==0){
            List<List<Integer>> dijkstra = new ArrayList<>();
        List<HunterAvatarAgent> agents = environment.getAllAgents();
        Coordinate nextMove = null;
        int nextMoveNumber = Integer.MAX_VALUE;
        List<Coordinate> neighborsCoordinates = environment.mooreNeighborhood(environment.getCoordinateOf(this));

        for(HunterAvatarAgent avatar : agents) {
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
            
            for(HunterAvatarAgent neighbor : environment.getNeighbors(this).values()) {
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

}