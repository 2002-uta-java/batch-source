package com.revature;

public class Calculator {
	
	/*
	 * We will create an add method which accepts strings and which return an int
	 * 
	 * The method will accept 0, 1, or 2 numbers, delimited by a comma
	 * 
	 * Providing 0 numbers (an empty string) will return 0
	 * Providing 1 number will return that number
	 * Providing 2 numbers will return their sum
	 * Providing an invalid input will return -1
	 * 
	 * Additional Requirements:
	 * Allow the add method to handle an unknown amount of numbers
	 * Numbers greater than 1000 will be ignored 
	 * Calling add with negative numbers will throw an exception
	 * 
	 */
	
	public static int add(String input) {
		// handle null input 
		if(input==null) {
			return -1;
		}
		
		// handle condition of an empty string 
		if(input.equals("")) {
			return 0;
		}
		
		String[] inputArr = input.split(",");
		int sum = 0;
		for (String i: inputArr) {
			
			if (i.charAt(0) =='-') 
			{
				throw new IllegalArgumentException("Cannot call add on negative numbers");
			} else if(!(i.matches("^\\d+$"))){
			 return -1;
			}
			else {
				
				if (Integer.parseInt(i) > 1000) {
					sum += 0;
				} else {
					sum += Integer.parseInt(i);
				}
			}
		}
		
		return sum;
	}
}
