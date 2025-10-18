import java.util.*;

public class Monitor{

    public ArrayList<Patient> patients;
    public ArrayList<SafeRange> saferanges;
    public Database database;

    public Monitor(){
        patients = new ArrayList<Patient>();
        saferanges = new ArrayList<SafeRange>();
        database = new Database();
    }

    public void addPatient(Patient newPatient){
        patients.add(newPatient);
    }

    public void addSafeRange(SafeRange newSafeRange){
        saferanges.add(newSafeRange);
    }

    public void readPatientData(int time, Patient patient){
        for(Device device : patient.deviecs){
            double newData = device.getData();
            if(newData == -1){
                System.out.printf("[%d] %s fails\n", time, device.name);
            }

            SafeRange thisSafeRange = findSafeRange(patient, device);
            if(thisSafeRange != null && newData != -1 && !thisSafeRange.checkRange(newData)){
                System.out.printf("[%d] %s is in danger! Cause: %s %.1f\n", time, patient.name, device.name, newData);
            }
            
            String record = String.format("[%d] %.1f", time, newData);
            database.saveToDatabase(patient, device, record);
        }
    }

    private SafeRange findSafeRange(Patient patient, Device device){
        for(SafeRange saferange : this.saferanges){
            if(patient == saferange.patient && device == saferange.device)
                return saferange;
        }
        return null;
    }

    public void startMonitor(int totalTime){
        for(int i = 0;i <= totalTime;i++){
            for(Patient patient : patients){
                if(i % patient.frequency == 0)
                    readPatientData(i, patient);
            }
        }
    }
}