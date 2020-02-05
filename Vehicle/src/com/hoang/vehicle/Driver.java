package com.hoang.vehicle;

public class Driver {
	public static void main(String[] args) {
		
		Car c = new Car (2,2);
		c.weight();
		c.horsePower();
		c.speed();
		System.out.println(c);
		
		System.out.println("----------------------------------");
		
		Van v = new Van (5,7);
		v.weight();
		v.horsePower();
		v.speed();
		System.out.println(v);
		
		System.out.println("----------------------------------");
		
		// covariant
		Car v2 = new Van (5,5);
		v2.weight();
		v2.horsePower();
		v2.speed();
		System.out.println(v2);
		
		Car c2 = new Car (2,2);
		Van v3 = new Van(5,7);
		// demonstrate hashcode() method
		System.out.println("hashcode for car c2: " + c2.hashCode());
		System.out.println("hashcode for van v3:" + v3.hashCode());
		
		// demonstrate equals() method 
		System.out.println("car c and car c2 is the equal?" + c.equals(c2));
		System.out.println("van v and van v3 is the equal?" + v.equals(v3));
		
		// example of NegativeWheelsException
		v2.setNumOfWheels(-4);
		

		
		
	}
}
