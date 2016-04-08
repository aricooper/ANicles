package gavehicles.classes;

import gavehicles.abstracts.AbstractSource;
import gavehicles.abstracts.IndividualVehicle;
import gavehicles.interfaces.Evaluable;
import gavehicles.interfaces.Modelable;
import gavehicles.interfaces.Viewable;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import gavehicles.lists.SourceList;

public class VehicleModel implements Modelable {

    int time;
    Population preyPop, predPop;
    SourceList foodSources;
    Viewable theWorld;

    public VehicleModel() {
        initPop();
        initSources();
    }

    public VehicleModel(Viewable v) {
        theWorld = v;
        initPop();
        initSources();
    }

    private void initPop() {
        preyPop = new Population(false, theWorld);
        predPop = new Population(true, theWorld);
    }

    private void initSources() {
        foodSources = new SourceList();
        for (int i = 0; i < MyUtilities.getFood(); i++) {
            foodSources.add(
                    new FoodSource(
                            new Point2D.Double(
                                    MyUtilities.randomInt(MyUtilities.getWidth()),
                                    MyUtilities.randomInt(MyUtilities.getHeight())
                            ),
                            MyUtilities.randomInt(5000, 10000)
                    )
            );
        }
    }

    @Override
    public void reset() {
        time = 0;
        initPop();
        initSources();
    }

    @Override
    public void step() {
        time++;
        evaluateFitness();
    }

    public void doAGeneration() {
        preyPop.doAGeneration();
        predPop.doAGeneration();
    }

    public void evaluateFitness() {
        preyPop.step();
        preyPop.update();
        predPop.step();
        predPop.update();
    }

    @Override
    public void init() {
        System.out.println("VehicleModel:init");
    }

    @Override
    public void finish() {
        doAGeneration();
        time = 0;
    }

    @Override
    public int getT() {
//        System.out.println("VehicleModel:getT");
        return time;
    }

    @Override
    public void paint(Graphics g) {
        foodSources.paint(g);
        preyPop.paint(g);
        predPop.paint(g);
    }

    @Override
    public void completeGeneration() {
        doAGeneration();
        time = 0;
    }

    @Override
    public double getPreyStimulusStrength(Point2D.Double location, IndividualVehicle v) {
        double sum = 0;

        for (Evaluable nextVeh : preyPop) {
            double d = location.distance(nextVeh.getLocation());
            MyUtilities.debug("sum: " + (v.getPreySense() / (d * d)));
            sum += v.getPreySense() / (d * d);
        }

        return sum;
    }

    @Override
    public double getPredStimulusStrength(Point2D.Double location, IndividualVehicle v) {
        double sum = 0;

        for (Evaluable nextVeh : predPop) {
            double d = location.distance(nextVeh.getLocation());
            sum += v.getPredSense() / (d * d);
        }

        return sum;
    }

    @Override
    public double getFoodStimulusStrength(Point2D.Double location, IndividualVehicle v) {
        double sum = 0;

        for (AbstractSource nextSource : foodSources) {
            double d = location.distance(nextSource.getLocation());
            sum += nextSource.getIntensity() / (2.5 * (d * d));
        }

        return sum;
    }

}
