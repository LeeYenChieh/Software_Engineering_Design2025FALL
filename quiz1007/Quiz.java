package quiz1007;

import java.io.*;

public class Quiz {

    public static void displayInputError(){
        System.out.println("Input Error");
    }

    public static String readNextLine(BufferedReader reader){
        String nextLine = null;
        while(true){
            try{
                nextLine = reader.readLine();
                if(nextLine == null)
                    break;
                String[] parts = nextLine.trim().split("\\s+");
                if(parts.length == 0 || parts[0].equals(""))
                    continue;
                break;
            } catch (Exception e){
                displayInputError();
                continue;
            }
        }
        return nextLine;
    }

    public static int readTotalTime(BufferedReader reader){
        String[] totalTimeLineParts;
        int totalTime = -1;

        while(true){
            try{
                String totalTimeLine = readNextLine(reader);
                if(totalTimeLine == null)
                    break;
                totalTimeLineParts = totalTimeLine.trim().split("\\s+");
                if(totalTimeLineParts.length != 1){
                    throw new Exception();
                }
                totalTime = Integer.parseInt(totalTimeLineParts[0]);
                if(totalTime < 0)
                    throw new Exception();
                break;
            } catch(Exception e){
                displayInputError();
                continue;
            }
        }

        return totalTime;
    }

    public static Patient parsePatient(String[] parts){
        if(parts.length != 3){
            displayInputError();
            return null;
        }
        try{
            Patient newPatient = new Patient(parts[1], Integer.parseInt(parts[2]));
            if(newPatient.frequency <= 0)
                throw new Exception();
            return newPatient;
        } catch (Exception e){
            displayInputError();
            return null;
        }
    }

    public static MyPair<Device, MyPair<Double, Double>> parseDeviceAndSafeRange(Patient curPatient, String[] parts){
        if(parts.length != 5){
            displayInputError();
            return null;
        }

        try{
            File dataFile = new File(parts[2]);
            BufferedReader dataReader = new BufferedReader(new FileReader(dataFile));

            Device newDevice = new Device(parts[1], parts[0], dataReader);
            MyPair<Double, Double> rangePair = new MyPair<Double, Double>(Double.parseDouble(parts[3]), Double.parseDouble(parts[4]));
            if(rangePair.first > rangePair.second || curPatient.safeRange.getRange(newDevice) != null)
                throw new Exception();
            return new MyPair<Device, MyPair<Double, Double>>(newDevice, rangePair);
        } catch(Exception e){
            // System.out.println(e);
            displayInputError();
            return null;
        }
    }
    public static void main(String[] args){
        int totalTime = -1;
        Database database = new Database();
        Monitor monitor = new Monitor(database);

        try{
            File inputFile;
            if(args.length > 0)
                inputFile = new File(args[0]);
            else
                inputFile = new File("");
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            totalTime = readTotalTime(reader);

            Patient curPatient = null;
            
            String commandLine = readNextLine(reader);
            while(commandLine != null){
                String[] parts = commandLine.trim().split("\\s+");
                if((parts[0].equals("PulseSensor") || parts[0].equals("BloodPressureSensor") || parts[0].equals("TemperatureSensor")) && curPatient != null){

                    MyPair<Device, MyPair<Double, Double>> temp = parseDeviceAndSafeRange(curPatient, parts);
                    if(temp != null)
                        curPatient.addDevice(temp.first, temp.second);

                } else if(parts[0].equals("patient")){

                    Patient temp = parsePatient(parts);
                    if(temp != null){
                        monitor.addPatient(temp);
                        curPatient = temp;
                    }

                } else{
                    displayInputError();
                }

                commandLine = readNextLine(reader);
            }

            monitor.readFactors(totalTime);
            monitor.displayByOrder();

        } catch (Exception e){
            displayInputError();
        }
    }
}
