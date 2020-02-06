package com.revature.types;

import java.util.ArrayList;
import java.util.List;

public class Autoboxing {

	public static void main(String[] args) {
		
		//boxing 
		int num1 = 35;
		Integer num2 = new Integer(num1);
		
		//unboxing 
		Integer num3 = new Integer(9);
		int num4 = num3.intValue();
		
		//autoboxing- implicit conversion
		int num5 = 35;
		Integer num6 = num5;
		
		//auto unboxing 
		Integer num7 = new Integer(9);
		int num8 = num7;
		
		List<Integer> myList = new ArrayList<>();
		Integer myInteger = new Integer(84);
		myList.add(myInteger);
		myList.add(85);
	}

}
