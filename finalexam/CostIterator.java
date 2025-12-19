import java.util.ArrayList;

public class CostIterator extends Iterator {
    public CostIterator(ArrayList<Component> components){
        super(components);
    }

    public <T> T next(){
        Component c = super.next();
        return (T) Double.valueOf(c.cost);
    }
}
