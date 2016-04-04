package gavehicles.abstracts;

import gavehicles.classes.Utilities;
import gavehicles.interfaces.Evaluable;
import gavehicles.interfaces.Viewable;
import gavehicles.lists.SensorList;
import java.awt.Graphics;
import java.awt.geom.Point2D;

public abstract class IndividualVehicle implements Evaluable {

    public byte[] DNA;
    public int fitness;

    public Point2D.Double location;
    public double orientation;
    public double velocity;
    public SensorList sensors;
    public int size = 30;
    public double maxSpeed = 5.0;
    public double baseSpeed = 1.0;

    public IndividualVehicle() {
        sensors = new SensorList();
        DNA = new byte[Evaluable.getLength()];
        for (int i = 0; i < Evaluable.getLength(); i++) {
            DNA[i] = (byte) (Utilities.randomInt(2));
        }
    }

    public IndividualVehicle(byte[] DNA) {
        this();
        this.DNA = DNA;
    }

    public abstract void determineTraits();

    public void addSensor(AbstractSensor s) {
        sensors.add(s);
    }

    @Override
    public abstract AbstractDriveOutput generateOutput(Viewable world);

    public double getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public double getOrientation() {
        return orientation;
    }

    @Override
    public Point2D.Double getLocation() {
        return location;
    }

    public double getX() {
        return location.getX();
    }

    public double getY() {
        return location.getY();
    }

    @Override
    public void setOrientation(double nuOrientation) {
        orientation = nuOrientation;
    }

    @Override
    public void setLocation(Point2D.Double nuLocation) {
        location = nuLocation;
    }

    @Override
    public int getSize() {
        return size;
    }

    public void setCrossed(boolean b) {
        for (AbstractSensor next : sensors) {
            next.crossed = b;
        }
    }

    @Override
    public abstract void paint(Graphics g);

    @Override
    public String toString() {
        String returnMe = "I am an Individual:";
        return returnMe;
    }

}
