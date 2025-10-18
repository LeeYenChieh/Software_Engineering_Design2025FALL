import java.util.*;

public class Client {
    HashMap<String, Person> people;
    NullPerson nullPerson;
    
    public Client(){
        this.people = new HashMap<String, Person>();
        this.nullPerson = new NullPerson();
    }

    public void createPerson(String line){
        String[] parts = line.trim().split("\\s+");
        try{
            if(parts.length != 5)
                throw new Exception();
            String name = parts[1];
            String job = parts[2];
            float weight = Float.parseFloat(parts[3]);
            float height = Float.parseFloat(parts[4]);
            if(weight < 0 || height < 0)
                throw new Exception();
            
            this.people.put(name, new ConcretePerson(name, job, weight, height));
        } catch(Exception e) {
            if(parts.length == 5)
                this.people.put(parts[1], this.nullPerson);
        }
    }

    public Person getPerson(String name){
        Person person = this.people.get(name);
        if(person == null)
            person = this.nullPerson;
        return person;
    }

    public void printJob(String name){
        Person person = this.getPerson(name);
        System.out.println(person.job);
    }

    public float getWeightSum(String[] names){
        float sum = 0;
        for(String n : names){
            Person person = this.getPerson(n);
            sum += person.weight;
        }
        return sum;
    }

    public void printWeightSum(String[] names){
        float sum = this.getWeightSum(names);
        System.out.println(Math.round(sum));
    }

    public void printWeightAverage(String[] names){
        float sum = this.getWeightSum(names);
        System.out.println(Math.round(sum / names.length));
    }

    public float getHeightSum(String[] names){
        float sum = 0;
        for(String n : names){
            Person person = this.getPerson(n);
            sum += person.height;
        }
        return sum;
    }

    public void printHeightSum(String[] names){
        float sum = this.getHeightSum(names);
        System.out.println(Math.round(sum));
    }

    public void printHeightAverage(String[] names){
        float sum = this.getHeightSum(names);
        System.out.println(Math.round(sum / names.length));
    }
}
