package com.revature;

public class Calculator {

	/*
	 * We will create an add method which accepts strings and which return an int
	 * 
	 * The method will accept 0, 1, or 2 numbers, delimited by a comma
	 * 
	 * Providing 0 numbers (an empty string) will return 0 Providing 1 number will
	 * return that number Providing 2 numbers will return that their sum Providing
	 * an invalid input will return -1
	 * 
	 * Additional Requirements: Allow the add method to handle an unknown amount of
	 * numbers Numbers greater than 1000 will be ignored Calling add with negative
	 * numbers will throw an exception
	 * 
	 */
	public static int add(String input) throws Exception {
		// implementation to come

		// handle null input
		if (input == null) {
			return -1;
		}
		// handle condition of an empty string
		if (input.contentEquals("")) {
			return 0;
		}
		String[] inputArr = input.split(",");

		// handle the condition of 1 number
		if (inputArr.length == 1) {
			if (inputArr[0].matches("-?\\d+$")) {
				return Integer.parseInt(inputArr[0]);
			} else {
				return -1;
			}
		}
		// handle the condition of 2 numbers
		if (inputArr.length == 2) {
			int result = 0;
			if (inputArr[0].matches("-?\\d+$") && inputArr[1].matches("-?\\d+$")) {
				return Integer.parseInt(inputArr[0]) + Integer.parseInt(inputArr[1]);
			} else {
				return -1;
			}
		}

		// handle unknown amount of numbers
		int sum = 0;
		for (int i = 0; i < inputArr.length; i++) {
			if (inputArr[i].matches("-?\\d+$")) {
				int number = Integer.parseInt(inputArr[i]);
				if (number < 0) {
					throw new NumberFormatException("Negative Numbers are not accepted");
				}
				if (!(number > 1000)) {
					sum = sum + number;
				}

			}

		}
		if (sum > 0) {
			return sum;

		}

		return -1;
	}

}
