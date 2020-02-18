package com.revature.banking.frontend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.banking.frontend.validation.Validation;
import com.revature.banking.services.BankAccountService;
import com.revature.banking.services.UserService;

/**
 * This is the interface for interactions with the bank.F
 * 
 * @author Jared F Bennatt
 *
 */
public abstract class BankInteraction {
	/**
	 * I'm using negatives here so that these don't collide with a chosen menu
	 * option (which will be 1, 2, etc.)
	 */
	public static final int SUCCESS = -1;
	public static final int LOGOUT = -2;
	public static final int EXIT = -3;
	public static final int FAILURE = -4;

	public static final String EXIT_STRING = "exit";
	public static final String LOGOUT_STRING = "logout";

	protected final CLI io;
	protected final BankAccountService baService;
	protected final UserService uService;
	
	private String title;
	private List<BankInteraction> menuOptions = new ArrayList<BankInteraction>();

	protected BankInteraction(final CLI io, final UserService uService, final BankAccountService baService) {
		super();
		this.io = io;
		this.uService = uService;
		this.baService = baService;
	}

	/**
	 * Returns the title for this interaction (used to populate the menu)
	 * 
	 * @return A string representation of this interaction
	 */
	public String getTitle() {
		return title;
	}

	protected void setTitle(final String title) {
		this.title = title;
	}

	public int interact(final int choice) throws IOException {
		return menuOptions.get(choice - 1).interact();
	}

	public abstract int interact() throws IOException;

	protected void addMenuOption(final BankInteraction menuOption) {
		menuOptions.add(menuOption);
	}

	public int getMenu() {
		int option = 1;
		while (true) {
			for (final BankInteraction menuOption : menuOptions) {
				io.println(option++ + ". " + menuOption.getTitle());
			}
			io.println("Please choose an option (or you can choose " + EXIT_STRING + " or " + LOGOUT_STRING + ").");

			try {
				final String chosen = io.readLine();
				if (!Validation.isNaturalNumber(chosen)) {
					if (chosen.equalsIgnoreCase(EXIT_STRING))
						return EXIT;
					if (chosen.equalsIgnoreCase(LOGOUT_STRING))
						return LOGOUT;
					// else this is a bad input
					io.println("Please choose an option, you typed " + chosen);

					if (!this.retry())
						return FAILURE;
					// try again
				} else {
					// number validation was successful, create an int and make sure it's a valid
					// option
					final int chosenOption = Integer.parseInt(chosen);
					if (chosenOption < 1 || chosenOption > menuOptions.size()) {
						io.println(chosenOption + " is not a valid option");

						if (!this.retry())
							return FAILURE;
					} else
						return chosenOption;
				}

			} catch (IOException e) {
				Logger.getRootLogger().error("There was in IOException: " + e.getMessage());
			}
		}
	}

	public boolean retry() throws IOException {
		io.println("Would you like to retry (y/n)?");
		final String response = io.readLine().trim();

		if (response.equalsIgnoreCase("y")) {
			io.println();
			io.println();
			return true;
		} else if (response.equalsIgnoreCase("n"))
			return false;
		else {
			io.println("You didn't type y or n, so I'm taking that as a no.");
			return false;
		}
	}
}
