package com.revature.pillarsAssignment;

import com.revature.pillarsAssignment.animal.*;

public class Driver{
	public static void main(String[] args) {
		Dog d = new Dog("Dog food", 4);
		Cat c = new Cat("Cat food", 4);
		try {
			if (d.getFood() != c.getFood()) {
				// throws the exception to the catch block
				throw new CustomException();
			}
		} catch (CustomException e) {
			// prints the stacktrace of the exception
			e.printStackTrace();
		}finally {
			System.out.println("\n");
			d.eat();
			c.eat();
		}
	}
}
