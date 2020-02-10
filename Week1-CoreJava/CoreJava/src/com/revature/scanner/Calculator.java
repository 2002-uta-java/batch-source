package com.revature.scanner;

import java.util.Scanner;

public class Calculator {

	private static Scanner sc = new Scanner(System.in);

	public void calculate() {

		// ask the user for their operation

		System.out.println("Please enter the operation you'd like to perform:");
		String operation = sc.nextLine();
//		System.out.println("Your operation was: "+operation);

		int result = 0;
		int[] nums;

		// obtain numbers they'd like to perform the operation on
		switch (operation) {
		case "addition":
			// add together my numbers
			nums = getNums();
			result = nums[0] + nums[1];
			break;
		case "subtraction":
			// subtract numbers
			nums = getNums();
			result = nums[0] - nums[1];
			break;
		case "multiplication":
			nums = getNums();
			result = nums[0]*nums[1];
			break;
		default:
			System.out.println("unsupported operation");
			calculate();
		}

		// display results to user
		System.out.println("Your result is: "+ result);
		
		sc.close();

	}

	public int[] getNums() {
		int[] nums = new int[2];

		System.out.println("Please enter first number:");
		nums[0] = getNum();

		System.out.println("Please enter second number:");
		nums[1] = getNum();

		return nums;
	}

	public int getNum() {
		int num;
		String input = sc.nextLine();
		while (!input.matches("^\\d+$")) {
			System.out.println("Invalid input. Please input an integer.");
			input = sc.nextLine();
		}
		num = Integer.parseInt(input);

		return num;
	}
}
