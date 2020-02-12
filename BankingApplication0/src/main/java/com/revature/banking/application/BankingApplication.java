package com.revature.banking.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.revature.banking.BankTeller;

public class BankingApplication {

	private final BankTeller teller;
	private final BufferedReader input;

	public BankingApplication(final BankTeller teller) {
		this.teller = teller;
		this.input = new BufferedReader(new InputStreamReader(System.in));
	}

	public void start() {
		boolean exit = false;
		while (!exit) {
			boolean logout = false;
			exit = loginPrompt();
			while (!exit && !logout) {

			}
		}
	}

	private boolean loginPrompt() {
		System.out.println("What would you like to do? (please type 1 or 2)");
	}
}
