package OOPExercise.OOPExercise.Restaurants;

public class User{

    String name;
    //creating id to replace default hashcode of Object
    int id;

    //Constructor
    public User(String name, int id){
        super();
        this.name = name;
        this.id = id;
    }

    public static void handleException() throws MyCustomException{
        throw new MyCustomException("My exception");
    }

    //equals() method from Object 
    @Override
    public boolean equals(Object obj){
        
        if(this.getClass() == obj.getClass()){
            return true;
        } else {
            return false;
        }
        
    }

    //hashCode() method from Object
    @Override
    public int hashCode(){
        return this.id;
    }

    public static void main(String[] args){
        User vytautas = new User("Vytautas", 0);
        User klimavicius = new User("Klimavicius", 0);
        Waiter carlos = new Waiter();
        
        //Compare Objects to use equal() and hashCode()
        if(vytautas.hashCode() == klimavicius.hashCode()){
            System.out.println("Both objects are equal");
        }

        if(vytautas.equals(klimavicius)){
            System.out.println("Both objects are equal");
        }

        System.out.println(carlos.restaurantName);
        System.out.println(carlos.specialty);
        System.out.println(carlos.lunchValue);
        System.out.println(carlos.dinnerValue);
        // System.out.println(vytautas.equals(klimavicius));

        // System.out.println(vytautas.getClass());
        // System.out.println(klimavicius instanceof User);
        // System.out.println(vytautas.hashCode());
        // System.out.println(klimavicius.hashCode());

        try{
            handleException();
        } catch(MyCustomException e) {
            System.out.println(e.getMessage());
        }


    }

}