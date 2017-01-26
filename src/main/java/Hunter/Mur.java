package Hunter;

import core.HunterAvatarAgent;
import core.Environment;
import java.awt.*;

public class Mur extends HunterAvatarAgent {

    public Mur(Environment<HunterAvatarAgent> environment) {
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


}
