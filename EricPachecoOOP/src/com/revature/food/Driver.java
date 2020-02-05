package com.revature.food;

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		System.out.println("hello are you hungry? Enter true or false");
		/**checking our user input
		 * 
		 */
		//temporaily storing a value to our hunger variable to jump to the code
		//that is outside of the try, catch block
		boolean hunger = false;
		Scanner in = new Scanner(System.in);
		try {
			
			hunger = in.nextBoolean();
			throw new NotHungryException("Thats not an option");
		}catch(NotHungryException err){
			err.printStackTrace();
		}
		if(hunger == false) {
			System.out.println("too bad that you're not hungry");
		}else {
		System.out.println("what time of day is it? \n Enter B, L or D");
		char meal = in.next().charAt(0);
		switch (meal) {
		case 'B':
			Oatmeal mealB = new Oatmeal();
			mealB.setReady(true);
			mealB.dayReady();
			break;
		case 'L':
			Burrito mealL = new Burrito();
			mealL.cook("Tortillas and beans");
			break;
		case 'D':
			Caserole mealD = new Caserole();
			mealD.howDoesItTaste(true);
			break;

		default:
			break;
			}
		

		}
	}

}
