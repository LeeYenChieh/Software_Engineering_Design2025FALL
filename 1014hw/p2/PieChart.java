public class PieChart extends Presentation {
    
    public PieChart(Data data){
        super(data);
        this.type = "PieChart";
    }

    public void reflect(){
        for(var e : this.data.data.entrySet()){
            System.out.printf("%s %.1f%%\n", e.getKey(), (float)Math.round((float)e.getValue() * 100 * 10 / this.data.total) / 10);
        }
    }
}
