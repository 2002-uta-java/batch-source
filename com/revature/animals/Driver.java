package com.revature.animals;

public class Driver {

	public static void main(String[] args) {

		
		Dog d = new Dog("Max", "Bark");
		Dog d2 = new Dog("Max", "Bark");
		System.out.println(d.getName());
		d.makeNoise();
		d.fetch();
		d.fetch("stick");
		try {
			int age = 0;
			d.setAge(age);
		} catch(Exception e) {
			System.out.println(e);
		}
		
		System.out.println("Same? " + d.equals(d2));
		System.out.println("Same hashcode? " + (d.hashCode() == d2.hashCode()));
		System.out.println("I reproduce ... " + d.reproduce());
		System.out.println("------------");
		Cat c = new Cat("Chase", "Meow");
		System.out.println(c.getName());
		c.makeNoise();
		System.out.println("I reproduce ... " + c.reproduce());
	}
	
}
