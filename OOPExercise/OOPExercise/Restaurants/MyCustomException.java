package OOPExercise.OOPExercise.Restaurants;

public class MyCustomException extends Exception{


    private static final long serialVersionUID = 1L;

    public MyCustomException(String errorMessage) {
        super(errorMessage);
    }
    
}