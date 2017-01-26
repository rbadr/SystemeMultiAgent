package Hunter;

import core.Agent;
import core.Coordinate;
import core.Environment;
import java.awt.*;

public class Mur extends Agent {

    public Mur(Environment<Agent> environment) {
        super(environment);
    }

    @Override
    public boolean isEatable() {
        return false;
    }

    @Override
    public boolean canStopSimulation() {
        return true;
    }

    @Override
    public void decide() {}

    @Override
    public Color getColor() {
        return Color.BLACK;
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
        return false;    }

    @Override
    public void removeFromEnvironment() {
           }


}
