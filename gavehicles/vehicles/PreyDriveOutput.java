package gavehicles.vehicles;

import gavehicles.abstracts.AbstractDriveOutput;

public class PreyDriveOutput extends AbstractDriveOutput {

    public PreyDriveOutput() {
    }

    public PreyDriveOutput(double left, double right, PreyVehicle v) {
        double l = v.getMaxSpeed();
        double r = v.getMaxSpeed();

        l = l - left;
        r = r - right;

        this.setLeftWheelOutput(l);
        this.setRightWheelOutput(r);
    }

    
    // Method to sum different DriveOutputs.
    // Written by Paul Schot
    @Override
    public AbstractDriveOutput combine(AbstractDriveOutput o) {
        PreyDriveOutput returnMe = new PreyDriveOutput();
        double left = o.getLeftWheelOutput() + this.getLeftWheelOutput();
        double right = o.getRightWheelOutput() + this.getRightWheelOutput();
        returnMe.setLeftWheelOutput(left);
        returnMe.setRightWheelOutput(right);
        return returnMe;
    }

    @Override
    public String toString() {
        return "myD" + super.toString();
    }

}
