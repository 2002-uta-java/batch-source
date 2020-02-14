package com.revature.banking.application;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Pattern;

import com.revature.banking.BankTeller;
import com.revature.banking.TransactionException;

public class BankingApplication {
	/**
	 * This is used so that when I read a response I can always return an integer
	 * (all choices, of what action to take, will be a integer choice).
	 */
	private static final int EXIT_RESPONSE = 0;
	private static final String EXIT_STRING = "exit";

	private static final int LOGOUT_RESPONSE = -1;
	private static final String LOGOUT_STRING = "logout";

	private static final int YES_RESPONSE = 1;
	private static final int NO_RESPONSE = 2;
	private static final String YES_STRING = "y";
	private static final String NO_STRING = "n";

	private static final int LOGIN = 1;
	private static final int CREATE_LOGIN = 2;

	private static final int VIEW_BALANCE = 1;
	private static final int VIEW_BANK_STATEMENT = 2;
	private static final int ADD_FUNDS = 3;
	private static final int WITHDRAW_FUNDS = 4;
	private static final int OPEN_NEW_ACCOUNT = 5;
	private static final int CLOSE_ACCOUNT = 6;
	private static final int TRANSFER_FUNDS = 7;
	private static final int REQUEST_FUNDS = 8;

	private static final String VIEW_BALANCE_PROMPT = "View balance.";
	private static final String VIEW_BANK_STATEMENT_PROMPT = "View transaction history.";
	private static final String ADD_FUNDS_PROMPT = "Add funds.";
	private static final String WITHDRAW_FUNDS_PROMPT = "Withdraw Funds.";
	private static final String OPEN_NEW_ACCOUNT_PROMPT = "Open new account";
//	private static final String REQUEST_FUNDS_PROMPT = "Request funds from external account.";
	private static final String TRANSFER_FUNDS_PROMPT = "Transfer funds from one account to another.";
	private static final String CLOSE_ACCOUNT_PROMPT = "Close an account.";

	/**
	 * Using a TreeMap so that I can iterate over the keys in ascending order
	 */
	private static final TreeMap<Integer, String> TRANSACTION_PROMPTS = new TreeMap<>();

	static {
		TRANSACTION_PROMPTS.put(VIEW_BALANCE, VIEW_BALANCE_PROMPT);
		TRANSACTION_PROMPTS.put(VIEW_BANK_STATEMENT, VIEW_BANK_STATEMENT_PROMPT);
		TRANSACTION_PROMPTS.put(ADD_FUNDS, ADD_FUNDS_PROMPT);
		TRANSACTION_PROMPTS.put(WITHDRAW_FUNDS, WITHDRAW_FUNDS_PROMPT);
		TRANSACTION_PROMPTS.put(OPEN_NEW_ACCOUNT, OPEN_NEW_ACCOUNT_PROMPT);
		TRANSACTION_PROMPTS.put(CLOSE_ACCOUNT, CLOSE_ACCOUNT_PROMPT);
		TRANSACTION_PROMPTS.put(TRANSFER_FUNDS, TRANSFER_FUNDS_PROMPT);
//		TRANSACTION_PROMPTS.put(REQUEST_FUNDS, REQUEST_FUNDS_PROMPT);
	}

	/**
	 * This is solely used to validate input so that I avoid a
	 * NumberFormatException. The program itself will decide whether or not the
	 * integer choice is valid.
	 */
	private static final Pattern INTEGER_PATTERN = Pattern.compile("^\\d+$");

	/**
	 * Regex for currency. Must have at least one digit to the left of decimal. Then
	 * the decimal can be there or not, if so, there must be exatly one decimal
	 * point followed by 1 or 2 digits.
	 * 
	 * I allow this to be negative (the Bank will throw an exception if it is)
	 */
	private static final Pattern DECIMAL_PATTERN = Pattern.compile("-?\\d+(\\.\\d{1,2})?");

	private final BankTeller teller;
	private final Console console;
	private final BufferedReader br;

