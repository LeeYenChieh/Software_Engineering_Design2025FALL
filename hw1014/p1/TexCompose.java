package hw1014.p1;

import java.util.ArrayList;

public class TexCompose implements Compose{
    public void compose(ArrayList<Component> components){
        for(Component c : components){
            System.out.printf("[%d]%s", c.natureSize, c.content);
            if(c.content.equals("<ParagraphEnd>"))
                System.out.println();
            else
                System.out.printf(" ");
        }
    }
}
