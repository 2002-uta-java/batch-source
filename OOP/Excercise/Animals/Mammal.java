public interface Mammal{
    
    abstract void giveBirth();
    
    default void feedYoungling(){
        System.out.println("Youngling Fed");
    }
}