	public BankingApplication(final BankTeller teller) {
		this.teller = teller;
		this.console = System.console();
		if (console == null)
			br = new BufferedReader(new InputStreamReader(System.in));
		else
			br = null;
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
					boolean logout = false;

					// if they choose something besides exit or logout, do the transaction, exit the
					// switch statement, then start back over with begin_transaction (i.e. stay
					// logged in and prompt for another transaction)
					switch (choice) {
					case EXIT_RESPONSE:
						return;// exit
					case LOGOUT_RESPONSE:
						System.out.println();
						System.out.println();
						logout = true;
						break;
					case VIEW_BALANCE:
						viewBalancePrompt();
						break;
					case VIEW_BANK_STATEMENT:
						viewBankStatementPrompt();
						break;
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
						logout = closeAccountAndLogoutPrompt();
						break;
					default:
						System.out.println("Please choose a valid transaction. You chose " + choice);
					}
					if (logout) {
						teller.userLogout();
						continue begin_prompt;
					}
					// else continue to transaction loop (print white space below)
				} catch (IllegalChoiceException e) {
					System.out.println("Please choose a number or type " + LOGOUT_STRING + " or " + EXIT_STRING
							+ ". You typed: \"" + e.getMessage() + "\".");
				} catch (TransactionException e) {
					System.out.println(e.getMessage());
					// only way this should happen is if we were trying to logout, so go ahead and
					// go back to login prompt
					continue begin_prompt;
				}

				// the only way out of this method is to type exit (if you reach this point, the
				// user never logged out so should be reprompted to choose another transaction)

