public class Driver {

    public static void checkException() throws MyException{
        throw new MyException();
    }

    public static void main(String[] args) {
        Dog dog = new Dog("Peter");
        dog.makeNoise();
        dog.walk();

        //Exception comming up!
        try{
            checkException();
        }catch(MyException e){
            System.out.println(e.getMessage());
        }
    }
}