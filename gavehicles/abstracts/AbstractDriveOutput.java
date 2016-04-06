package gavehicles.abstracts;

public abstract class AbstractDriveOutput {

    static final int MAX_OUTPUT = 10;
    protected double leftWheelOutput;
    protected double rightWheelOutput;

    public AbstractDriveOutput() {
    }

    public double getLeftWheelOutput() {
        return leftWheelOutput;
    }

    public double getRightWheelOutput() {
        return rightWheelOutput;
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
    
    public abstract AbstractDriveOutput combine(AbstractDriveOutput o);

    @Override
    public String toString() {
        String returnMe = "I am a DO: ";
        returnMe += "\tleft=" + leftWheelOutput;
        returnMe += "\tright=" + rightWheelOutput;
        return returnMe;
    }

}
