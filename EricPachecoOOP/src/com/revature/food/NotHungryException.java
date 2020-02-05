package com.revature.food;
/**
 * our exception class
 * we will use this to check if user is hungry
 * @author erpac
 *
 */
public class NotHungryException extends Exception{
	/**
	 * calling the execption
	 * @param hungry
	 */
	public NotHungryException(String message) {
		super(message);
	}

//	public NotHungryException(String message) {
//		// TODO Auto-generated constructor stub
//	}
}
