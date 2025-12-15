package hw1014.p2;

public class Presentation {
    public Data data;
    public String type;

    public Presentation(Data data){
        this.data = data;
    }

    public void reflect(){}

    public void changeData(String key, int val){
        data.changeData(key, val);
    }
}
