public class SafeRange{

    public Patient patient;
    public Device device;
    public MyPair<Double, Double> pulseSensorRange, bloodPressureSensorRange, temperatureSensorRange;

    public SafeRange(Patient patient, Device device, MyPair<Double, Double> range){
        this.patient = patient;
        this.device = device;
        this.pulseSensorRange = null;
        this.bloodPressureSensorRange = null;
        this.temperatureSensorRange = null;
        if(device.category.equals("PulseSensor"))
            pulseSensorRange = range;
        else if(device.category.equals("BloodPressureSensor"))
            bloodPressureSensorRange = range;
        else if(device.category.equals("TemperatureSensor"))
            temperatureSensorRange = range;
    }

    public boolean checkInRange(double value){
        if(device.category.equals("PulseSensor"))
            return (value >= pulseSensorRange.first && value <= pulseSensorRange.second);
        else if(device.category.equals("BloodPressureSensor"))
            return (value >= bloodPressureSensorRange.first && value <= bloodPressureSensorRange.second);
        else if(device.category.equals("TemperatureSensor"))
            return (value >= temperatureSensorRange.first && value <= temperatureSensorRange.second);
        return false;
    }

}