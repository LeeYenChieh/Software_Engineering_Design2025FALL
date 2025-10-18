import java.io.*; 
import java.util.*;

public class Quiz{

    private static Patient parsePatient(String[] parts){
        if(parts.length != 3){
            System.out.println("Input Error");
            return null;
        }

        try {
            double frequency = Double.parseDouble(parts[2]);
            Patient newPatient = new Patient(parts[1], frequency);
            return newPatient;
        } catch(Exception e) {
            System.out.println("Input Error");
            return null;
        }
    }

    private static Pair<Device, SafeRange> parseDeviceAndSafeRange(String[] parts, Patient patient){
        if(parts.length != 5 && patient != null){
            System.out.println("Input Error");
            return null;
        }

        try {
            double minRange = Double.parseDouble(parts[3]);
            double maxRange = Double.parseDouble(parts[4]);
            Device newDevice = new Device(parts[1], parts[0], parts[2]);
            SafeRange newSafeRange = new SafeRange(patient, newDevice, new Pair<Double, Double>(minRange, maxRange));

            return new Pair<Device, SafeRange>(newDevice, newSafeRange);
        } catch(Exception e) {
            System.out.println("Input Error");
            return null;
        }
    }

    public static void main(String[] args){
        String inputFileName = args[0];
        int totalTime = -1;
        Monitor monitor = new Monitor();
        
        File inputFile = new File(inputFileName);

        try(BufferedReader reader = new BufferedReader(new FileReader(inputFile))){
            
            String line;

            String[] timeLine = reader.readLine().trim().split("\\s+");
            totalTime = Integer.parseInt(timeLine[0]);

            Patient curPatient = null;

            while((line = reader.readLine()) != null){
                String[] parts = line.trim().split("\\s+");

                if(parts[0].equals("PulseSensor") || parts[0].equals("BloodPressureSensor") || parts[0].equals("TemperatureSensor")){ //equalsIgnoreCase

                    Pair<Device, SafeRange> temp = parseDeviceAndSafeRange(parts, curPatient);
                    if (temp != null && curPatient != null){
                        curPatient.addDevice(temp.first);
                        monitor.addSafeRange(temp.second);
                    }

                } else if(parts[0].equals("patient")){

                    Patient temp = parsePatient(parts);
                    if(temp != null){
                        monitor.addPatient(temp);
                        curPatient = temp;
                    }

                } else{
                    System.out.println("Input Error");
                }
            }

        } catch (IOException e){
            System.out.println("Input Error");
        }

        monitor.startMonitor(totalTime);
        monitor.database.display(monitor.patients);
    }
}