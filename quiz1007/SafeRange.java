package quiz1007;

import java.util.*;

public class SafeRange{

    public Patient patient;
    public ArrayList<Device> devices;
    public MyPair<Double, Double> pulseSensorRange, bloodPressureSensorRange, temperatureSensorRange;

    public SafeRange(Patient patient){
        this.patient = patient;
        this.devices = new ArrayList<Device>();
        this.pulseSensorRange = null;
        this.bloodPressureSensorRange = null;
        this.temperatureSensorRange = null;
    }

    public void addDevice(Device device, MyPair<Double, Double> range){
        this.devices.add(device);
        if(device.type.equals("PulseSensor"))
            pulseSensorRange = range;
        else if(device.type.equals("BloodPressureSensor"))
            bloodPressureSensorRange = range;
        else if(device.type.equals("TemperatureSensor"))
            temperatureSensorRange = range;
    }

    public MyPair<Double, Double> getRange(Device device){
        if(device.type.equals("PulseSensor"))
            return pulseSensorRange;
        else if(device.type.equals("BloodPressureSensor"))
            return bloodPressureSensorRange;
        else if(device.type.equals("TemperatureSensor"))
            return temperatureSensorRange;
        return null;
    }
}