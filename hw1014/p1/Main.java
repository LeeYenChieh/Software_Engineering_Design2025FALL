package hw1014.p1;

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

    public static Text parseText(String[] parts){
        int id = Integer.parseInt(parts[1]);
        int natureSize = Integer.parseInt(parts[2]);
        int shrinkAbility = Integer.parseInt(parts[3]);
        int stretchAbility = Integer.parseInt(parts[4]);
        String content = parts[5];
        return new Text(id, natureSize, stretchAbility, shrinkAbility, content);
    }

    public static Graphical parseGraphical(String[] parts){
        int id = Integer.parseInt(parts[1]);
        int natureSize = Integer.parseInt(parts[2]);
        int shrinkAbility = Integer.parseInt(parts[3]);
        int stretchAbility = Integer.parseInt(parts[4]);
        String content = parts[5];
        return new Graphical(id, natureSize, stretchAbility, shrinkAbility, content);
    }

    public static void main(String[] args){
        try{
            File inputFile;
            if(args.length > 0)
                inputFile = new File(args[0]);
            else
                inputFile = new File("");
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            Composition composition = new Composition();

            String line = readNextLine(reader);
            while (line != null) {
                String[] parts = line.trim().split("\\s+");

                if(parts[0].equals("Text")){
                    composition.addComponent(parseText(parts));
                }

                else if(parts[0].equals("GraphicalElement")){
                    composition.addComponent(parseText(parts));
                }

                else if(parts[0].equals("ChangeSize")){
                    int id = Integer.parseInt(parts[1]);
                    int newSize = Integer.parseInt(parts[2]);
                    composition.changeSize(id, newSize);
                }

                else if(parts[0].equals("Require")){
                    if(parts[1].equals("SimpleComposition"))
                        composition.setComposeMode(new SimpleCompose());
                    else if(parts[1].equals("TexComposition"))
                        composition.setComposeMode(new TexCompose());
                    else if(parts[1].equals("ArrayComposition"))
                        composition.setComposeMode(new ArrayCompose());
                    composition.arrange();
                }

                line = readNextLine(reader);
            }
        } catch(Exception e){

        }
    }
}
