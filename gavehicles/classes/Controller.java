package gavehicles.classes;

import gavehicles.UI.ControlPanel;
import gavehicles.interfaces.Modelable;
import gavehicles.interfaces.Viewable;

public class Controller extends Thread {

    int speed = 10;

    ControlPanel controls;
    private boolean running = false;
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
            if (running) {
                if (theModel.getT() < Utilities.getGenTime()) {
                    step();
                    delay();
                } else {
                    completeGeneration();
                }
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
