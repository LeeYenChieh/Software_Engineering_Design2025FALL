import java.io.*;

public class Device{

    public String name;
    public String category;
    public BufferedReader dataReader;

    public Device(String name, String category, BufferedReader dataReader){
        this.name = name;
        this.category = category;
        this.dataReader = dataReader;
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