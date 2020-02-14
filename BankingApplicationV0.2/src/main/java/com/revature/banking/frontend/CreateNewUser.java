package com.revature.banking.frontend;

import java.io.IOException;
import java.io.ObjectInputStream.GetField;

import com.revature.banking.validation.Validation;

public class CreateNewUser implements BankInteraction {
	public static final String TITLE = "Create new user and account.";

	@Override
	public boolean interact(final CLI io) throws IOException {
		final String firstName = getName("First", io);
		if (firstName == null)
			return false;

		final String lastName = getName("Last", io);
		if (lastName == null)
			return false;

		final String taxId = getTaxId(io);

		// TODO (let's hope not)
		return false;
	}

	private String getTaxId(CLI io) throws IOException {
		while (true) {
			io.println("Please enter your unique, 10 digit, tax id:");
			final String taxid = io.readLine().trim();
			
			if(!Validation.validateTaxid(taxid)) {
				io.println(taxid + " is not a valid tax id");
				if(!retry(io))
					return null;
				// else try again
			}else if()//TODO
		}
	}

	@Override
	public String getTitle() {
		return TITLE;
	}

	private String getName(final String firstLast, final CLI io) throws IOException {
		while (true) {
			io.println(firstLast + " Name:");
			final String name = io.readLine().trim();

			if (!Validation.validateName(name)) {
				if (name.length() == 0) {
					io.println("You didn't enter anything");
					if (!this.retry(io))
						return null;
				} else {
					io.println("You typed: " + name);
					io.println("This name is too long for our system. Do you go by another, shorter name?");
					if (!this.retry(io))
						return null;
				}
				// if they chose to retry loop again, try again
			}
			// else return their name
			return name;
		}
	}
}
