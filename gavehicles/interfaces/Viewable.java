package gavehicles.interfaces;

import gavehicles.vehicles.PredVehicle;
import gavehicles.vehicles.PreyVehicle;
import java.awt.geom.Point2D;

public interface Viewable {

    public void display();

    public double getPreyStimulusStrength(Point2D.Double sensorLocation, PreyVehicle v);

    public double getPredStimulusStrength(Point2D.Double rightSensorLocation, PredVehicle v);

}
