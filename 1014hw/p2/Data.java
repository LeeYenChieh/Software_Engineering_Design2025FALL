import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Data {

    public int total;
    public LinkedHashMap<String, Integer> data;
    public ArrayList<Presentation> presentations;

    public Data(){
        this.total = 0;
        this.data = new LinkedHashMap<String, Integer>();
        this.presentations = new ArrayList<Presentation>();
    }

    public void addData(String key, int val){
        this.data.put(key, val);
        this.total += val;
    }

    public void changeData(String key, int val){
        if(this.data.get(key) == null)
            this.addData(key, val);
        else{
            this.total = this.total - this.data.get(key) + val;
            this.data.put(key, val);
        }

        for(Presentation p : this.presentations){
            p.reflect();
        }
    }

    public void addPresentation(Presentation p){
        this.presentations.add(p);
    }

}
