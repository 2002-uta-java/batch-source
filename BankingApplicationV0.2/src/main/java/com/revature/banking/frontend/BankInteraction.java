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
public interface BankInteraction {

	/**
	 * 
	 * @return
	 */
	public String getTitle();

	public boolean interact(final CLI io, final BankAccountService bs, final UserService us) throws IOException;

	public default boolean retry(final CLI io) throws IOException {
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
