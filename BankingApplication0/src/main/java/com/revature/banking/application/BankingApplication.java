package com.revature.banking.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

import com.revature.banking.BankTeller;

public class BankingApplication {
	/**
	 * This is used so that when I read a response I can always return an integer
	 * (all choices, of what action to take, will be a integer choice).
	 */
	private static final int EXIT_RESPONSE = 0;
	private static final String EXIT_STRING = "exit";

	private static final int LOGOUT_RESPONSE = -1;
	private static final String LOGOUT_STRING = "logout";

	private static final int LOGIN = 1;
	private static final int CREATE_LOGIN = 2;

	private static final int ADD_FUNDS = 1;
	private static final int WITHDRAW_FUNDS = 2;
	private static final int OPEN_NEW_ACCOUNT = 3;
	private static final int REQUEST_FUNDS = 6;
	private static final int TRANSFER_FUNDS = 5;
	private static final int CLOSE_ACCOUNT = 4;

	private static final String ADD_FUNDS_PROMPT = "Add funds.";
	private static final String WITHDRAW_FUNDS_PROMPT = "Withdraw Funds.";
	private static final String OPEN_NEW_ACCOUNT_PROMPT = "Open new account";
	private static final String REQUEST_FUNDS_PROMPT = "Request funds from external account.";
	private static final String TRANSFER_FUNDS_PROMPT = "Transfer funds from one account to another.";
	private static final String CLOSE_ACCOUNT_PROMPT = "Close an account.";

	/**
	 * Using a TreeMap so that I can iterate over the keys in ascending order
	 */
	private static final TreeMap<Integer, String> TRANSACTION_PROMPTS = new TreeMap<>();

	static {
		TRANSACTION_PROMPTS.put(ADD_FUNDS, ADD_FUNDS_PROMPT);
		TRANSACTION_PROMPTS.put(WITHDRAW_FUNDS, WITHDRAW_FUNDS_PROMPT);
		TRANSACTION_PROMPTS.put(OPEN_NEW_ACCOUNT, OPEN_NEW_ACCOUNT_PROMPT);
		TRANSACTION_PROMPTS.put(CLOSE_ACCOUNT, CLOSE_ACCOUNT_PROMPT);
		TRANSACTION_PROMPTS.put(TRANSFER_FUNDS, TRANSFER_FUNDS_PROMPT);
		TRANSACTION_PROMPTS.put(REQUEST_FUNDS, REQUEST_FUNDS_PROMPT);
	}

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

	public void begin() {
		// keep looping until user chooses to exit
		begin_prompt: while (true) {

			switch (beginningPrompt()) {
			case CREATE_LOGIN:
				createLoginPrompt();
				break;
			case LOGIN:
				loginPrompt();
				break;
			case EXIT_RESPONSE:
				// exit loop and function
				return;
			}
			// keep going until you exit

			int promptNo;

			// keep looping until user chooses to logout or exit
			// If the user chooses to logout, they will jump to the above (outer) loop and
			// if they choose to exit, the method will return and break out of both loops
			begin_transaction: while (true) {

				promptNo = 1;

				System.out.println("What would you like to do? Choose an option or type \"" + LOGOUT_STRING
						+ "\" to logout or \"" + EXIT_STRING + "\" to exit.");
				for (final int transaction : TRANSACTION_PROMPTS.keySet()) {
					System.out.println(promptNo++ + ") " + TRANSACTION_PROMPTS.get(transaction));
				}

				try {
					final int choice = readChoice();

					// if they choose something besides exit or logout, do the transaction, exit the
					// switch statement, then start back over with begin_transaction (i.e. stay
					// logged in and prompt for another transaction)
					switch (choice) {
					case EXIT_RESPONSE:
						return;// exit
					case LOGOUT_RESPONSE:
						// goto beginning of method
						System.out.println();
						System.out.println();
						continue begin_prompt;
					case ADD_FUNDS:
						addFundsPrompt();
						break;
					case WITHDRAW_FUNDS:
						withdrawFundsPrompt();
						break;
					case OPEN_NEW_ACCOUNT:
						openNewAccountPrompt();
						break;
					case REQUEST_FUNDS:
						requestFundsPrompt();
						break;
					case TRANSFER_FUNDS:
						transferFundsPrompt();
						break;
					case CLOSE_ACCOUNT:
						closeAccountPrompt();
						break;
					default:
						System.out.println("Please choose a valid transaction. You chose " + choice);
						System.out.println();
						System.out.println();
						continue begin_transaction;
					}
				} catch (IllegalChoiceException e) {
					System.out.println("Please choose a number or type " + LOGOUT_STRING + " or " + EXIT_STRING
							+ ". You typed: \"" + e.getMessage() + "\".");
					System.out.println();
					System.out.println();
					continue begin_transaction;
				}

				// the only way out of this method is to type exit (if you reach this point, the
				// user never logged out so should be reprompted to choose another transaction)

				// print some white space to make it more obvious that we're starting over
				System.out.println();
				System.out.println();
			}
		}
	}

	private void closeAccountPrompt() {
		// TODO Auto-generated method stub

	}

	private void transferFundsPrompt() {
		// TODO Auto-generated method stub

	}

	private void requestFundsPrompt() {
		// TODO Auto-generated method stub

	}

	private void openNewAccountPrompt() {
		// TODO Auto-generated method stub

	}

	private void withdrawFundsPrompt() {
		// TODO Auto-generated method stub

	}

	private void addFundsPrompt() {
		// TODO Auto-generated method stub

	}

	private void loginPrompt() {
		// TODO Auto-generated method stub

	}

	private void createLoginPrompt() {
		// TODO Auto-generated method stub

	}

	private int beginningPrompt() {
		System.out.println("What would you like to do? Please type 1 or 2 (you may exit at any time by typing \""
				+ EXIT_STRING + "\")");
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
			case LOGOUT_RESPONSE:
				System.out.println("You aren't logged in. Please select again.");
				return beginningPrompt();
			// the default is that the user typed an integer but wasn't 1 or 2 therefore
			// tell the user to type 1 or 2 and re-prompt
			default:
				System.out.println(response + " wasn't an option, please try again.");
				return beginningPrompt();
			}
		} catch (IllegalChoiceException e) {
			System.out.println(
					"Please choose 1,2, or \"exit\". You typed: \"" + e.getMessage() + "\"; please try again.");
			return beginningPrompt();
		}

	}

	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			System.out.println(
					"Could not read your input and the program is now exiting (if program does not exit, try pressing ctrl-c). If you continue to have this issue please copy the error message below and send to it to a developer.");
			e.printStackTrace();
			System.exit(1);
			return null; // not sure how (or why) System.exit(1) would fail
		}
	}

	private int readChoice() throws IllegalChoiceException {
		String line = readLine();
		if (line.trim().equalsIgnoreCase(EXIT_STRING))
			return EXIT_RESPONSE;
		if (line.trim().equalsIgnoreCase(LOGOUT_STRING))
			return LOGOUT_RESPONSE;

		if (INTEGER_PATTERN.matcher(line).matches())
			return Integer.parseInt(line);

		throw new IllegalChoiceException(line);
	}
}
