package com.revature.util;

import java.util.Scanner;

import com.revature.doas.BankDaoImpl;
import com.revature.doas.UserDaoImpl;
import com.revature.models.Bank;
import com.revature.models.User;

public class UserUI {
	private static Scanner scan = new Scanner(System.in);
	private static Bank b = new Bank();
	private static BankDaoImpl bDi = new BankDaoImpl();
	private static char firstOption;
	
//	our inital prompt to the user
	public static void setUpUi() {
		// TODO Auto-generated method stub
		System.out.println("Welcome, push \'L\' to login or push \'N\' to make a new account: ");
		//TODO trim method might not work for string
		firstOption = scan.nextLine().toUpperCase().charAt(0);
		firstMenu();
	}
	/**
	 * the menu that logs the user in
	 */
	public static void firstMenu()	{
		switch (firstOption) {
//		Logging in  the user
		case 'L':
			System.out.println("Enter your User Name:");
			String userName = scan.nextLine();
			System.out.println("Enter your Password: ");
			String passWord = scan.nextLine();
//			TODO change control to login method
			b.setUserName(userName);
			b.setPassWord(passWord);
			
			bDi.login(userName, passWord, b);
			break; 	

		case 'N':
			System.out.println("Enter your name:");
			String un = scan.nextLine();
			System.out.println("Enter your passWord (passWord cannot be more than 10 characters long):");
			String pw = scan.nextLine();
			System.out.println("Enter your deposit Amount:");
			Double da = scan.nextDouble();
			scan.nextLine();
			UserDaoImpl uDi = new UserDaoImpl();
			String an = uDi.generateAccountNumber(4);
			Bank b2 = new Bank(un, pw, an, da);
			User user = new User(pw, un, an);
			uDi.createUser(user);
			bDi.createAccount(b2);
			logoutUser();
			break;
		}
		
	}
	
	/**menu for loging out the user.
	 * it will take the user back to the first menu if they say yes
	 */
	public static void logoutUser() {
		System.out.println("Would you like to logout? \'Y\'  or N'");
		char option = scan.nextLine().toUpperCase().charAt(0);
		switch (option) {
		case 'Y':
			//bDi.logout();
			//call the setupUi method
			System.out.println("goodbye.");
			setUpUi();
			
			break;
		case 'N':
			//make a method for transactions
			
			accountOptions();
			break;

		default:
			break;
		}
	}
	/**
	 * takes the user to the account menu options
	 */
	public static void accountOptions() {
		
		System.out.println("Press \'D\' to deposit money, \'W\' to withdraw or \'C\' to checkbalance: ");
		char moneyOption = scan.nextLine().toUpperCase().charAt(0);
		if (moneyOption == 'D') {
			//deposit
			System.out.println("Enter the amount that you would like to deposit: ");
			double deposit = scan.nextDouble();
			scan.nextLine();
//			TODO add bank obj
			negativeDepositOrWithDraw(bDi.depositMoney(b,deposit));
			
		}else if (moneyOption == 'W') {
			//withdraw	
			System.out.println("Enter the amount that you would like to Withdraw: ");
			double withdraw = scan.nextDouble();
			scan.nextLine();
			negativeDepositOrWithDraw(bDi.withdrawMoney(b, withdraw));
		}else if (moneyOption == 'C') {
			//checkbalance
			bDi.viewBallance(b);
		}
		logoutUser();
	}
	/**checking to see if we enter a negtive value to deposit or withdraw if we do then
	 * we just bring the user back to the start of the menu
	 * 
	 * @param deposit
	 */
	public static void negativeDepositOrWithDraw(int deposit) {
		if(deposit == 0) {
			System.out.println("Can't enter a negative value");
			accountOptions();
			
		}
		return;
		
	}
	
}
