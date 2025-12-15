package hw1014.p2;

import java.util.ArrayList;

public class Client {
    
    public ArrayList<Presentation> presentations;

    public Client(){
        this.presentations = new ArrayList<Presentation>();
    }

    public void changeData(String chartType, String key, int val){
        for(Presentation p : this.presentations){
            if(p.type.equals(chartType)){
                System.out.printf("%s change %s %d.\n", chartType, key, val);
                p.changeData(key, val);
                return;
            }
        }
        return;
    }

    public void addPresentation(Presentation p){
        this.presentations.add(p);
    }
}
