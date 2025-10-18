import java.io.*;

public class Device{

    public String name;
    public String category;
    public BufferedReader dataReader;

    public Device(String name, String category, String path){
        this.name = name;
        this.category = category;

        try{
            File dataFile = new File(path);
            this.dataReader = new BufferedReader(new FileReader(dataFile));
        } catch (Exception e){
            this.dataReader = null;
        }
    }

    public double readData(){
        if(this.dataReader == null)
            return -1;
        try {
            double value = Double.parseDouble(this.dataReader.readLine().trim().split("\\s+")[0]);
            return value;
        } catch (Exception e){
            return -1;
        }
    }

}