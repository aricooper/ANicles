package gavehicles.abstracts;

import gavehicles.interfaces.Viewable;
import java.awt.geom.Point2D;

public abstract class AbstractSensor {
    
    public int weight;

    public boolean crossed;

    public boolean getCrossed() {
        return crossed;
    }
    
    public void setWeight(int w) {
        this.weight = w;
    }

    public void setCrossed(boolean c) {
        crossed = c;
    }
    
    public int getWeight() {
        return weight;
    }

    public abstract double getStimulusStrength(Viewable world, IndividualVehicle v, Point2D.Double sensorLocation);
    
    abstract public String mySource();

    public abstract AbstractDriveOutput createDriveOutput(double right, double left, IndividualVehicle v);

}
