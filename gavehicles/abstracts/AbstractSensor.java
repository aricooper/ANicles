package gavehicles.abstracts;

import gavehicles.interfaces.Viewable;
import java.awt.geom.Point2D;

public abstract class AbstractSensor {

    public boolean crossed;

    public boolean getCrossed() {
        return crossed;
    }

    public void setCrossed(boolean c) {
        crossed = c;
    }

    public abstract double getStimulusStrength(Viewable world, IndividualVehicle v, Point2D.Double sensorLocation);
    
    abstract public String mySource();

}
