package com.revature.clothing;

public class ClothingDriver {
	
	public static void main(String[] args) {

		System.out.println("-------------------------------------------------------------------------------------------------------");
		System.out.println("CREATING CLOTHING ITEMS");
		System.out.println("-------------------------------------------------------------------------------------------------------");
		Pants pantOne = new Pants("Dockers","Grey","Cotton");
		System.out.println();
		
		Shorts shortsOne = new Shorts("Banana Republic", "Navy Blue", "Cotton");
		System.out.println();
		
		Shorts shortsTwo = new Shorts("Banana Republic", "Navy Blue", "Cotton");
		System.out.println();
		
		Shorts shortsThree = new Shorts("Gap", "Salmon", "Cotton");
		System.out.println();
		
		Shirt shirtOne = new Shirt("Polo","White","Polyester");
		System.out.println();
		
		System.out.println("-------------------------------------------------------------------------------------------------------");
		System.out.println("GETTING / SETTING EXAMPLES");
		System.out.println("-------------------------------------------------------------------------------------------------------");
		
		System.out.println("getting pantOne category:");
		System.out.println(pantOne.getCategory());
		System.out.println();
		System.out.println("attempting to change the category to invalid category:");
		pantOne.setCategory("kapri");
		System.out.println();
		System.out.println("getting shirtOne brand:");
		System.out.println(shirtOne.getBrand());
		System.out.println();
		System.out.println("Setting shirtOne brand to Levis:");
		shirtOne.setBrand("Levis");
		System.out.println(shirtOne.toString());
		System.out.println();
		
		System.out.println();
		System.out.println("-------------------------------------------------------------------------------------------------------");
		System.out.println("USING INHERITED INTERFACE FUNCTIONALITY");
		System.out.println("-------------------------------------------------------------------------------------------------------");		
		
		System.out.print("first attempt to put on a shirt: ");
		shirtOne.putOn();
		System.out.print("second attempt to put on same shirt: ");
		shirtOne.putOn();
		System.out.print("first attempt to put on pants: ");
		pantOne.putOn();
		System.out.print("first attempt to takeoff pants: ");
		pantOne.takeOff();
		System.out.print("second attempt to takeoff same pants: ");
		pantOne.takeOff();
		
		System.out.println();
		System.out.println("-------------------------------------------------------------------------------------------------------");
		System.out.println("EXAMPLE OF COVARIANCE");
		System.out.println("-------------------------------------------------------------------------------------------------------");
		// this is an example of covariance - a polymorphism concept
		// using a subclass as an instance of the superclass
		Clothing coatOne = new Coat("London Fog","Grey","Wool");
		
		System.out.println();
		System.out.println("-------------------------------------------------------------------------------------------------------");
		System.out.println("PRINTING HASH CODES AND CHECKING EQUALS");
		System.out.println("-------------------------------------------------------------------------------------------------------");
		System.out.println("shortsOne: " + shortsOne.hashCode());
		System.out.println("shorstTwo: " + shortsTwo.hashCode());
		System.out.println("shorstThree: " + shortsThree.hashCode());
		
		System.out.println();
		
		System.out.println("shortsOne equals shortsTwo: " + shortsOne.equals(shortsTwo));
		System.out.println("shortsOne equals shortsThree: " + shortsOne.equals(shortsThree));
	}

}
