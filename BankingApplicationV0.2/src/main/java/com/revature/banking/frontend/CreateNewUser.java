package com.revature.banking.frontend;

import java.io.IOException;
import java.io.ObjectInputStream.GetField;

import com.revature.banking.models.Password;
import com.revature.banking.models.RawPassword;
import com.revature.banking.models.TaxID;
import com.revature.banking.models.User;
import com.revature.banking.services.BankAccountService;
import com.revature.banking.services.UserService;
import com.revature.banking.validation.Validation;

public class CreateNewUser implements BankInteraction {
	public static final String TITLE = "Create new user and account.";

	@Override
	public void interact(final CLI io, final BankAccountService bs, final UserService us) throws IOException {
		final String firstName = getName("First", io);
		if (firstName == null)
			return;

		final String lastName = getName("Last", io);
		if (lastName == null)
			return;

		final User newUser = getTaxId(io, us, firstName, lastName);
		if (newUser == null)
			return;

		// this user has successfully given their first and last name and a "valid" tax
		// id. This user is ready to be added to the database and an account created.

		final String username = getUserName(io);
		if (username == null)
			return;

		newUser.setUsername(username);

		final Password password = getPassword(io);
		if (password == null)
			return;

		newUser.setPassword(password);

		if (us.createNewUser(newUser)) {
			io.println("You have been added to the system. You should be able to login now.");
		} else {
			io.println("There was an error and you were not added.");
		}
	}

	private String getUserName(CLI io) throws IOException {
		while (true) {
			io.println("Please provide a username: ");
			final String username = io.readLine();

			if (Validation.validateName(username)) {
				final int length = username.length();
				if (length >= UserService.USER_NAME_MIN_LENGTH && length <= UserService.USER_NAME_MIN_LENGTH)
					return username;
				else
					io.println("Your user name should be " + UserService.USER_NAME_MIN_LENGTH + " to "
							+ UserService.USER_NAME_MAX_LENGTH + " characters long.");
			} else {
				io.println("That's in invalid user name. User names must only contain characters and underscores.");
			}
			if (!this.retry(io))
				return null;
			// else try again
		}

	}

	private Password getPassword(CLI io) throws IOException {
		while (true) {
			io.println("Please provide a username: ");
			final String password = io.readPassword();

			if (Validation.validateName(password)) {
				final int length = password.length();
				if (length >= UserService.PASSWORD_MIN_LENGTH && length <= UserService.PASSWORD_MIN_LENGTH)
					return new RawPassword(password);// TODO need to think about how to modularize this
				else
					io.println("Your user name should be " + UserService.PASSWORD_MIN_LENGTH + " to "
							+ UserService.PASSWORD_MAX_LENGTH + " characters long.");
			} else {
				io.println(
						"That's in invalid password. Passwords should contain at least one of each of: lower case letters, upper case letters, numbers, and special characters ("
								+ Validation.PASSWORD_SPECIAL + ")");
			}
			if (!this.retry(io))
				return null;
			// else try again
		}

	}

	private User getTaxId(final CLI io, final UserService us, final String firstName, final String lastName)
			throws IOException {
		while (true) {
			io.println("Please enter your unique, 10 digit, tax id:");
			final String taxid = io.readLine().trim();

			if (!Validation.validateTaxid(taxid)) {
				io.println(taxid + " is not a valid tax id");
				if (!retry(io))
					return null;
				// else try again
			} else {
				final TaxID newTaxId = new TaxID(taxid);
				final User user = new User();
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setTaxId(newTaxId);
				final int newUserCheck = us.checkNewUserTaxid(user);
				if (newUserCheck == UserService.CHECK_NEW_USER_PASS)
					return user;
				if (newUserCheck == UserService.CHECK_NEW_USER_ACCOUNT_EXISTS) {
					io.println(
							"You already have an account. If you need help logging in please call our customer support line at 1-800-BAD-PHONE.");
					return null;
				}
				if (newUserCheck == UserService.CHECK_NEW_USER_TAXID_MISMATCH) {
					io.println("The tax id and name you provided does not match. Did you enter the wrong tax id?");
					if (!this.retry(io))
						return null;
					// else continue
				}
			}
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
