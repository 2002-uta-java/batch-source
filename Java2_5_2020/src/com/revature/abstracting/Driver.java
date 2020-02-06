package com.revature.abstracting;

public class Driver {

	public static void main(String[] args) {
		Cat cat = new Cat();
		Dog dog = new Dog();
		
		cat.BodyType(); // 'Concrete' cat speed method from the AbstractAnimal Class
		dog.SpeedType();// 'Concrete' dog body method used from the AbstractAnimal Class
		
		cat.setColor("Black"); // setting cat object color. Inheriented from abstractAnimal class 
		dog.setColor("Brown"); // setting dog object color. Inheriented from abstractAnimal class
		
		System.out.println("The dogs color is " + dog.getColor()); 
		System.out.println("The cats color is " + cat.getColor());
		
		cat.Eating("mice", "rats"); //Polymorphism of how eating method
		cat.Eating("Catfood");
		
		try {
			
			dog.Eating("Catfood");
		} catch (Exception e) {
			
			System.out.println("DOGS DONT EAT CATFOOD SILLY!!!");
		}
	}

}
