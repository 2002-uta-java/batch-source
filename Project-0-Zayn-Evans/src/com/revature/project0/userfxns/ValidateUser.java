package com.revature.project0.userfxns;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

import com.revature.project0.services.CustomerAccountService;
import com.revature.project0.ui.Menu;

public class ValidateUser {
	
	public static void login(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter username: ");
		//Scanner user = new Scanner(System.in);
		String username = sc.nextLine();
		//CustomerAccountService some = new CustomerAccountService();
		//some.checkCustomerAccountUsername(username);
	}
	
	public static void loginp() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter password: ");
		//Scanner passw = new Scanner(System.in);
		String password = sc.nextLine();
	}
	
	public static void menu2() {
		Scanner sc = new Scanner(System.in);
		String menu2msg = "1) Withdraw\n2) Deposit\n3) Show Account Balance\n4) Log out";
		System.out.println(menu2msg);
		boolean x = true;
		double amt = 0;
		
		Menu.balance = 0;
		
		while (x = true) {
			switch (sc.nextLine().trim()) {
			
			case "logout":
				//System.out.println("Thanks for using Zayn's Bank");
				x = false;
				new Menu();
				break;
				
			case "1":
				System.out.println("Enter withdraw amount: ");
				amt = sc.nextDouble();
				if (Menu.balance - amt < 0) {
					Menu.balance -= amt;
				} else {
					System.out.println("You cant overdraw your account!");
				}
				
				NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));
		        String val = nf.format(Menu.balance);
		        
				System.out.println("New balance is: "+val);
				System.out.println(menu2msg);
				break;
				
			case "2":
				System.out.println("Enter deposit amount: ");
				amt = sc.nextDouble();
				Menu.balance += amt;
				NumberFormat nf2 = NumberFormat.getInstance(new Locale("en", "US"));
		        String val2 = nf2.format(Menu.balance);
				System.out.println("New balance is: "+val2);
				System.out.println(menu2msg);
				break;
				
			case "3":
				NumberFormat nf3 = NumberFormat.getInstance(new Locale("en", "US"));
		        String val3 = nf3.format(Menu.balance);
				System.out.println("Your account balance is "+val3);
				System.out.println(menu2msg);
				break;
				
			default:
				System.out.println("Invalid input, please use 1-3 or 'logout' to select one of the menu options");
				continue;
			}
		}
	}
	
	// compare entered credentials with db
	// if not, have user re-enter credentials
	

}
