package gavehicles.vehicles;

import gavehicles.abstracts.AbstractDriveOutput;
import gavehicles.abstracts.AbstractSensor;
import gavehicles.abstracts.IndividualVehicle;
import gavehicles.interfaces.Evaluable;
import gavehicles.interfaces.Viewable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

public class PreyVehicle extends IndividualVehicle {

    public PreyVehicle() {
        super();
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
        double dx = getSize()/2 * Math.cos(getOrientation() - Math.PI / 4);
        double dy = -getSize()/2 * Math.sin(getOrientation() - Math.PI / 4);
        return new Point2D.Double(getX() + dx * 2, getY() + dy * 2);
    }

    private Point2D.Double leftSensorLocation() {
        double dx = getSize()/2 * Math.cos(getOrientation() + Math.PI / 4);
        double dy = -getSize()/2 * Math.sin(getOrientation() + Math.PI / 4);
        return new Point2D.Double(getX() + dx * 2, getY() + dy * 2);
    }

    @Override
    public void step(Viewable world) {
        // add to Individual's fitness
    }

    @Override
    public String toString() {
        return "PreyVehicle: location = " + getLocation() + " orientation = " + getOrientation();
    }

    @Override
    public void determineTraits() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public byte[] getDNA() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

}
