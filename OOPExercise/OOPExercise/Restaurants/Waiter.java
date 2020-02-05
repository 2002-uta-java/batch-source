package OOPExercise.OOPExercise.Restaurants;

// Inheritance is used here
public class Waiter extends Restaurant implements Menu{

    boolean test = true;

    public Waiter(){
        super();
    }

    //Polymorphism is used here
	@Override
	public String welcomeCustomer() {
		return "Hello customer";
	}

	@Override
	public String showMenu() {
		return "Here is the menu";
	}

	@Override
	public String farewellCustomer() {
		return "Bye Customer";
	}

    @Override
    public String receiveMenu() {
            return "Menu is received";
        }

}
