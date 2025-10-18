import java.util.*;

public class Patient{

    public String name;
    public double frequency;
    public ArrayList<Device> devices;
    public SafeRange safeRange;

    public Patient(String name, double frequency){
        this.name = name;
        this.frequency = frequency;
        this.devices = new ArrayList<Device>();
        this.safeRange = new SafeRange(this);

    }

    public void addDevice(Device newDevice, MyPair<Double, Double> range){
        this.devices.add(newDevice);
        this.safeRange.addDevice(newDevice, range);
    }

}