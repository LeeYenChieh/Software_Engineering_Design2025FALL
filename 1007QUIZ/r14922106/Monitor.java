import java.util.*;

public class Monitor{

    public ArrayList<Patient> patients;
    public Database database;

    public Monitor(Database d){
        this.patients = new ArrayList<Patient>();
        this.database = d;
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
            
            MyPair<Double, Double> range = patient.safeRange.getRange(device);
            if(value != -1 && !(range.first <= value && value <= range.second))
                displayPatientDanger(time, patient.name, device.name, value);
            
            String record = String.format("[%d] %.1f", time, value);
            this.database.storeRecord(patient, device, record);
        }
    }

    public void readFactors(int totalTime){
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

    public void addPatient(Patient newPatient){
        this.patients.add(newPatient);
    }
}