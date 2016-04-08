package gavehicles.classes;

import gavehicles.abstracts.AbstractDriveOutput;
import gavehicles.vehicles.PreyVehicle;
import gavehicles.interfaces.Evaluable;
import gavehicles.interfaces.Viewable;
import gavehicles.lists.EvaluableList;
import gavehicles.vehicles.PredVehicle;
import java.awt.Graphics;
import java.awt.geom.Point2D;

public class Population extends EvaluableList {

    Viewable theWorld;
    EvaluableList matingPool, nextGen;
    int preySize = 8;
    int predSize = 8;

    public Population() {
    }

    public Population(boolean pred) {
        if (pred) {
            for (int i = 0; i < predSize; i++) {
                this.add(new PredVehicle(new Point2D.Double(Utilities.randomInt(1200), Utilities.randomInt(800)), Utilities.randomDouble(2) * Math.PI, false));
            }
        } else {
            for (int i = 0; i < preySize; i++) {
                this.add(new PreyVehicle(new Point2D.Double(Utilities.randomInt(1200), Utilities.randomInt(800)), Utilities.randomDouble(2) * Math.PI, false));
            }
        }
    }

    public Population(boolean pred, Viewable v) {
        this(pred);
        theWorld = v;
    }

    public void doAGeneration() {
        selectMatingPool();
        applyGeneticOperators();
        replacement();
    }

    public EvaluableList getPopulation() {
        return this;
    }

    public void selectMatingPool() {
        System.out.println("Population:selectMatingPool");
    }

    public void applyGeneticOperators() {
        System.out.println("Population:applyGeneticOperators");
    }

    public void replacement() {
        System.out.println("Population:replacement");
    }

    public void step() {
        for (Evaluable nextVehicle : this) {
            nextVehicle.step(theWorld);
        }
    }

    public void update() {
        for (Evaluable nextVehicle : this) {
            AbstractDriveOutput theOutput = nextVehicle.generateOutput(theWorld);
            moveIt(nextVehicle, theOutput);
        }
    }

    private void moveIt(Evaluable theVehicle, AbstractDriveOutput theOutput) {
        double leftOutput = theOutput.getLeftWheelOutput();
        double rightOutput = theOutput.getRightWheelOutput();
        double direction = theVehicle.getOrientation();

        double distance = (leftOutput + rightOutput) / 2;
        double dx = distance * Math.cos(direction);
        double dy = -distance * Math.sin(direction);
        double x = theVehicle.getLocation().getX();
        double y = theVehicle.getLocation().getY();
        theVehicle.setLocation(new Point2D.Double(x + dx, y + dy));

        double deltaDirection = ((rightOutput - leftOutput) / theVehicle.getSize()) * (Math.PI / 8);
        theVehicle.setOrientation(direction + deltaDirection);
    }

    @Override
    public String toString() {
        String returnMe = "I am a Population:";
        returnMe += "\n\tContaining:";
        for (Evaluable next : this) {
            returnMe += "\n\t\t" + next.toString();
        }
        return returnMe;
    }

    public void paint(Graphics g) {
        for (Evaluable e : this) {
            e.paint(g);
        }
    }

}
