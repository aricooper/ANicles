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

public class PreyVehicle extends IndividualVehicle {
    
    int FOOD_WEIGHT = 1;
    int dangerSense, herding, poisonous;

    public PreyVehicle() {
        super();
    }

    public PreyVehicle(int fit, byte[] dna) {
        super();
        fitness = fit;
        this.DNA = dna;

    }

    public PreyVehicle(Point2D.Double location, double orientation, boolean crossed) {
        this();
        this.location = location;
        this.orientation = orientation;
        FoodSensor foodSensor = new FoodSensor();
        foodSensor.setCrossed(crossed);
        addSensor(foodSensor);
    }

    @Override
    public AbstractDriveOutput generateOutput(Viewable world) {
        PreyDriveOutput returnMe = new PreyDriveOutput();

        double right = world.getPreyStimulusStrength(rightSensorLocation());
        double left = world.getPreyStimulusStrength(leftSensorLocation());

        if (sensors.size() > 1) {
            System.out.println("okay... time to generalize PreyVehicle:step to sum all the drives!!");
            assert (false);
        }

        for (AbstractSensor nextSensor : sensors) {
            if (nextSensor.getCrossed()) {
                returnMe = new PreyDriveOutput(right, left, this);  // backwards
            } else {
                returnMe = new PreyDriveOutput(left, right, this);
            }

        }
        return returnMe;
    }

    private Point2D.Double rightSensorLocation() {
        double dx = getSize() / 2 * Math.cos(getOrientation() - Math.PI / 4);
        double dy = -getSize() / 2 * Math.sin(getOrientation() - Math.PI / 4);
        return new Point2D.Double(getX() + dx * 2, getY() + dy * 2);
    }

    private Point2D.Double leftSensorLocation() {
        double dx = getSize() / 2 * Math.cos(getOrientation() + Math.PI / 4);
        double dy = -getSize() / 2 * Math.sin(getOrientation() + Math.PI / 4);
        return new Point2D.Double(getX() + dx * 2, getY() + dy * 2);
    }

    @Override
    public void step(Viewable world) {
        evaluateMyFitness();
    }

    @Override
    public String toString() {
        return "PreyVehicle: location = " + getLocation() + " orientation = " + getOrientation();
    }

    @Override
    public void determineTraits() {
    int[] traits = PredTraitDeterminer.getValue(this);
        dangerSense = traits[0];
        herding = traits[1];
        poisonous = traits[2];    }

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
        int fit = fitness;
        return new PreyVehicle(fit, DNA.clone());
    }

    @Override
    public void paint(Graphics g) {
        int x = (int) getX();
        int y = (int) getY();
        g.setColor(Color.black);

        g.drawOval(x - size / 2, y - size / 2, size, size);

        g.setColor(Color.green);
        Point2D.Double left = leftSensorLocation();
        g.drawLine(x, y, (int) left.getX(), (int) left.getY());
        Point2D.Double right = rightSensorLocation();
        g.drawLine(x, y, (int) right.getX(), (int) right.getY());
    }

    @Override
    public void setCollision(boolean b) {
        collision = b;
    }

    private void evaluateMyFitness() {
        if (eating()) {
            fitness += FOOD_WEIGHT;
        }
    }

    private boolean eating() {
        return collision;
    }

}
