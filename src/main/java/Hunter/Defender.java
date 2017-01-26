package Hunter;

import core.Agent;
import core.Coordinate;
import core.Environment;
import java.awt.Color;

public class Defender extends Agent {

    public Defender(Environment<Agent> environment) {
        super(environment);
    }

    @Override
    public boolean canStopSimulation() {
        return false;
    }

    @Override
    public void decide() {
        //do nothing.
    }

    @Override
    public Color getColor() {
        return Color.YELLOW;
    }

    @Override
    public boolean isEatable() {
        //conflis avec le hunter qui voudra le manger aussi !! penser à ajouter un nouveau boolean spécifique
        return true;
    }

    @Override
    public boolean canEat() {
        return false;
    }

    @Override
    protected void addChild(Coordinate childCoordinate) {
            }

    @Override
    protected boolean canGiveBirth() {
        return false;   }

    @Override
    public void removeFromEnvironment() {
            }


    
}
