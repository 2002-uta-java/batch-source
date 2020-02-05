package com.revature.cuisines;

public class Driver {

	public static void main(String[] args) {
		Cuisine c = new Chinese("rice");
		c.cook();
		c.eat();
		
		System.out.println(c.getFood());
		c.setUtensil("fork");

	}

}
