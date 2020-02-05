package com.revature.food;
/**
 * my ingredients interface
 * @author erpac
 *
 */
public interface Ingredients {
	boolean edible = true;
	
	/**
	 * default method takes a parameter of ingredients and prints them to user
	 * @param ingredients
	 */
	default void printIgredients(String ingredients) {
		System.out.println("this food contains:" + ingredients);
	}
	/**
	 * other default method to evaluate the taste
	 * @param taste
	 */
	default void howDoesItTaste(Boolean taste) {
		if(taste) {
			System.out.println("This is Yummy!");
		}else {
			System.out.println("Ewwww!");
		}
	}
}
