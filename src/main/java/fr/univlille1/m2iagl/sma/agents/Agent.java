package fr.univlille1.m2iagl.sma.agents;

import fr.univlille1.m2iagl.sma.environment.Coordinate;
import fr.univlille1.m2iagl.sma.environment.Environment;
import java.awt.Color;

public abstract class Agent implements IAgent, IFoodChain {
    
    protected int breedTime;
    protected int age;
    
    protected Environment<Agent> environment;
    
    public Agent(Environment<Agent> environment) {
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
    
    public abstract void removeFromEnvironment();

    @Override
    public abstract Color getColor();

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}