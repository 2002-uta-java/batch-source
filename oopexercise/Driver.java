package com.revature.oopexercise;

import java.util.ArrayList;
import java.util.List;

public class Driver {
	
	static void showAnimals() {
		List<Animal> animals = new ArrayList<>();
		
		Coyote c1 = new Coyote();
		c1.setSound("Ah-wooooo!");
		animals.add(c1);

		Cat c2 = new Cat();
		c2.setSound("Meow!");
		animals.add(c2);
		
		Crow c3 = new Crow();
		c3.setSound("Caw!");
		animals.add(c3);
		
		for (Animal a: animals) {
			a.makeSound();
			a.eat();
			System.out.println();
		}
		c1.eat("crow");
		c3.die();
	}
	public static void main(String[] args) {
		showAnimals();
	}

}
