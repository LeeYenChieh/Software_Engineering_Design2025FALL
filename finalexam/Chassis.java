import java.util.ArrayList;

public class Chassis extends Component{
    public ArrayList<Component> components;
    public CreateIterator createIterator;

    public Chassis(String name, double powerConsumption, double cost){
        super(name, powerConsumption, cost);
        this.components = new ArrayList<Component>();
    }

    public void addComponent(Component c){
        this.components.add(c);
    }

    public void setCreateIterator(CreateIterator createIterator){
        this.createIterator = createIterator;
    }

    public Iterator create(){
        return this.createIterator.create(this.components);
    }

    public double sumPowerConsumption(){
        double result = this.powerConsumption;
        this.setCreateIterator(new CreatePowerConsumptionIterator());
        Iterator it = this.create();
        while (it.hasNext()) 
            result += (double) it.next();
        return result;
    }

    public double sumCost(){
        double result = this.cost;
        this.setCreateIterator(new CreateCostIterator());
        Iterator it = this.create();
        while (it.hasNext()) 
            result += (double) it.next();
        return result;
    }
}
