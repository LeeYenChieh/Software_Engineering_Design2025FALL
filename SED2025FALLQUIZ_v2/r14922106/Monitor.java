import java.util.*;

public class Monitor{

    public ArrayList<Patient> patients;
    public ArrayList<SafeRange> safeRanges;
    public Database database;

    public Monitor(){
        this.patients = new ArrayList<Patient>();
        this.safeRanges = new ArrayList<SafeRange>();
        this.database = new Database();
    }

    private void displayDeviceFail(int time, String deviceName){
        System.out.printf("[%d] %s fails\n", time, deviceName);
    }

    private void displayPatientDanger(int time, String patientName,String deviceName, double value){
        System.out.printf("[%d] %s is in danger! Cause: %s %.1f\n", time, patientName, deviceName, value);
    }

    private void readPatientData(int time, Patient patient){
        for(Device device : patient.devices){
            double value = device.readData();
            if(value == -1)
                displayDeviceFail(time, device.name);
            
            SafeRange theSafeRange = getSafeRange(patient, device);
            if(value != -1 && !theSafeRange.checkInRange(value))
                displayPatientDanger(time, patient.name, device.name, value);
            
            String record = String.format("[%d] %.1f", time, value);
            this.database.storeData(patient, device, record);
        }
    }

    public void startMonitor(int totalTime){
        if(totalTime < 0)
            return;

        for(int i = 0;i <= totalTime;i++){
            for(Patient patient : patients){
                if(i % patient.frequency == 0)
                    readPatientData(i, patient);
            }
        }
    }

    public void displayByOrder(){
        database.display(patients);
    }

    public SafeRange getSafeRange(Patient patient, Device device){
        for(SafeRange safeRange : safeRanges){
            if(safeRange.patient == patient && safeRange.device == device){
                return safeRange;
            }
        }
        return null;
    }

    public void addPatient(Patient newPatient){
        this.patients.add(newPatient);
    }

    public void addSafeRange(Patient patient, Device device, MyPair<Double, Double> range){
        SafeRange newSafeRange = new SafeRange(patient, device, range);
        safeRanges.add(newSafeRange);
    }

    public Patient findPatient(String name){
        Patient thePatient = null;
        for(Patient oldPatient : this.patients){
            if(oldPatient.name.equals(name)){
                thePatient = oldPatient;
                return thePatient;
            }
        }

        return thePatient;
    }

}