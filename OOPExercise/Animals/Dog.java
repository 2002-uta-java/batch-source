package Animals;

public class Dog extends Animal implements Mammal, Walkable{
    
    private String noise = "Bark!";
    private String name;

    public Dog(){
        super();
    }

    public Dog(String name){
        this.name=name;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void makeNoise(){
        System.out.println(noise);
    }

    public void giveBirth(){
        System.out.println("Congrats " + name + "!\n");
    }

    public void walk(){
        for(int i = 0; i < 3; i++){
            System.out.println("Walking...  ");
        }
        System.out.println("Stopped\n");
    }

    public void feedYoungling(){
        System.out.println("Youngling Fed");
    }

}