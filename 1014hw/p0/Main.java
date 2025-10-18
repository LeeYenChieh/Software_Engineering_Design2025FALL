import java.io.*;
import java.util.Arrays;

public class Main {

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
                continue;
            }
        }
        return nextLine;
    }

    public static void main(String[] args){
        Client client = new Client();
        try{
            File inputFile;
            if(args.length > 0)
                inputFile = new File(args[0]);
            else
                inputFile = new File("");
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            String line = readNextLine(reader);
            while (line != null) {
                String[] parts = line.trim().split("\\s+");

                if(parts[0].equals("Person")){
                    client.createPerson(line);
                }

                else if(parts[0].equals("Job")){
                    client.printJob(parts[1]);
                }

                else if(parts[0].equals("WeightAverage")){
                    client.printWeightAverage(Arrays.copyOfRange(parts, 1, parts.length));
                }

                else if(parts[0].equals("WeightSum")){
                    client.printWeightSum(Arrays.copyOfRange(parts, 1, parts.length));
                }

                else if(parts[0].equals("HeightAverage")){
                    client.printHeightAverage(Arrays.copyOfRange(parts, 1, parts.length));
                }

                else if(parts[0].equals("HeightSum")){
                    client.printHeightSum(Arrays.copyOfRange(parts, 1, parts.length));
                }

                line = readNextLine(reader);
            }
        } catch(Exception e){

        }
    }
}
