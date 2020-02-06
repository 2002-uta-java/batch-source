package com.Revature.Animals;

public abstract class Driver extends FurType{

	public static void main(String[] args) {
		
		Dog d = new Dog(2.5, true); // input arguments for dog 	
		System.out.println(d); 	  // represented as feet and T/F boolean 
		
		Dog look = new Dog();
		look.calculateHeight();
		System.out.println(look.calculateHeight());
		
		Human h = new Human(6.9, false);  // input arguments for Human 
		System.out.println(h.toString());// represented as feet and T/F boolean 
	
		Human look2 = new Human(6.9, false);
		look2.calculateHeight();
		System.out.println(look2.calculateHeight());
		}
}		

