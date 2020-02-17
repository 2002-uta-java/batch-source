package com.revature.banking.frontend;

import java.io.IOException;

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
	 * this
	 */
	public static final int SUCCESS = 0;
	public static final int LOGOUT = 1;
	public static final int EXIT = 2;
	public static final int FAILURE = 3;

	protected CLI io = null;
	protected BankAccountService baService = null;
	protected UserService uService = null;
	private String title;

	protected BankInteraction() {
		super();
	}

	/**
	 * 
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	protected void setTitle(final String title) {
		this.title = title;
	}

	public abstract int interact() throws IOException;

	public void setCLI(final CLI io) {
		this.io = io;
	}

	public void setBankAccountService(final BankAccountService baService) {
		this.baService = baService;
	}

	public void setUserService(final UserService uService) {
		this.uService = uService;
	}

	/**
	 * Returns whether or not this interaction has a menu. The default is that it
	 * does not.
	 * 
	 * @return Whether or not this interaction has a menu.
	 */
	public boolean hasMenu() {
		return false;
	}

	/**
	 * Prints the menu for this interaction. The default is that it prints nothing.
	 */
	public void printMenu() {
		// does nothing by default
	}

	public boolean retry(final CLI io) throws IOException {
		io.println("You you like to retry (y/n)?");
		final String response = io.readLine().trim();

		if (response.equalsIgnoreCase("y"))
			return true;
		else if (response.equalsIgnoreCase("n"))
			return false;
		else {
			io.println("You didn't type y or n, so I'm taking that as a no.");
			return false;
		}
	}
}
