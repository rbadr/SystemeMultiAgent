package Hunter;

import fr.univlille1.m2iagl.sma.environment.Environment;
import java.awt.Color;

public class Defender extends HunterAvatarAgent {

    public Defender(Environment<HunterAvatarAgent> environment) {
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


    
}
