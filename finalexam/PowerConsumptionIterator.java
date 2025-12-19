import java.util.ArrayList;

public class PowerConsumptionIterator extends Iterator {
    public PowerConsumptionIterator(ArrayList<Component> components){
        super(components);
    }

    public <T> T next(){
        Component c = super.next();
        return (T) Double.valueOf(c.powerConsumption);
    }
}
