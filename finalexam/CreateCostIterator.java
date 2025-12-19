import java.util.ArrayList;

public class CreateCostIterator implements CreateIterator {
    public CreateCostIterator(){}

    public Iterator create(ArrayList<Component> components){
        return new CostIterator(components);
    }
}
