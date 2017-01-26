package core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class Agent implements IAgent, IFoodChain, IAgentEndSimulation {
    
    protected int breedTime;
    protected int age;
    
    protected Environment<Agent> environment;
    
    public Agent(Environment<Agent> environment) {
        super();
        this. environment = environment;
    }
    
    @Override
    public void decide() {
        age++;
    }
    
    protected void tryToGiveBirth() {
        if (this.canGiveBirth()) {
            Coordinate childCoordinate = environment.findFreeBox(this);
            if (childCoordinate != null) {
                this.addChild(childCoordinate);
            }
            this.breedTime = 0;
        }
        else {
            breedTime++;
        }
    }
    
    protected abstract void addChild(Coordinate childCoordinate);
    
    protected abstract boolean canGiveBirth();

    protected void tryToMove() {
        Coordinate newPosition = environment.findFreeBox(this);
        if (newPosition != null) {
            environment.move(this, newPosition);
        }
    }
    
    protected void moveToDirection(){
        Coordinate newPosition = environment.findNextPosition(this,1,0);
        if (newPosition != null) {
            environment.move(this, newPosition);
        } else{
            Coordinate reversePosition = environment.findNextPosition(this,-1,0);
            environment.move(this, reversePosition);
        }
    }
    
    public abstract void removeFromEnvironment();

    @Override
    public abstract Color getColor();

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean canStopSimulation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    protected void initDijkstraGrid(List<List<Integer>> dijkstraGrid) {
        dijkstraGrid.clear();
        List<List<Agent>> board = environment.getBoard();
        for(int i = 0, size = board.size(); i < size; i++) {
            List<Integer> distanceValue = new ArrayList<>();
            for(int j = 0; j < size; j++) {
                distanceValue.add(0);
            }
            dijkstraGrid.add(distanceValue);
        }
    }

    protected List<List<Integer>> getShortestPath(List<List<Integer>> dijkstraGrid, Agent avatar) {
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
}