package core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Random;
import java.util.Set;

public class Environment<T extends IAgent> extends Observable {

    private List<List<T>> board;
    private Map<T, Coordinate> agents;

    private List<T> agentsList;
    
    private Set<T> theDead;

    public Environment(int gridSizeX, int gridSizeY) {
        agents = new HashMap<>();
        agentsList = new LinkedList<>();
        board = new ArrayList<>();
        theDead = new HashSet<>();
        List<T> xCoordinate;
        for (int y = 0; y < gridSizeY; y++) {
            xCoordinate = new ArrayList<>();
            for (int x = 0; x < gridSizeX; x++) {
                xCoordinate.add(null);
            }
            board.add(xCoordinate);
        }
    }

    public void initEnvironment(List<T> agents, long seed) {
        Coordinate coordinate;
        for (T agent : agents) {
            coordinate = findFreeBox(seed);
            addAgent(agent, coordinate);
        }
        setChanged();
    }

    public Coordinate findFreeBox(T agent) {
        Coordinate coordinate = agents.get(agent);
        List<Coordinate> possibilities = mooreNeighborhood(coordinate);
        Collections.shuffle(possibilities);
        for (Coordinate c : possibilities) {
            if (freeBox(c)) {
                return c;
            }
        }
        return null;
    }
    
    public Coordinate findNextPosition(T agent,int xDirection, int yDirection){
        Coordinate coordinate = agents.get(agent);
        Coordinate nextPosition = new Coordinate(coordinate.getX()+xDirection,coordinate.getY()+yDirection);
        return nextPosition;
    }

    public Coordinate findFreeBox(long seed) {
        int size = board.size();
        if (agentsList.size() == size * size) {
            return null;
        }
        
        Random r = new Random(seed);
        Coordinate coordinate;
        do {
            int x = r.nextInt(size);
            int y = r.nextInt(size);
            coordinate = new Coordinate(x, y);
        } while (!freeBox(coordinate));
        return coordinate;
    }

    public T getAgentAt(Coordinate coordinate) {
        return board.get(coordinate.getY()).get(coordinate.getX());
    }

    public Coordinate getCoordinateOf(T agent) {
        return agents.get(agent);
    }

    public List<Coordinate> mooreNeighborhood(Coordinate coordinate) {
        int size = board.size();
        List<Coordinate> possibilities = new LinkedList<>();
        Coordinate currentCoordinate;
        for (int yCoord = coordinate.getY(), y = Math.max(0, yCoord - 1); y <= yCoord + 1 && y < size; y++) {
            for (int xCoord = coordinate.getX(), x = Math.max(0, xCoord - 1); x <= xCoord + 1 && x < size; x++) {
                currentCoordinate = new Coordinate(x, y);
                if (currentCoordinate != coordinate) {
                    possibilities.add(currentCoordinate);
                }
            }
        }
        return possibilities;
    }

    public List<T> getAllAgents() {
        List<T> agentsCopy = new LinkedList<>();
        agentsList.forEach(agentsCopy::add); 
        Collections.shuffle(agentsCopy);
        return agentsCopy;
    }
    
    public boolean isDead(T agent) {
        return theDead.contains(agent);
    }
    
    public void clearDead() {
        theDead.clear();
    }

    public Map<Coordinate, T> getNeighbors(T agent) {
        Coordinate coordinate = agents.get(agent);

        List<Coordinate> neighborsCoordinate = mooreNeighborhood(coordinate);
        Collections.shuffle(neighborsCoordinate);

        Map<Coordinate, T> neighbors = new HashMap<>();
        T neighbor;
        for (Coordinate neighborCoordinate : neighborsCoordinate) {
            neighbor = getAgentAt(neighborCoordinate);
            if (neighbor != null) {
                neighbors.put(neighborCoordinate, neighbor);
            }
        }
        return neighbors;
    }

    public Map<Coordinate, T> getAllNeighbors(T agent) {
        Coordinate coordinate = agents.get(agent);

        List<Coordinate> neighborsCoordinate = mooreNeighborhood(coordinate);
        Collections.shuffle(neighborsCoordinate);

        Map<Coordinate, T> neighbors = new HashMap<>();
        T neighbor;
        for (Coordinate neighborCoordinate : neighborsCoordinate) {
            neighbor = getAgentAt(neighborCoordinate);
            if (neighbor != null) {
                neighbors.put(neighborCoordinate, neighbor);
            } else {
            	neighbors.put(neighborCoordinate, null);
            }
        }
        return neighbors;
    }

    private boolean freeBox(Coordinate coordinate) {
        return getAgentAt(coordinate) == null;
    }

    public void addAgent(T agent, Coordinate coordinate) {
        agents.put(agent, coordinate);
        agentsList.add(agent);
        setBoardBox(coordinate, agent);
        setChanged();
    }
    
    public void removeAgent(T agent) {
        Coordinate agentCoordinate = agents.get(agent);
        setBoardBox(agentCoordinate, null);
        agentsList.remove(agent);
        agents.remove(agent);
        theDead.add(agent);
        setChanged();
    }
    

    private void setBoardBox(Coordinate agentCoordinate, T agent) {
        board.get(agentCoordinate.getY()).set(agentCoordinate.getX(), agent);
    }

    public void move(T agent, Coordinate newPosition) {
        if (agents.containsKey(agent)) {
            Coordinate previousPosition = agents.get(agent);
            agents.put(agent, newPosition);
            setBoardBox(previousPosition, null);
            setBoardBox(newPosition, agent);
            setChanged();
        }
    }
    
    public void moveNewPosition(T agent, int x, int y){
            Coordinate previousPosition = agents.get(agent);
            Coordinate next = null;
            
            
            if(previousPosition.getX()+x==100 || previousPosition.getX()+x==-1 || previousPosition.getY()+y==-1 || previousPosition.getY()+y==100 ){
                next = previousPosition;
                agents.put(agent, next);
            setBoardBox(previousPosition, null);
            setBoardBox(next, agent);
            setChanged();
            } else {
            Coordinate futurePosition = new Coordinate(previousPosition.getX()+x,previousPosition.getY()+y);
            T wall = getAgentAt(futurePosition);
            if(wall!=null){
             next = previousPosition;
             agents.put(agent, next);
            setBoardBox(previousPosition, null);
            setBoardBox(next, agent);
            setChanged();   
            } else{
            next = new Coordinate(previousPosition.getX()+x,previousPosition.getY()+y);
            agents.put(agent, next);
            setBoardBox(previousPosition, null);
            setBoardBox(next, agent);
            setChanged();   
            }
            }
        
    }

    public List<List<T>> getBoard() {
        return board;
    }
    
    public Map<Color, Integer> getAgentGroupedByColor() {
        Color currentColor;
        int nbAgentsForColor;
        Map<Color, Integer> counter = new HashMap<>();
        for(T agent : agentsList) {
            currentColor = agent.getColor();
            if(counter.containsKey(currentColor)) {
                nbAgentsForColor = counter.get(currentColor) + 1;
            }
            else {
                nbAgentsForColor = 1;
            }
            counter.put(currentColor, nbAgentsForColor);
        }
        return counter;
    }
}