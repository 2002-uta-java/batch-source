package OOPExercise.OOPExercise.Restaurants;

public abstract class Restaurant{

    //Abstraction
    
    //Final instance variables
    final String restaurantName = "EL POLLO LOCO";
    final String specialty = "Chicken";
    final int lunchValue = 5;
    final int dinnerValue = 7;

    //Abstract methods
    public abstract String welcomeCustomer();
    public abstract String farewellCustomer();

}