				// print some white space to make it more obvious that we're starting over
				System.out.println();
				System.out.println();
				continue begin_transaction;// continue transaction loop (this isn't actually necessary)
			}
		}
	}

	private void viewBankStatementPrompt() {
		while (true) {
			try {
				final List<String> accounts = teller.viewAccounts();
				int choice = 1;

				if (accounts.size() > 1) {
					System.out.println("Plese choose an account to view the statement for (1, 2, etc)");
					choice = chooseAccountPrompt(accounts);
				}

				List<String> statement = teller.viewStatement(choice - 1);

				for (final String transaction : statement)
					System.out.println('\t' + transaction);
				// exit method (it was successful)
				return;
			} catch (TransactionException e) {
				System.out.println(e.getMessage());
				return;// exit method if we get an error
			} catch (IllegalChoiceException e) {
				System.out.println(e.getMessage());
				if (!retryPrompt())
					return;
				// else continue loop and retry
			}
		}
	}

	private void viewBalancePrompt() {
		try {
			final List<String> accounts = teller.viewAccounts();
			System.out.println("Accounts:");
			for (final String account : accounts)
				System.out.println('\t' + account);
		} catch (TransactionException e) {
			System.out.println("Unable to view your accounts: " + e.getMessage());
		}
	}

	/**
	 * Returns a boolean to signal to the application to logout.
	 * 
	 * @return true if you should logout, false otherwise.
	 */
	private boolean closeAccountAndLogoutPrompt() {
		while (true) {
			try {
				final List<String> accounts = teller.viewAccounts();

				if (accounts.size() > 1) {
					System.out.println("Which account would you like to close (please choose 1, 2, etc.?");
					final int choice = chooseAccountPrompt(accounts);
					final List<String> remaining = teller.closeAccount(choice - 1);
					System.out.println("Here are your remaining accounts:");
					for (final String account : remaining)
						System.out.println('\t' + account);
				} else {
					// this is the only account which means it will remove the user as well
					System.out.println(
							"This is your only account. If you remove it, you will be removed from our system and no longer be able to log in.");
					System.out.println("Do you still want to close this account and be removed? (y/n)");
					final int choice = readChoice();

					if (choice == YES_RESPONSE) {
						// close only account (index 0)
						teller.closeAccount(0);
						teller.deleteUser();
						System.out.println("Your account has been close and you have been removed from the system");
						return true;
					} else if (choice == NO_RESPONSE) {
						System.out.println("You account has not been removed");
						return false;
					} else {
						throw new IllegalArgumentException("Please choose y/n.");
					}
				}
			} catch (TransactionException e) {
				System.out.println(e.getMessage());
				return false;// don't try to redo on banking error
			} catch (IllegalChoiceException e) {
				System.out.println(e.getMessage());
				if (!retryPrompt())
					return false;
				// else keep looping and retry
			}
		}

	}

	private void transferFundsPrompt() {
		while (true) {
			try {
				final List<String> accounts = teller.viewAccounts();
				if (accounts.size() < 2) {
					System.out.println("You only have one account. You cannot transfer funds.");
					return;
				}
				// else

				// show available accounts
				System.out.println("Please choose an account to withdraw funds from:");
				printAccountChoices(accounts);
				int choiceW = chooseAccount(accounts.size());
				System.out.println("Please choose an account to transfer funds to:");
				printAccountChoices(accounts);
				int choiceT = chooseAccount(accounts.size());

				if (choiceW == choiceT)
					throw new IllegalChoiceException(
							"You cannot transfer to the same account you're withdrawing (that wouldn't be a transfer, now would it)");

				System.out.println("How much would you like to transfer?");
				double amount = readAmount();

				final List<String> modifiedAccounts = teller.transferFunds(choiceW - 1, choiceT - 1, amount);
				System.out.println("Your funds have been transferred:");
				for (final String account : modifiedAccounts)
					System.out.println('\t' + account);
				// this was successful, exit method
				return;
			} catch (TransactionException e) {
				System.out.println(e.getMessage());
				return;// just return if transaction failed
			} catch (IllegalChoiceException e) {
				System.out.println(e.getMessage());
				if (!retryPrompt())
					return;// exit
				System.out.println();
				System.out.println();
				// loop, try again
			}

		}
	}

	private void requestFundsPrompt() {
		// TODO Auto-generated method stub

	}

	private void openNewAccountPrompt() {
		try {
			final String newAccount = teller.openNewAccount();
			System.out.println("Here is your new account");
			System.out.println('\t' + newAccount);
		} catch (TransactionException e) {
			System.out.println(e.getMessage());
		}
	}

	private void withdrawFundsPrompt() {
		while (true) {
			try {
				final List<String> accounts = teller.viewAccounts();
				int choice = 1;

				if (accounts.size() > 1) {
					System.out.println("Please choose an account to withdraw from:");
					choice = chooseAccountPrompt(accounts);
				}

				System.out.println("How much would you like to withdraw?");
				double withdrawal = readAmount();

				final String updatedAccount = teller.withdrawFunds(choice - 1, withdrawal);

				System.out.println('\t' + updatedAccount);
				return;// this was successful, exit method
			} catch (TransactionException e) {
				System.out.println(e.getMessage());
				return;// exit method if there's a banking error
			} catch (IllegalChoiceException e) {
				System.out.println(e.getMessage());
				if (!retryPrompt())
					return; // exit method

				// else try again
				System.out.println();
				System.out.println();
			}
		}
	}

	private void addFundsPrompt() {
		// keep trying until they add funds or don't respond with "y" when asked to try
		// again.
		while (true) {
			try {
				// get the accounts
				final List<String> accounts = teller.viewAccounts();
				int choice = 1;// we're going to subtract 1 from choice, if there is only 1 account that would
								// have to be the "first" account

				// if there are more than one accounts, display them all and let the user choose
				// which one to place money into
				if (accounts.size() > 1) {
					System.out.println("Please choose an account to add funds to (1, 2, etc.):");
					choice = chooseAccountPrompt(accounts);
				} else {
					// display their one account for them
					System.out.println('\t' + accounts.get(0));
				}

				System.out.println("How much would you like to add (please entire a decimal or integer amount)? ");
				final double amount = readAmount();

				// give the teller the index of the account to add to (choice - 1) and the
				// amount to add (both as strings).
				final String updatedAccount = teller.addFunds(choice - 1, amount);
				System.out.println("New Balance:");
				System.out.println('\t' + updatedAccount);
				// this was successful, return from this method
				return;
			} catch (TransactionException e) {
				System.out.println(e.getMessage());
				return; // exit method (don't try to re-add
			} catch (IllegalChoiceException e) {
				System.out.println(e.getMessage());
				if (!retryPrompt())
					return;// exit method
				// continue loop and retry to add funds
				System.out.println();
				System.out.println();
			}
		}
	}

	private void loginPrompt() {
		System.out.println("user name: ");
		final String username = readLine();
		System.out.println("password: ");
		final String password = readPassword();

		try {
			teller.userLogin(username, password);
		} catch (TransactionException e) {
			System.out.println(e.getMessage());
		}
	}

	private void createLoginPrompt() {
		System.out.println("We will need your name and tax id:");
		System.out.println("First Name: ");
		final String firstName = readLine();
		System.out.println("Last Name: ");
		final String lastName = readLine();
		System.out.println("Tax ID: ");
		final String taxID = readLine();

		String username = null;

		while (true) {
			System.out.println("Please choose a username: ");
			username = readLine();
			final String explanation = teller.checkUserName(username);
			if (explanation != null) {
				System.out.println(explanation);
				if (!retryPrompt())
					return;
			} else
				break;// break out of loop if username is valid
		}

		String password = null;
		while (true) {
			System.out.println("Please provide a password: ");
			password = readPassword();
			final String explanation = teller.checkPassword(password);
			if (explanation != null) {
				System.out.println(explanation);
				if (!retryPrompt())
					return;
			} else
				break;// break out of loop is password is valid
		}

		try {
			final String newAccount = teller.createAccount(firstName, lastName, taxID, username, password);
			System.out.println("Here is your new account:");
			System.out.println('\t' + newAccount);
		} catch (TransactionException e) {
			System.out.println(e.getMessage());
		}
	}

	private String readPassword() {
		try {
			return console != null ? new String(console.readPassword()) : br.readLine();
		} catch (IOException e) {
			System.out.println("well this is embarassing...I'll be exiting now");
			System.exit(1);
			return null;// don't know how this will happen
		}
	}

	private boolean retryPrompt() {
		System.out.println("Would you like to try again?(" + YES_STRING + "/" + NO_STRING + ")");
		try {
			final int choice = readChoice();
			if (choice != YES_RESPONSE)
				return false;// do no retry unless they type "y"
			else {
				// add padding for retrying prompt
				System.out.println();
				System.out.println();
				return true;
			}
		} catch (IllegalChoiceException e1) {
			// don't care what they typed if it wasn't "y"
			return false;// stop trying to add funds
		}
	}

	/**
	 * This assumes there's more than one account (there's not check against that)
	 * 
	 * @param accounts
	 * @return
	 * @throws IllegalChoiceException
	 */
	private int chooseAccountPrompt(final List<String> accounts) throws IllegalChoiceException {
		printAccountChoices(accounts);
		return chooseAccount(accounts.size());
	}

	private int chooseAccount(final int numAccounts) throws IllegalChoiceException {
		final int choice = readChoice();
		if (choice < 1 || choice > numAccounts)
			throw new IllegalArgumentException("You must choose a number from 1 to " + numAccounts);

		// else choice was valid, return it
		return choice;
	}

	private void printAccountChoices(final List<String> accounts) {
		int numAccounts = 0;
		for (final String account : accounts) {
			System.out.println("\t" + ++numAccounts + ") " + account);
		}
	}

	private int beginningPrompt() {
		// the only way out of this loop is to give a valid input (i.e. 1, 2, or exit)
		while (true) {
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
					System.out.println();
					System.out.println();
					break;
				// the default is that the user typed an integer but wasn't 1 or 2 therefore
				// tell the user to type 1 or 2 and re-prompt
				default:
					System.out.println(response + " wasn't an option, please try again.");
					System.out.println();
					System.out.println();
				}
			} catch (IllegalChoiceException e) {
				System.out.println(
						"Please choose 1,2, or \"exit\". You typed: \"" + e.getMessage() + "\"; please try again.");
				System.out.println();
				System.out.println();
			}
		}

	}

	private String readLine() {
		try {
			return console != null ? console.readLine().trim() : br.readLine().trim();
		} catch (IOException e) {
			System.out.println("well this is embarassing...I'll be exiting now");
			System.exit(1);
			return null;// don't know how this will happen
		}
	}

	private double readAmount() throws IllegalChoiceException {
		final String line = readLine();

		if (DECIMAL_PATTERN.matcher(line).matches())
			return Double.parseDouble(line);

		// else throw exception
		throw new IllegalChoiceException(line + " isn't a valid amount");
	}

	private int readChoice() throws IllegalChoiceException {
		String line = readLine().toLowerCase();

		switch (line) {
		case EXIT_STRING:
			return EXIT_RESPONSE;
		case LOGOUT_STRING:
			return LOGOUT_RESPONSE;
		case NO_STRING:
			return NO_RESPONSE;
		case YES_STRING:
			return YES_RESPONSE;
		}

		if (INTEGER_PATTERN.matcher(line).matches())
			return Integer.parseInt(line);

		throw new IllegalChoiceException(line);
	}
}
