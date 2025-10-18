import java.io.*; 

public class Device{

    public String category;
    public String name;
    public BufferedReader reader;

    public Device(String name, String category, String path){
        this.name = name;
        this.category = category;

        try{
            File dataFile = new File(path);
            this.reader = new BufferedReader(new FileReader(dataFile));
        } catch (Exception e){
            this.reader = null;
        }
    }

    public double getData(){
        if(this.reader == null)
            return -1;
        try {
            double value = Double.parseDouble(this.reader.readLine().trim().split("\\s+")[0]);
            return value;
        } catch (Exception e){
            return -1;
        }
    }
}