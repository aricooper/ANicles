package gavehicles.vehicles;

import gavehicles.abstracts.AbstractSensor;

public class PreySensor extends AbstractSensor {

    @Override
    public String mySource() {
        return "prey";
    }

}
