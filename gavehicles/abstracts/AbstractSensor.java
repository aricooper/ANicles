package gavehicles.abstracts;

public abstract class AbstractSensor {

    public boolean crossed;

    public boolean getCrossed() {
        return crossed;
    }

    public void setCrossed(boolean c) {
        crossed = c;
    }

    abstract public String mySource();

}
