package gavehicles.vehicles;

import gavehicles.abstracts.AbstractDriveOutput;

public class PredDriveOutput extends AbstractDriveOutput {

    public PredDriveOutput() {
    }

    public PredDriveOutput(double left, double right, PredVehicle v) {
        double l = v.getBaseSpeed();
        double r = v.getBaseSpeed();

//        if (left < l) {
//            this.setLeftWheelOutput(left);
//        } else {
//            this.setLeftWheelOutput(l);
//        }
//        if (right < r) {
//            this.setLeftWheelOutput(right);
//        } else {
//            this.setLeftWheelOutput(r);
//        }
        this.setLeftWheelOutput(l + left);
        this.setRightWheelOutput(r + right);
    }

    @Override
    public String toString() {
        return "myD" + super.toString();
    }

}
