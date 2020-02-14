package com.revature;

public class Calculator {
	public static void main(String[] args) {
		System.out.println(add("-1"));
	}
	
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
		
		// handle the condition of 1 number 
<<<<<<< HEAD
		
		boolean allNumeric = true;
		for (String num : inputArr) {
			if (!num.matches("^-?\\d+$")) {
				allNumeric = false;
			}
		}
		if (allNumeric) {
			int sum = 0;
			for (String num : inputArr) {
				Integer currNum = Integer.parseInt(num);
				if (currNum < 0) {
					System.out.println("I'm triggered");
					throw new IllegalArgumentException("you cannot put negative number");
				}
				if (currNum <= 1000) {
					sum += currNum;
				}
=======
		if(inputArr.length == 1) {
			if(inputArr[0].matches("-?\\d+$")) {
				return Integer.parseInt(inputArr[0]);
			} else {
				return -1;
			}
		}
		
		// handle the condition of 2 numbers
		if(inputArr.length == 2) {
			if(inputArr[0].matches("-?\\d+$") && inputArr[1].matches("-?\\d+$")) {
				return Integer.parseInt(inputArr[0])+Integer.parseInt(inputArr[1]);
			} else {
				return -1;
>>>>>>> 9ded321fd172f86d60e1031bf772c3940067fd6d
			}
			return sum;
		}
		
		
		return -1;
	}
	
	

}
