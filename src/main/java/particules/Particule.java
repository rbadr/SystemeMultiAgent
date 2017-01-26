package particules;

import core.Agent;
import core.Coordinate;
import core.Environment;
import java.awt.Color;
import java.util.Random;

public class Particule extends Agent {
    
    Random rand = new Random();
    float r = rand.nextFloat();
    float g = rand.nextFloat();
    float b = rand.nextFloat();
    
    public Particule(Environment<Agent> environment) {
        super(environment);
    }
    
    @Override
    public void decide(){
        super.decide();
        this.tryToMove();
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

    @Override
    public Color getColor() {
       return new Color(r, g, b); }

    @Override
    public boolean isEatable() {
        return false;    }

    @Override
    public boolean canEat() {
        return false;    }
    
}
