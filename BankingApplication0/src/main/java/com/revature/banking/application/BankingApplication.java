package com.revature.banking.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import com.revature.banking.BankTeller;

public class BankingApplication {
	/**
	 * This is used so that when I read a response I can always return an integer
	 * (all choices, of what action to take, will be a integer choice).
	 */
	private static final int EXIT_RESPONSE = 0;
	private static final String EXIT_STRING = "exit";

	private static final int LOGIN = 1;
	private static final int CREATE_LOGIN = 2;
	private static final int LOGOUT = 3;

	/**
	 * This is solely used to validate input so that I avoid a
	 * NumberFormatException. The program itself will decide whether or not the
	 * integer choice is valid.
	 */
	private static final Pattern INTEGER_PATTERN = Pattern.compile("\\d+");

	private final BankTeller teller;
	private final BufferedReader input;

	public BankingApplication(final BankTeller teller) {
		this.teller = teller;
		this.input = new BufferedReader(new InputStreamReader(System.in));
	}

	public void start() {
		// keep going until
		while (true) {

		}
	}

	private int loginPrompt() {
		System.out
				.println("What would you like to do? Please type 1 or 2 (you may exit at any time by typing \"exit\"");
		System.out.println("1) Login to an existing account");
		System.out.println("2) Sign up and create a new account");
		try {
			final int response = readChoice();

			switch (response) {
			case EXIT_RESPONSE:
				return EXIT_RESPONSE;
			case 1:
				return LOGIN;
			case 2:
				return CREATE_LOGIN;
			// the default is that the user typed an integer but wasn't 1 or 2 therefore
			// tell the user to type 1 or 2 and re-prompt
			default:
				System.out.println(response + " wasn't an option, please try again.");
				return loginPrompt();
			}
		} catch (IllegalChoiceException e) {
			System.out.println(
					"Please choose 1,2, or \"exit\". You typed: \"" + e.getMessage() + "\"; please try again.");
			return loginPrompt();
		}

	}

	private int readChoice() throws IllegalChoiceException {
		String line;
		try {
			line = input.readLine();
		} catch (IOException e) {
			// TODO Eventually should remove this stack trace for user (hopefully this never
			// happens
			e.printStackTrace();
			
		}
		if (line.trim().toLowerCase().equals(EXIT_STRING))
			return EXIT_RESPONSE;
		if (INTEGER_PATTERN.matcher(line).matches())
			return Integer.parseInt(line);

		throw new IllegalChoiceException(line);
	}
}
