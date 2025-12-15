package hw1014.p0;

public abstract class Person {
    String name;
    String job;
    float weight;
    float height;

    public Person(String name, String job, float weight, float height){
        this.name = name;
        this.job = job;
        this.weight = weight;
        this.height = height;
    }
}
