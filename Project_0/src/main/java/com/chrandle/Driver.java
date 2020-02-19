package com.chrandle;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.chrandle.models.*;
import com.chrandle.daos.*;
import com.chrandle.services.*;


public class Driver {

	public static void main(String[] args) {
		System.out.println("		PROGRAM START		\n");
		UserService uService = new UserService();
		AccountService aService = new AccountService();
		
		List<User> users = uService.getUsers();
		List<Account> accounts = aService.getAccounts();
		
		System.out.println("				USERs\n");
		for (User u: users) {
			System.out.println(u.toString());
		}
		
		System.out.println("				Accounts\n");
		for (Account a: accounts) {
			System.out.println(a.toString());
		}
		
		
		menu();
		
		System.out.println("\n		PROGRAM END		");
		return;
		
	}
	
	public static void menu() {
		Scanner input = new Scanner(System.in);
		while (true) {
			
			System.out.print("\nWhat would you like to do?\n"
					+ "Please enter a numeric input\n"
					+ "	1: Login to existing user\n"
					+ "	2: Create new user account\n"
					+ "	3. Exit application\n");
			int i = 0;
			
			try {
				i = Integer.parseInt(input.nextLine());
			} catch(Exception e) {
				System.out.print("\n------------------------------------\n"
						+ "Please enter a numeric input that corresponds to the options listed\n"
						+ "\n------------------------------------\n");
				continue;
			}
			
			switch (i) {
			case 1:
				
				break;
				
			case 2:
				
				break;
				
			case 3:
				System.out.println("\n		PROGRAM END		");
				System.exit(0);
				break;

			default:
				System.out.print("\n------------------------------------\n"
						+ "Please enter a numeric input that corresponds to the options listed\n"
						+ "\n------------------------------------\n");
				break;
			}
		}
	}
	
}
