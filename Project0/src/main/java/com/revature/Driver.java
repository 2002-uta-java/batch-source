package com.revature;

import com.revature.services.LoginService;

// Project0
// Developed by Nicholas Desmornes at Revature (~2/16/2020).

// Banking Application
// Communicates with user via console.
// Persists data in PostGreSQL database using JDBC.
// Showcases user accounts, usernames/passwords, login/out, bank accounts, balances,
// deposit/withdraw/view, create account, ...
// (hopefully include logging, checking/savings, transfer funds)

public class Driver {

	public static void main(String[] args) {
		System.out.println("Application opening...");
		
		LoginService app = new LoginService();
		app.login();
		
		System.out.println("Application closing...");

	}

}
