public class BarChart extends Presentation {
    
    public BarChart(Data data){
        super(data);
        this.type = "BarChart";
    }

    public void reflect(){
        for(var e : this.data.data.entrySet()){
            for(int i = 0;i < e.getValue();i++)
                System.out.printf("=");
            System.out.printf(" %s\n", e.getKey());
        }
    }
}
