import java.util.ArrayList;

public class CreatePowerConsumptionIterator implements CreateIterator {
    public CreatePowerConsumptionIterator(){}

    public Iterator create(ArrayList<Component> components){
        return new PowerConsumptionIterator(components);
    }
}
