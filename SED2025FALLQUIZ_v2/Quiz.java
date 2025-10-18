import java.io.*;

public class Quiz{

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
                    displayInputError();
                    continue;
                }
                totalTime = Integer.parseInt(totalTimeLineParts[0]);
                break;
            } catch(Exception e){
                displayInputError();
                continue;
            }
        }

        return totalTime;
    }

    public static Patient parsePatient(String[] parts, Monitor monitor){
        if(parts.length != 3){
            displayInputError();
            return null;
        }
        try{
            Patient thePatient = monitor.findPatient(parts[1]);
            if(thePatient == null)
                thePatient = new Patient(parts[1], Integer.parseInt(parts[2]));
            return thePatient;
        } catch (Exception e){
            displayInputError();
            return null;
        }
    }

    public static MyPair<Device, MyPair<Double, Double>> parseDeviceAndSafeRange(String[] parts){
        if(parts.length != 5){
            displayInputError();
            return null;
        }

        try{
            File dataFile = new File(parts[2]);
            BufferedReader dataReader = new BufferedReader(new FileReader(dataFile));

            Device newDevice = new Device(parts[1], parts[0], dataReader);
            MyPair<Double, Double> rangePair = new MyPair<Double, Double>(Double.parseDouble(parts[3]), Double.parseDouble(parts[4]));
            return new MyPair<Device, MyPair<Double, Double>>(newDevice, rangePair);
        } catch(Exception e){
            displayInputError();
            return null;
        }
    }

    public static void main(String[] args){
        int totalTime = -1;
        Monitor monitor = new Monitor();

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
                if((parts[0].equalsIgnoreCase("PulseSensor") || parts[0].equalsIgnoreCase("BloodPressureSensor") || parts[0].equalsIgnoreCase("TemperatureSensor")) && curPatient != null){

                    MyPair<Device, MyPair<Double, Double>> temp = parseDeviceAndSafeRange(parts);
                    if(temp != null){
                        curPatient.addDevice(temp.first);
                        monitor.addSafeRange(curPatient, temp.first, temp.second);
                    }

                } else if(parts[0].equalsIgnoreCase("patient")){

                    Patient temp = parsePatient(parts, monitor);
                    if(temp != null){
                        curPatient = temp;
                        if(monitor.findPatient(temp.name) == null)
                            monitor.addPatient(temp);
                    }

                } else{
                    displayInputError();
                }

                commandLine = readNextLine(reader);
            }

            monitor.startMonitor(totalTime);
            monitor.displayByOrder();

        } catch (Exception e){
            displayInputError();
        }
    }

}