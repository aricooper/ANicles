package gavehicles.classes;

import gavehicles.abstracts.AbstractSource;
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
        for (int i = 0; i < 2; i++) {
            foodSources.add(new FoodSource(new Point2D.Double(Utilities.randomInt(800), Utilities.randomInt(600)), 5000));
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
        checkForCollisions();
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
    public double getPreyStimulusStrength(Point2D.Double location) {
        double sum = 0;

        for (AbstractSource nextSource : foodSources) {
            double d = location.distance(nextSource.getLocation());
            sum += nextSource.getIntensity() / (d * d);
        }

        for (Evaluable nextVeh : preyPop) {
            double d = location.distance(nextVeh.getLocation());
            sum += 700 / (d * d);
        }

        return sum;
    }

    @Override
    public double getPredStimulusStrength(Point2D.Double location) {
        double sum = 0;

        for (AbstractSource nextSource : foodSources) {
            double d = location.distance(nextSource.getLocation());
            sum += nextSource.getIntensity() / (d * d);
        }

        return sum;
    }

    @Override
    public void completeGeneration() {
        doAGeneration();
        time = 0;
    }

    private void checkForCollisions() {
        checkForPredFood();
        checkForPreyFood();

    }

    private boolean ate(Evaluable prey, Evaluable pred) {
        double x = prey.getLocation().getX();
        double y = prey.getLocation().getY();

        double x1 = pred.getLocation().getX();
        double y1 = pred.getLocation().getY();

        if (Math.abs(x - x1) <= 5) {
            if (Math.abs(y - y1) <= 5) {
                return true;
            }
        }
        return false;
    }

    private void checkForPredFood() {
        for (int i = 0; i < predPop.predSize; i++) {
            if (ate(preyPop.get(i), predPop.get(i))) {
                predPop.get(i).setCollision(true);
            }
        }
    }

    private void checkForPreyFood() {
        for (int i = 0; i < preyPop.preySize; i++) {
            if (feeding(preyPop.get(i), foodSources)) {
                preyPop.get(i).setCollision(true);
            }
        }
    }

    private boolean feeding(Evaluable prey, SourceList foodSources) {
        for (AbstractSource source : foodSources) {
            if (ate(prey, source)) {
                return true;
            }
        }
        return false;
    }

    private boolean ate(Evaluable prey, AbstractSource source) {
        double x = prey.getLocation().getX();
        double y = prey.getLocation().getY();

        double x1 = source.getLocation().getX();
        double y1 = source.getLocation().getY();

        if (Math.abs(x - x1) <= 5) {
            if (Math.abs(y - y1) <= 5) {
                return true;
            }
        }
        return false;
    }

}
