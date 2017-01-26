package Hunter;

import core.Agent;
import core.Coordinate;
import core.Environment;
import java.awt.Color;

public class Winner extends Agent {

    public Winner(Environment<Agent> environment) {
        super(environment);
    }


    @Override
    public boolean canStopSimulation() {
        return false;
    }

    @Override
    public void decide() {
          }

    @Override
    public Color getColor() {
        return Color.GREEN;    
    }

    @Override
    public boolean isEatable() {
        return true; }

    @Override
    public boolean canEat() {
        return false;    }

    @Override
    protected void addChild(Coordinate childCoordinate) {
            }

    @Override
    protected boolean canGiveBirth() {
        return false;    }

    @Override
    public void removeFromEnvironment() {
           }
    
}
