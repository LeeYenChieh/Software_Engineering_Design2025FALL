import java.util.*;

public class Database{

    public HashMap<String, ArrayList<String>> records;

    public Database(){
        this.records = new HashMap<String, ArrayList<String>>();
    }

    public void saveToDatabase(Patient patient, Device device, String newRecord){
        String key = patient.name + " " + device.name;
        if(this.records.containsKey(key)){
            ArrayList<String> theRecord = this.records.get(key);
            theRecord.add(newRecord);
        } else{
            ArrayList<String> theRecord = new ArrayList<String>();
            theRecord.add(newRecord);
            this.records.put(key, theRecord);
        }
    }

    public void display(ArrayList<Patient> patients){
        for(Patient patient : patients){
            System.out.println("patient " + patient.name);
            for(Device device : patient.deviecs){
                System.out.println(device.category + " " + device.name);
                ArrayList<String> theRecord = this.records.get(patient.name + " " + device.name);
                for(String record : theRecord)
                    System.out.println(record);
            }
        }
    }
}