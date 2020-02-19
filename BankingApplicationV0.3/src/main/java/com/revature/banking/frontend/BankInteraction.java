package com.revature.banking.frontend;

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
	public static final int TRY_AGAIN = -5;
	public static final int YES = -6;
	public static final int NO = -7;

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

	public int interact(final int choice) {
		final int rtrn = menuOptions.get(choice - 1).interact();
		Logger.getRootLogger().debug("interact returned " + rtrn);
		return rtrn;
	}

	public void promptToContinue() {
		io.println("Press the any key to continue");
		io.readLine();
	}

	public abstract int interact();

	protected void addMenuOption(final BankInteraction menuOption) {
		menuOptions.add(menuOption);
	}

	protected int getNumMenuOptions() {
		return menuOptions.size();
	}

	protected BankInteraction getMenuOption(final int i) {
		return menuOptions.get(i);
	}

	public int readOption(final int numOptions) {
		final String chosen = io.readLine();
		if (!Validation.isNaturalNumber(chosen)) {
			if (chosen.equalsIgnoreCase(EXIT_STRING))
				return EXIT;
			if (chosen.equalsIgnoreCase(LOGOUT_STRING))
				return LOGOUT;

			return FAILURE;
		} else {
			// number validation was successful, create an int and make sure it's a valid
			// option
			final int chosenOption = Integer.parseInt(chosen);
			if (chosenOption < 1 || chosenOption > numOptions) {
				return FAILURE;
			} else
				return chosenOption;
		}
	}

	public int getMenu() {
		int option = 1;
		while (true) {
			io.println("Please choose an option (or you can choose " + EXIT_STRING + " or " + LOGOUT_STRING + ").");
			for (final BankInteraction menuOption : menuOptions) {
				io.println("\t" + option++ + ". " + menuOption.getTitle());
			}

			return readOption(menuOptions.size());
			// TODO don't ask to retry here (most likely you should just re print the menu
		}
	}

	public int readYesOrNo() {
		final String response = io.readLine().trim();

		if (response.equalsIgnoreCase("y")) {
			return YES;
		} else if (response.equalsIgnoreCase("n"))
			return NO;
		else {
			return FAILURE;
		}
	}

	public boolean retry() {
		io.println("Would you like to retry (y/n)?");
		switch (readYesOrNo()) {
		case YES:
			return true;
		case NO:
			return false;
		default:
			io.println("You didn't type y or n, so I'm taking that as a no.");
			return false;
		}
	}
}
