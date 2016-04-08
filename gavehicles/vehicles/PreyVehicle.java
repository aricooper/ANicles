package gavehicles.vehicles;

import gavehicles.abstracts.AbstractDriveOutput;
import gavehicles.abstracts.AbstractSensor;
import gavehicles.abstracts.IndividualVehicle;
import gavehicles.classes.MyUtilities;
import gavehicles.classes.PreyTraitDeterminer;
import gavehicles.interfaces.Evaluable;
import gavehicles.interfaces.Viewable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

public class PreyVehicle extends IndividualVehicle {

    public PreyVehicle() {
        super();
        this.preySense = MyUtilities.randomInt(3000);
        this.predSense = 20000;
    }

    public PreyVehicle(Point2D.Double location, double orientation) {
        this();
        this.location = location;
        this.orientation = orientation;
        setupSensors();
    }

    private void setupSensors() {
        FoodSensor foodSensor = new FoodSensor();
        foodSensor.setCrossed(false);
        addSensor(foodSensor);

//        PreySensor preySensor = new PreySensor();
//        preySensor.setCrossed(false);
//        addSensor(preySensor);
//
//        PredSensor predSensor = new PredSensor();
//        predSensor.setCrossed(false);
//        addSensor(predSensor);
    }

    @Override
    public AbstractDriveOutput generateOutput(Viewable world) {
        AbstractDriveOutput returnMe = new PassiveDriveOutput();

        for (AbstractSensor nextSensor : sensors) {
            double right = nextSensor.getStimulusStrength(world, this, rightSensorLocation());
            double left = nextSensor.getStimulusStrength(world, this, leftSensorLocation());
            if (nextSensor.getCrossed()) {
                returnMe = returnMe.combine(nextSensor.createDriveOutput(right, left, this), this); //crossed
            } else {
                returnMe = returnMe.combine(nextSensor.createDriveOutput(left, right, this), this);
            }
        }
        System.out.println("left:" + returnMe.getLeftWheelOutput());
        System.out.println("right: " + returnMe.getRightWheelOutput());
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
        // add to Individual's fitness
    }

    @Override
    public String toString() {
        String returnMe = "PreyVehicle: location = " + getLocation() + " orientation = " + getOrientation() + "\n";
        for (byte next : DNA) {
            returnMe += next;
        }
        return returnMe;
    }

    @Override
    public void determineTraits() {
        System.out.println("PreyVehicle:determineTraits");
        PreyTraitDeterminer.determine(this);
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

    @Override
    public void moveIt(AbstractDriveOutput theOutput) {
        double leftOutput = theOutput.getLeftWheelOutput();
        double rightOutput = theOutput.getRightWheelOutput();
        double direction = this.getOrientation();

//        double distance = (leftOutput + rightOutput) / 2;
//        double dx = distance * Math.cos(direction);
//        double dy = -distance * Math.sin(direction);

        double x = this.getLocation().getX();
        double y = this.getLocation().getY();
        

//        this.setLocation(new Point2D.Double(x + dx, y + dy));

        double deltaDirection = ((rightOutput - leftOutput) / this.getSize()) * (Math.PI / 8);
        this.setOrientation(direction + deltaDirection);
        
        vX += theOutput.getAcceleration() * Math.cos(direction);
        vY += theOutput.getAcceleration() * Math.sin(direction);
        
        this.setLocation(new Point2D.Double(x + vX, y + vY));
    }

}
