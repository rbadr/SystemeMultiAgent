package Hunter;

import fr.univlille1.m2iagl.sma.environment.Coordinate;
import fr.univlille1.m2iagl.sma.environment.Environment;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Avatar extends HunterAvatarAgent implements KeyListener {

    private int dirX,dirY;
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

    public void run() {
           }
    
    @Override
     public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                dirY = -1;
                dirX = 0;
                break;
            case KeyEvent.VK_RIGHT:
                dirY = 1;
                dirX = 0;
                break;
            case KeyEvent.VK_UP:
                dirX = -1;
                dirY = 0;
                break;
            case KeyEvent.VK_DOWN:
                dirX = 1;
                dirY = 0;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
          }

    @Override
    public void keyReleased(KeyEvent e) {
         }
}