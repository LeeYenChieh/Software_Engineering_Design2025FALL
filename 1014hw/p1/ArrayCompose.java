import java.util.ArrayList;

public class ArrayCompose implements Compose{
    public void compose(ArrayList<Component> components){
        int cnt = 0;
        for(Component c : components){
            if(cnt % 3 == 0 && cnt != 0)
                System.out.println();
            else if(cnt % 3 != 0)
                System.out.printf(" ");
            System.out.printf("[%d]%s", c.natureSize, c.content);
            cnt += 1;
        }
        System.out.println();
    }
}