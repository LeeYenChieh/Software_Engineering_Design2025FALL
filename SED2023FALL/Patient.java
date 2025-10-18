import java.util.*;

public class Patient{

    public String name;
    public double frequency;
    public ArrayList<Device> deviecs = new ArrayList<Device>();

    public Patient(String name, double frequency){
        this.name = name;
        this.frequency = frequency;
    }

    public void addDevice(Device newDevice){
        this.deviecs.add(newDevice);
    }
}