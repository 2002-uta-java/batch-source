package com.revature.ui;

import java.util.Scanner;

public class UIDriver {
	
	static Scanner in = new Scanner(System.in);
	public static void start() {
		while(true) {
			printLine();
			int option = printMainMenuOptions();
			switch(option) {
				case 1:
					System.out.println("Signing in........");
					break;
				case 2:
					System.out.println("Signing up.........");
					break;
				case 3:
					System.out.println("Exit...............");
					break;
				default:
					System.out.println("Invalid input");
			}
		}
	}
	
	
	public static int printMainMenuOptions() {
		int option = 0;
		System.out.println("\n\nSelect one of the following options:\n");
		System.out.println("[ 1 ] Sign in.\n[ 2 ] Sign up.\n[ 3 ] Exit.\n>>");
		if(in.hasNextInt()) {
			option = in.nextInt();
			System.out.println(option);
		}
		return option;
	}
	
	
	public static void printLine() {
		System.out.println("************************************");
		System.out.println("************************************");
		System.out.println("*****Hello, Welcome to Revabank*****\n");
		System.out.println("************************************");
		System.out.println("************************************");
	}
}

//System.out.println("Test");
//UserServices us = new UserServices();
//User user = us.getUserByUsername("israel");
//System.out.println(user);
//user.setPassword("NewestNewPassword");
//if(us.updateUser(user))
//	System.out.println(user);
//AccountServices as = new AccountServices();
//Account account = as.getAccount(1);
//System.out.println(account);
//if(as.deposit(account, 3500))
//	System.out.println(account);