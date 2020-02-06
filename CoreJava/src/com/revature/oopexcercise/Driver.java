package com.revature.oopexcercise;

import java.lang.reflect.Method;

import com.revature.oopexcercise.Drivable;

public class Driver {

	public static void main(String[] args) {
		
		Car c1 = new Car("Grey","Toyota", "Camry", "2010", "Gasoline", 4);
		c1.Start();
		c1.Stop();
		c1.Accelerate();
		System.out.println("Details of Car1 object" + c1);
		System.out.println(Drivable.MY_INT);
		
		System.out.println("--------------------------------------------");
		System.out.println("--------------------------------------------");

		Car c2 = new Car("White","Mercedes Benz", "GLE350","2017","Gasoline", 4);
		c2.Start();
		c2.Stop();
		c2.Accelerate();
		System.out.println("Details of Car2 object" + c2);
		System.out.println(Drivable.MY_INT);
		
		System.out.println("--------------------------------------------");
		System.out.println("--------------------------------------------");
		
		Car c3 = new Car("Red","Tesla", "Model S", "2019","Electric", 4);
		c3.Start();
		c2.Stop();
		c2.Accelerate();
		System.out.println("Details of Car3 object" + c3);
		System.out.println(Drivable.MY_INT);
		
		//HashCode Method for a class
		Class c = Car.class;
		Method[] methods = c.getMethods();
		for(Method m: methods) {
			System.out.println("Hashcode for method " + m.getName() + " is " + m.hashCode());
		}

	}

}
