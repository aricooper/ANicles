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

    public String toString() {
        return "myD" + super.toString();
    }

}
