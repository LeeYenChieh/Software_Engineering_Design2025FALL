public class SafeRange{

    public Patient patient;
    public Device device;
    public Pair<Double, Double> PulseSafeRange;
    public Pair<Double, Double> BloodPressureSafeRange;
    public Pair<Double, Double> TemperatureSageRange;

    public SafeRange(Patient patient, Device device, Pair<Double, Double> range){
        this.patient = patient;
        this.device = device;
        if(device.category.equals("PulseSensor"))
            this.PulseSafeRange = range;
        else if(device.category.equals("BloodPressureSensor"))
            this.BloodPressureSafeRange = range;
        else if(device.category.equals("TemperatureSensor"))
            this.TemperatureSageRange = range;
    }

    public boolean checkRange(double value){
        if(this.device.category.equals("PulseSensor"))
            return (value >= this.PulseSafeRange.first && value <= this.PulseSafeRange.second);
        else if(this.device.category.equals("BloodPressureSensor")){
            return (value >= this.BloodPressureSafeRange.first && value <= this.BloodPressureSafeRange.second);
        }
        else if(this.device.category.equals("TemperatureSensor"))
            return (value >= this.TemperatureSageRange.first && value <= this.TemperatureSageRange.second);
        return false;
    }
}