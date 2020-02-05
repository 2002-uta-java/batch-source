package com.charnecki.life;

public class Driver {

	public static void main(String[] args) {
		
		// Create a person and test toString
		Person p1 = new Person("Phil", 30);
		System.out.println(p1);
		
		Person p2 = new Person("Phil", 30);
		
		// Compare p1 and new person p2
		System.out.println("p1.equals(p2): " + p1.equals(p2));
		
		// Create new person and set p2 and p3 as children of p1
		Person p3 = new Person("Billy");
		
		p1.setChildren(p2, p3);
		
		// Print out p1 again to see the children
		System.out.println(p1);
		
		// Test methods from interfaces
		p3.doWork();
		p1.speak();
		
		// Makes a tree for some reason
		Tree t1 = new Tree();
		
		// Kill a tree for capitalist greed
		t1.die();
		
		// Covariance
		Living t2 = new Tree();
		
		t2.die();
		
		// Try to make a person with a negative age, should throw exception and default to 0
		Person p4 = new Person("Robert", -50);

		System.out.println(p4);
		
		// Kill Phil 1
		p1.die();
		
		// See if Phil 1 can work
		p1.doWork();
	}

}
