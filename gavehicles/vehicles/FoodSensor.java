package gavehicles.vehicles;

import gavehicles.abstracts.AbstractSensor;

public class FoodSensor extends AbstractSensor {

    @Override
    public String mySource() {
        return "food";
    }
    
}
