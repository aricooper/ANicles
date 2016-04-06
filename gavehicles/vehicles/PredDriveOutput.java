package gavehicles.vehicles;

import gavehicles.abstracts.AbstractDriveOutput;

public class PredDriveOutput extends AbstractDriveOutput {

    public PredDriveOutput() {
    }

    public PredDriveOutput(double left, double right, PredVehicle v) {
        double l = v.getBaseSpeed();
        double r = v.getBaseSpeed();
        double ml = v.getMaxSpeed();
        double mr = v.getMaxSpeed();

        if (left + l < ml) {
            this.setLeftWheelOutput(left + l);
        } else {
            this.setLeftWheelOutput(ml);
        }
        if (right + r < mr) {
            this.setRightWheelOutput(right + r);
        } else {
            this.setRightWheelOutput(mr);
        }

    }

    @Override
    public String toString() {
        return "myD" + super.toString();
    }

    @Override
    public AbstractDriveOutput combine(AbstractDriveOutput o) {
        PredDriveOutput returnMe = new PredDriveOutput();
        double left = o.getLeftWheelOutput() + this.getLeftWheelOutput();
        double right = o.getRightWheelOutput() + this.getRightWheelOutput();
        returnMe.setLeftWheelOutput(left);
        returnMe.setRightWheelOutput(right);
        return returnMe;
    }

}
