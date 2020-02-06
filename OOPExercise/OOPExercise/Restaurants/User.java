package OOPExercise.OOPExercise.Restaurants;

public class User{

    String name;

    //Constructor
    public User(String name){
        this.name = name;
    }

    public static void handleException() throws MyCustomException{
        throw new MyCustomException("My exception");
    }

    //equals() method of Object class
    public static boolean checkName(String name){
        return name.equals(name);
    }

    public static void main(String[] args){
        User vytautas = new User("Vytautas");
        Waiter carlos = new Waiter();
        String userName = vytautas.name;

        System.out.println(carlos.restaurantName);
        System.out.println(carlos.specialty);
        System.out.println(carlos.lunchValue);
        System.out.println(carlos.dinnerValue);

        System.out.println(checkName(userName));

        try{
            handleException();
        } catch(MyCustomException e) {
            System.out.println(e.getMessage());
        }


    }

}