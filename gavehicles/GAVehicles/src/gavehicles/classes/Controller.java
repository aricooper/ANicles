package gavehicles.classes;

import gavehicles.UI.ControlPanel;
import gavehicles.interfaces.Modelable;
import gavehicles.interfaces.Viewable;

public class Controller extends Thread {

    int speed = 10;

    ControlPanel controls;
    public boolean running, stepping = false;
    Viewable theView;
    Modelable theModel;

    public Controller(Viewable theView) {
        this.theView = theView;
        controls = new ControlPanel(this);
        theModel = new VehicleModel(theView);
    }

    @Override
    public void run() {
        for (;;) {
            if (running || stepping) {
                if (theModel.getT() < MyUtilities.getGenTime()) {
                    step();
                    delay();
                } else {
                    completeGeneration();
                }
                stepping = false;
            }
            delay();
        }
    }

    private void delay() {
        try {
            Thread.sleep(speed);
        } catch (Exception ex) {
        }
    }

    public void toggleRunning() {
        running = !running;
    }

    public void toggleStepping() {
        stepping = !stepping;
    }

    private void step() {
        theModel.step();
        theView.display();
    }

    public void setStep() {
        running = false;
    }

    public Modelable getModel() {
        return theModel;
    }

    public boolean getRunning() {
        return running;
    }

    public void reset() {
        running = false;
        theModel.reset();
        theView.display();
    }

    public void completeGeneration() {
        theModel.completeGeneration();
        theView.display();
    }

}
