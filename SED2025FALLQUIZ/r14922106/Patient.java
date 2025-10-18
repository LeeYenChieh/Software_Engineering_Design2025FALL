import java.util.*;

public class Patient{

    public String name;
    public double frequency;
    public ArrayList<Device> devices;

    public Patient(String name, double frequency){
        this.name = name;
        this.frequency = frequency;
        this.devices = new ArrayList<Device>();
    }

    public void addDevice(Device newDevice){
        this.devices.add(newDevice);
    }

}