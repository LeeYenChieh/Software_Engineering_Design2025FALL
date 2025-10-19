import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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
        try{
            File inputFile;
            if(args.length > 0)
                inputFile = new File(args[0]);
            else
                inputFile = new File("");
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            Data data = new Data();
            Client client = new Client();

            String line = readNextLine(reader);
            while (line != null) {
                String[] parts = line.trim().split("\\s+");

                if(parts[0].equals("data")){
                    data.addData(parts[1], Integer.parseInt(parts[2]));
                }

                else if(parts[0].equals("addChart")){
                    Presentation p = null;
                    if(parts[1].equals("Spreadsheet"))
                        p = new SpreadSheet(data);
                    else if(parts[1].equals("PieChart"))
                        p = new PieChart(data);
                    else if(parts[1].equals("BarChart"))
                        p = new BarChart(data);
                    
                    data.addPresentation(p);
                    client.addPresentation(p);
                }

                else if(parts[0].equals("change")){
                    client.changeData(parts[1], parts[2], Integer.parseInt(parts[3]));
                }

                line = readNextLine(reader);
            }
        } catch(Exception e){

        }
    }
}
