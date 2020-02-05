package com.revature.exercise;

// OOP Exercise (2/5/2020)
// Coded by Nicholas Desmornes at Revature.

// INHERITANCE
// The hierarchy of this project is as follows:
// Behavior (interface)
// Mammals (abstract class)
// Dog, Cat, Horse (3 concrete classes)

// This demonstrates inheritance. (dog/cat/horse inherits mammals which inherits behavior).

// ENCAPSULATION
// All concrete classes contain private variables with getter and setter methods.
// Therefore, each class has all its data wrapped or encapsulated into one unit.

// POLYMORPHISM
// All concrete classes have their own version of the abstract method makeNoise(). Each overrode
// or had their own form of that method, which demonstrates polymorphism.

// ABSTRACTION
// Abstraction is achieved through the use of the interface Behavior. This interface defines
// a couple of behaviors that all Mammals share. These behaviors aren't explicitly defined in
// the interface, which makes them abstract in concept.

// Custom Exception
// There is an exception in the Mammals class. Within setAge(), a custom exception is thrown if
// you set the age to a negative number.

// Equals and Hashcode
// All concrete classes have equals and hashcode method overrides. Equals returns true if the type
// of mammals being compared are the same. (Ex. any cat equals any cat, dog doesn't equal horse).
// For the hashcodes, dog hashes to 1, cat to 2, horse to 3.

public class Driver {

	public static void main(String[] args) {
		
		// Create mammals.
		Dog dog1 = new Dog("black", 1, "Spot");
		Dog dog2 = new Dog("brown", 2, "Max");
		Cat cat1 = new Cat("white", 5, "Snow", 16);
		Horse horse1 = new Horse("golden", 10, 30);
		System.out.println(dog1.toString());
		System.out.println(dog2.toString());
		System.out.println(cat1.toString());
		System.out.println(horse1.toString());
		System.out.println("");
		
		// Demonstrate setter/getter methods.
		dog1.setName("Jake");
		System.out.println(dog1.getName());
		cat1.setSleepTime(20);
		System.out.println(cat1.getSleepTime());
		System.out.println("");
		
		// Demonstrate abstract methods.
		dog1.makeNoise();
		cat1.makeNoise();
		horse1.makeNoise();
		dog2.eatFood();
		horse1.eatFood();
		System.out.println("");
		
		// Demonstrate equals and hashcode.
		System.out.println(dog1.equals(dog1));
		System.out.println(dog1.equals(dog2));
		System.out.println(dog1.equals(horse1));		
		System.out.println("");
		
		// Demonstrate custom exception.
		dog1.setAge(8); // works
		System.out.println(dog1.getAge());
		dog1.setAge(-1); // error!
		

	}

}

