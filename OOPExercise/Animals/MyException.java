package Animals;

public class MyException extends Exception{
    
    private static final long serialVersionUID = 1L;

    public MyException(){
        super();
    }

    @Override
    public String getMessage(){
        return "This is a custom exception!";
    }
}