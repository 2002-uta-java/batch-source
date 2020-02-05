package OOPExercise.OOPExercise.Restaurants;

public class User{

    String name;

    public User(String name){
        this.name = name;
    }

    public static void handleException() throws MyCustomException{
        throw new MyCustomException("My exception");
    }

    public static void main(String[] args){
        Waiter carlos = new Waiter();

        System.out.println(carlos.restaurantName);
        System.out.println(carlos.specialty);
        System.out.println(carlos.lunchValue);
        System.out.println(carlos.dinnerValue);

        try{
            handleException();
        } catch(MyCustomException e) {
            System.out.println(e.getMessage());
        }


    }

}