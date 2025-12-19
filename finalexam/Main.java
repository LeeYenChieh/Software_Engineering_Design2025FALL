import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;


public class Main {
    public static Chassis getChassis(String name){
        return chassis.get(name);
    }

    public static Component getComponent(String name){
        return components.get(name);
    }

    public static void displayInputError(){
        System.out.println("input error");
    }

    public static String readNextLine(BufferedReader reader){
        String nextLine = null;
        while(true){
            try{
                nextLine = reader.readLine();
                if(nextLine == null)
                    break;
                String[] parts = parseStrings(nextLine);
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

    public static Component createComponent(String type, String name, String powerConsumption, String cost){
        try{
            double p = Double.parseDouble(powerConsumption);
            double c = Double.parseDouble(cost);
            if(p < 0 || c < 0)
                throw new Exception();
            
            if(type.equals("chassis")){
                Chassis co = new Chassis(name, p, c);
                chassis.put(name, co);
                return co;
            } else if(type.equals("bus")){
                Component co = new Buses(name, p, c);
                components.put(name, co);
                return co;
            } else if(type.equals("floppy")){
                Component co = new Floppies(name, p, c);
                components.put(name, co);
                return co;
            } else{
                throw new Exception();
            }
        } catch(Exception e){
            // displayInputError();
            return null;
        }
    }

    public static String[] parseStrings(String line){
        return line.trim().split("\\s+");
    }

    public static void printComponent(Component c){

    }

    public static void create(String[] parts){
        try{
            Component co = createComponent("chassis", parts[1], parts[2], parts[3]);
            if(co == null)
                throw new Exception();
        } catch(Exception e){
            displayInputError();
        }
    }

    public static void add(String[] parts){
        try{
            Chassis ch = getChassis(parts[5]);
            if(ch == null)
                throw new Exception();
            Component co = createComponent(parts[1], parts[2], parts[3], parts[4]);
            if(co == null)
                throw new Exception();
            ch.addComponent(co);
        } catch(Exception e){
            displayInputError();
        }
    }

    public static void get(String[] parts){
        try{
            int index = Integer.parseInt(parts[2]);

            Chassis ch = getChassis(parts[1]);
            if(ch == null){
                System.out.println(parts[1] + " does not support command get");
                return;
            } 

            if(index >= ch.components.size() || index < 0){
                System.out.printf("Index %d out of bound of %s\n", index, parts[1]);
                return;
            }
            
            Component co = ch.components.get(index);
            System.out.printf("%s:%s\n", ch.name, co.name);

        } catch(Exception e){
            displayInputError();
        }
    }

    public static void print(String[] parts){
        try{
            Chassis ch = getChassis(parts[1]);
            if(ch != null){
                System.out.printf("%s (%.1f, %.1f)\n", ch.name, ch.powerConsumption, ch.cost);
                for(Component subco : ch.components){
                    System.out.printf("%s:%s (%.1f, %.1f)\n", ch.name, subco.name, subco.powerConsumption, subco.cost);
                }
                return;
            }
            Component co = getComponent(parts[1]);
            if(co != null){
                System.out.printf("%s (%.1f, %.1f)\n", co.name, co.powerConsumption, co.cost);
                return;
            }

            throw new Exception();

        } catch(Exception e){
            displayInputError();
        }
    }

    public static void sumOfPowerConsumption(String[] parts){
        try{
            Chassis ch = getChassis(parts[1]);
            if(ch == null){
                System.out.println(parts[1] + " does not support command sumOfPowerConsumption");
                return;
            }
            double result = ch.sumPowerConsumption();
            System.out.printf("%.1f\n", result);
        } catch(Exception e){
            displayInputError();
        }
    }

    public static void sumOfAdditionCost(String[] parts){
        try{
            Chassis ch = getChassis(parts[1]);
            if(ch == null){
                System.out.println(parts[1] + " does not support command sumOfAdditionCost");
                return;
            }
            double result = ch.sumCost();
            System.out.printf("%.1f\n", result);
        } catch(Exception e){
            displayInputError();
        }
    }

    static HashMap<String, Chassis> chassis = new HashMap<String, Chassis>();
    static HashMap<String, Component> components = new HashMap<String, Component>();

    public static void main(String[] args) {
        try{
            File inputFile;
            if(args.length > 0)
                inputFile = new File(args[0]);
            else
                inputFile = new File("");
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            String line = readNextLine(reader);
            while (line != null) {
                try{
                    String[] parts = parseStrings(line);

                    if(parts[0].equals("create")){
                        create(parts);
                    } else if(parts[0].equals("add")){
                        add(parts);
                    } else if(parts[0].equals("get")){
                        get(parts);
                    } else if(parts[0].equals("print")){
                        print(parts);
                    } else if(parts[0].equals("sumOfPowerConsumption")){
                        sumOfPowerConsumption(parts);
                    } else if(parts[0].equals("sumOfAdditionCost")){
                        sumOfAdditionCost(parts);
                    } else{
                        throw new Exception();
                    }

                } catch(Exception e){
                    displayInputError();
                }
                
                line = readNextLine(reader);
            }

        } catch(Exception e){
            displayInputError();
        }
    }
}
