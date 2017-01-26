package core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class HunterAvatarAgent implements IAgent, IAgentEndSimulation, IFoodChain{
    
    protected Environment<HunterAvatarAgent> environment;
    
    public HunterAvatarAgent(Environment<HunterAvatarAgent> environment) {
        super();
        this.environment = environment;
    }

    protected void initDijkstraGrid(List<List<Integer>> dijkstraGrid) {
        dijkstraGrid.clear();
        List<List<HunterAvatarAgent>> board = environment.getBoard();
        for(int i = 0, size = board.size(); i < size; i++) {
            List<Integer> distanceValue = new ArrayList<>();
            for(int j = 0; j < size; j++) {
                distanceValue.add(0);
            }
            dijkstraGrid.add(distanceValue);
        }
    }

    protected List<List<Integer>> getShortestPath(List<List<Integer>> dijkstraGrid, HunterAvatarAgent avatar) {
        LinkedList<Coordinate> freeBoxes = new LinkedList<>();
        freeBoxes.add(environment.getCoordinateOf(avatar));
        findDijkstraShortestPaths(dijkstraGrid, freeBoxes);

        return dijkstraGrid;
    }

    private void findDijkstraShortestPaths(List<List<Integer>> dijkstraGrid, LinkedList<Coordinate> freeBoxes) {
        int number;
        while(!freeBoxes.isEmpty()) {
            Coordinate currentCoordinate = freeBoxes.pop();
            number = dijkstraGrid.get(currentCoordinate.getY()).get(currentCoordinate.getX());
            List<Coordinate> coordinates = environment.mooreNeighborhood(currentCoordinate);
            for(Coordinate coordinate : coordinates) {
                if(dijkstraGrid.get(coordinate.getY()).get(coordinate.getX()) == 0 && environment.getAgentAt(coordinate) == null) {
                    dijkstraGrid.get(coordinate.getY()).set(coordinate.getX(), number + 1);
                    freeBoxes.add(coordinate);
                }
            }
        }
    }
    
    protected void moveman(int dirX, int dirY){
        environment.moveNewPosition(this, dirX, dirY);
    }

    @Override
    public boolean canStopSimulation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
