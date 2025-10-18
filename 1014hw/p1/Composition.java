import java.util.ArrayList;

public class Composition {
    public ArrayList<Component> components;
    Compose composeMode;

    public Composition(){
        this.components = new ArrayList<Component>();
    }

    public void changeSize(int id, int newSize){
        for(Component c : this.components){
            if(c.id == id){
                if(newSize < c.shrinkAbility || c.stretchAbility < newSize)
                    System.out.printf("component %d failed to change size\n", c.id);
                else{
                    System.out.printf("component %d size changed to %d\n", c.id, newSize);
                    c.natureSize = newSize;
                }
                return;
            }
        }
        return;
    }

    public void setComposeMode(Compose composeMode){
        this.composeMode = composeMode;
    }

    public void arrange(){
        this.composeMode.compose(this.components);
    }

    public void addComponent(Component component){
        this.components.add(component);
    }
}
