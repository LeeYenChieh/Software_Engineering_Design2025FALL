package quiz1007;

import java.util.*;

public class Database{

    HashMap<Patient, HashMap<Device, ArrayList<String>>> records;

    public Database(){
        this.records = new HashMap<Patient, HashMap<Device, ArrayList<String>>>();
    }

    public void storeRecord(Patient patient, Device device, String record){
        HashMap<Device, ArrayList<String>> patientRecords;
        if(this.records.containsKey(patient))
            patientRecords = this.records.get(patient);
        else{
            patientRecords = new HashMap<Device, ArrayList<String>>();
            this.records.put(patient, patientRecords);
        }
        
        ArrayList<String> oldRecords;
        if(patientRecords.containsKey(device))
            oldRecords = patientRecords.get(device);
        else{
            oldRecords = new ArrayList<String>();
            patientRecords.put(device, oldRecords);
        }

        oldRecords.add(record);
    }

    public void display(ArrayList<Patient> patients){
        for(Patient patient : patients){
            if(patient == patients.get(patients.size() - 1) && patient.devices.size() == 0)
                System.out.print("patient " + patient.name);
            else
                System.out.println("patient " + patient.name);
            HashMap<Device, ArrayList<String>> patientRecords = this.records.get(patient);
            for(Device device : patient.devices){
                System.out.println(device.type + " " + device.name);
                ArrayList<String> patientDeviceRecords = patientRecords.get(device);
                for(String record : patientDeviceRecords){
                    if(patient == patients.get(patients.size() - 1) && device == patient.devices.get(patient.devices.size() - 1) && record == patientDeviceRecords.get(patientDeviceRecords.size() - 1))
                        System.out.print(record);
                    else
                        System.out.println(record);
                }
            }
        }
    }

}