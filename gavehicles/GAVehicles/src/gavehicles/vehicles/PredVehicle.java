package gavehicles.vehicles;

import gavehicles.abstracts.AbstractDriveOutput;
import gavehicles.abstracts.AbstractSensor;
import gavehicles.abstracts.IndividualVehicle;
import gavehicles.classes.MyUtilities;
import gavehicles.interfaces.Evaluable;
import gavehicles.interfaces.Viewable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

public class PredVehicle extends IndividualVehicle {

    public PredVehicle() {
        super();
        this.preySense = 15000;
        this.predSense = MyUtilities.randomInt(3000);
    }

    public PredVehicle(Point2D.Double location, double orientation) {
        this();
        this.location = location;
        this.orientation = orientation;
        setupSensors();
    }

    private void setupSensors() {
//        FoodSensor foodSensor = new FoodSensor();
//        foodSensor.setCrossed(false);
//        addSensor(foodSensor);

//        PreySensor preySensor = new PreySensor();
//        preySensor.setCrossed(true);
//        addSensor(preySensor);
//
//        PredSensor predSensor = new PredSensor();
//        predSensor.setCrossed(false);
//        addSensor(predSensor);
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
    public AbstractDriveOutput generateOutput(Viewable world) {
        AbstractDriveOutput returnMe = new AggressiveDriveOutput();

        for (AbstractSensor nextSensor : sensors) {
            double right = nextSensor.getStimulusStrength(world, this, rightSensorLocation());
            double left = nextSensor.getStimulusStrength(world, this, leftSensorLocation());
            if (nextSensor.getCrossed()) {
                returnMe = returnMe.combine(nextSensor.createDriveOutput(right, left, this), this); //crossed
            } else {
                returnMe = returnMe.combine(nextSensor.createDriveOutput(left, right, this), this);
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

}
