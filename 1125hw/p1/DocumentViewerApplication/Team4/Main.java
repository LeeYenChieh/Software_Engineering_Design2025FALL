import java.io.*;

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
        AbstractApplication curApp = null;
        AbstractApplication textApp = new TextApplication();
        AbstractApplication spreadSheetApp = new SpreadSheetApplication();
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

                if(parts[0].equals("text") && parts[1].equals("application")){
                    curApp = textApp;
                }

                else if(parts[0].equals("spreadsheet") && parts[1].equals("application")){
                    curApp = spreadSheetApp;
                }

                else if(parts[0].equals("read")){
                    if(curApp != null)
                        curApp.openDocument(parts[1]);
                }

                line = readNextLine(reader);
            }
        } catch(Exception e){

        }
    }
}
