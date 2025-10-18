import java.util.ArrayList;

public class SimpleCompose implements Compose{
    public void compose(ArrayList<Component> components){
        for(Component c : components){
            System.out.printf("[%d]%s\n", c.natureSize, c.content);
        }
    }
}
