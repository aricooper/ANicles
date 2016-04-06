package gavehicles.vehicles;

import gavehicles.abstracts.AbstractSensor;
import gavehicles.abstracts.IndividualVehicle;
import gavehicles.interfaces.Viewable;
import java.awt.geom.Point2D;

public class PreySensor extends AbstractSensor {

    @Override
    public String mySource() {
        return "prey";
    }

    @Override
    public double getStimulusStrength(Viewable world, IndividualVehicle v, Point2D.Double sensorLocation) {
        return world.getPreyStimulusStrength(v, sensorLocation);
    }

}
