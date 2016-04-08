package gavehicles.abstracts;

/*

 crossed sensor + aggressive = attack the stimulus
 crossed + passive = admires the stimulus, then leaves
 uncrossed sensor + aggressive = run away from stimulus
 uncrossed + passive = attracted to the stimulus

 */
public abstract class AbstractDriveOutput {

    static final int MAX_OUTPUT = 10;
    protected double leftWheelOutput;
    protected double rightWheelOutput;
    protected double acceleration;

    public AbstractDriveOutput() {
    }

    public double getLeftWheelOutput() {
        return leftWheelOutput;
    }

    public double getRightWheelOutput() {
        return rightWheelOutput;
    }
    
    public double getAcceleration() {
        return acceleration;
    }

    public void setLeftWheelOutput(double nuLeftWheelOutput) {
        if (nuLeftWheelOutput > MAX_OUTPUT) {
            leftWheelOutput = MAX_OUTPUT;
        } else {
            leftWheelOutput = nuLeftWheelOutput;
        }
    }

    public void setRightWheelOutput(double nuRightWheelOutput) {
        if (nuRightWheelOutput > MAX_OUTPUT) {
            rightWheelOutput = MAX_OUTPUT;
        } else {
            rightWheelOutput = nuRightWheelOutput;
        }
    }
    
    public void setAcceleration(double a) {
        this.acceleration = a;
    }

    public abstract AbstractDriveOutput combine(AbstractDriveOutput o, IndividualVehicle v);

    @Override
    public String toString() {
        String returnMe = "I am a DO: ";
        returnMe += "\tleft=" + leftWheelOutput;
        returnMe += "\tright=" + rightWheelOutput;
        return returnMe;
    }

}
