package gavehicles.vehicles;

import gavehicles.abstracts.AbstractDriveOutput;
import gavehicles.abstracts.AbstractSensor;
import gavehicles.abstracts.IndividualVehicle;
import gavehicles.interfaces.Evaluable;
import gavehicles.interfaces.Viewable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

public class PredVehicle extends IndividualVehicle {
    
    int preySense, foodSense;
    
    public PredVehicle() {
        super();
        this.maxSpeed = 8;
    }

    public PredVehicle(Point2D.Double location, double orientation, boolean crossed) {
        this();
        this.location = location;
        this.orientation = orientation;
        PreySensor preySensor = new PreySensor();
        preySensor.setCrossed(!crossed);
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

        double right = world.getPredStimulusStrength(rightSensorLocation(), this);
        double left = world.getPredStimulusStrength(leftSensorLocation(), this);

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
    }

    @Override
    public String toString() {
        return "ProtoVehicle: location = " + getLocation() + " orientation = " + getOrientation();
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setFitness(int fitness) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Evaluable myClone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    double getBaseSpeed() {
        return baseSpeed;
    }
    
}