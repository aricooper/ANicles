package gavehicles.vehicles;

import gavehicles.abstracts.AbstractDriveOutput;
import gavehicles.abstracts.AbstractSensor;
import gavehicles.abstracts.IndividualVehicle;
import gavehicles.classes.PredTraitDeterminer;
import gavehicles.interfaces.Evaluable;
import gavehicles.interfaces.Viewable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

public class PredVehicle extends IndividualVehicle {

    int baseSpeed = 3;
    int FOOD_WEIGHT = 3;
    int speed, appetite, sight;
    
    
   
    
    public PredVehicle() {
        super();
    }
    
    public PredVehicle(int fit, byte[] dna) {
        super();
        fitness = fit;
        this.DNA = dna;
        
    }

    public PredVehicle(Point2D.Double location, double orientation, boolean crossed) {
        this();
        this.location = location;
        this.orientation = orientation;
        PreySensor preySensor = new PreySensor();
        preySensor.setCrossed(crossed);
        addSensor(preySensor);
    }

    @Override
    public void paint(Graphics g) {
        int x = (int) getX();
        int y = (int) getY();
        g.setColor(Color.black);

        g.drawOval(x - size / 2, y - size / 2, size, size);

        g.setColor(Color.RED);
        Point2D.Double left = leftSensorLocation();
        g.drawLine(x, y, (int) left.getX(), (int) left.getY());
        Point2D.Double right = rightSensorLocation();
        g.drawLine(x, y, (int) right.getX(), (int) right.getY());
    }

    @Override
    public double getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public AbstractDriveOutput generateOutput(Viewable world) {
        PredDriveOutput returnMe = new PredDriveOutput();

        double right = world.getPreyStimulusStrength(rightSensorLocation());
        double left = world.getPreyStimulusStrength(leftSensorLocation());

        if (sensors.size() > 1) {
            System.out.println("okay... time to generalize PredVehicle:step to sum all the drives!!");
            assert (false);
        }

        for (AbstractSensor nextSensor : sensors) {
            if (nextSensor.getCrossed()) {
                returnMe = new PredDriveOutput(right, left, this);  // backwards
            } else {
                returnMe = new PredDriveOutput(left, right, this);
            }

        }
        return returnMe;
    }

    private Point2D.Double rightSensorLocation() {
        double dx = getSize() * Math.cos(getOrientation() - Math.PI / 4);
        double dy = -getSize() * Math.sin(getOrientation() - Math.PI / 4);
        return new Point2D.Double(getX() + dx * 2, getY() + dy * 2);
    }

    private Point2D.Double leftSensorLocation() {
        double dx = getSize() * Math.cos(getOrientation() + Math.PI / 4);
        double dy = -getSize() * Math.sin(getOrientation() + Math.PI / 4);
        return new Point2D.Double(getX() + dx * 2, getY() + dy * 2);
    }

    @Override
    public void step(Viewable world) {
        evaluateMyFitness();
    }

    @Override
    public String toString() {
        return "ProtoVehicle: location = " + getLocation() + " orientation = " + getOrientation();
    }

    @Override
    public void determineTraits() {
        int[] traits = PredTraitDeterminer.getValue(this);
        speed = traits[0];
        appetite = traits[1];
        sight = traits[2];
        
    }

    @Override
    public byte[] getDNA() {
        return DNA;
    }

    @Override
    public int getFitness() {
        return fitness;
    }

    @Override
    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    @Override
    public Evaluable myClone() {
        return new PredVehicle(fitness, DNA.clone());
    }

    double getBaseSpeed() {
        return baseSpeed;
    }

    private void evaluateMyFitness() {
        if (atePrey()) {
            fitness += FOOD_WEIGHT;
        }
        
    }

    private boolean atePrey() {
        return collision;
    }

    @Override
    public void setCollision(boolean b) {
        collision = b;
    }
    
}