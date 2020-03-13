package com.revature.shapes;

import com.revature.exceptions.NegativeSidesException;

public class Driver {

	public static void main(String[] args) {
		Rectangle r = new Rectangle(6,2);
		System.out.println(r);
		r.draw();
		
		
		Square s = new Square(5);
		System.out.println(s.toString());
		s.draw();
		
		
		Rectangle s2 = new Square(3);
		s2.draw();
		System.out.println(s2.calculateArea());
		
		s2.setNumOfSides(-6);
//		System.out.println("some other stuff we want our program to do");
	}

}
