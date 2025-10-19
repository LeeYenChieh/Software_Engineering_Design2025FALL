public class SpreadSheet extends Presentation {
    
    public SpreadSheet(Data data){
        super(data);
        this.type = "Spreadsheet";
    }

    public void reflect(){
        for(var e : this.data.data.entrySet()){
            System.out.printf("%s %d\n", e.getKey(), e.getValue());
        }
    }

}
