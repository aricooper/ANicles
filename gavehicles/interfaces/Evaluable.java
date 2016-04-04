package gavehicles.interfaces;

import gavehicles.abstracts.AbstractDriveOutput;
import java.awt.Graphics;
import java.awt.geom.Point2D;

public interface Evaluable {

    static int LENGTH = 100;

    public static int getLength() {
        return LENGTH;
    }

    // Vehicle methods needed
    
    public void paint(Graphics g);

    public double getOrientation();

    public Point2D.Double getLocation();

    public void setLocation(Point2D.Double aDouble);

    public void setOrientation(double d);

    public int getSize();

    public AbstractDriveOutput generateOutput(Viewable theWorld);

    public void step(Viewable world);
    
    // Genetic Algorithm methods needed
    
    public byte[] getDNA();

    public int getFitness();

    public void setFitness(int fitness);

    public Evaluable myClone();

}
