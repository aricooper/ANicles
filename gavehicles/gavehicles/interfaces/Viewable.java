package gavehicles.interfaces;

import java.awt.geom.Point2D;

public interface Viewable {

    public void display();

    public double getPreyStimulusStrength(Point2D.Double sensorLocation);

    public double getPredStimulusStrength(Point2D.Double rightSensorLocation);

}
