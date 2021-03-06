package gavehicles.interfaces;

import java.awt.Graphics;
import java.awt.geom.Point2D;

public interface Modelable {
    
    public void step();

    public void init();

    public int getT();

    public void paint(Graphics g);

    public void finish();

    public double getPreyStimulusStrength(Point2D.Double sensorLocation);

    public void reset();

    public double getPredStimulusStrength(Point2D.Double sensorLocation);

    public void completeGeneration();
    
}
