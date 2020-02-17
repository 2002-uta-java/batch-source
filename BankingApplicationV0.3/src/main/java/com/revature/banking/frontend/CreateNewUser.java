package com.revature.banking.frontend;

import java.io.IOException;

import com.revature.banking.frontend.validation.Validation;
import com.revature.banking.services.UserService;
import com.revature.banking.services.models.BankAccount;
import com.revature.banking.services.models.User;

public class CreateNewUser extends BankInteraction {
	public static final String TITLE = "Create new user and account.";

	public CreateNewUser() {
		super();
		super.setTitle(TITLE);
	}

	@Override
	public int interact() throws IOException {
		final String firstName = getName("First");
		if (firstName == null)
			return BankInteraction.FAILURE;

		final String lastName = getName("Last");
		if (lastName == null)
			return BankInteraction.FAILURE;
		final User newUser = new User();
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);

		final int taxIdStatus = getTaxId(newUser);
		if (taxIdStatus != UserService.CHECK_NEW_USER_BRAND_NEW
				&& taxIdStatus != UserService.CHECK_NEW_USER_VALID_ALREADY_EXISTS)
			return BankInteraction.FAILURE;

		// this user has successfully given their first and last name and a "valid" tax
		// id. This user is ready to be added to the database and an account created.

		final String username = getUserName();
		if (username == null)
			return BankInteraction.FAILURE;

		newUser.setUserName(username);

		final String password = getPassword();
		if (password == null)
			return BankInteraction.FAILURE;

		newUser.setPassword(password);

		BankAccount account = null;
		switch (taxIdStatus) {
		case UserService.CHECK_NEW_USER_BRAND_NEW:
			account = uService.createNewUser(newUser);
			break;
		case UserService.CHECK_NEW_USER_VALID_ALREADY_EXISTS:
			account = uService.updateUserCreateNewAccount(newUser);
		}
		if (account != null) {
			io.println("You have been added to the system.");
			io.println("Your new bank account number is " + account.getAccountNo());
			io.println("You should now be able to login to the system with your user name (" + newUser.getUserName()
					+ ") and password.");
			return BankInteraction.SUCCESS;
		} else {
			io.println("There was an error and you were not added.");
			return BankInteraction.FAILURE;
		}
	}

	private String getUserName() throws IOException {
		while (true) {
			io.println("Please provide a username: ");
			final String username = io.readLine();

			if (Validation.validateName(username)) {
				final int length = username.length();
				if (length >= UserService.USER_NAME_MIN_LENGTH && length <= UserService.USER_NAME_MAX_LENGTH)
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

	private String getPassword() throws IOException {
		while (true) {
			io.println("Please provide a password: ");
			final String password = io.readPassword();

			if (Validation.validatePassword(password)) {
				final int length = password.length();
				if (length >= UserService.PASSWORD_MIN_LENGTH && length <= UserService.PASSWORD_MAX_LENGTH)
					return password;
				else
					io.println("Your password should be " + UserService.PASSWORD_MIN_LENGTH + " to "
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

	private int getTaxId(final User user) throws IOException {
		while (true) {
			io.println("Please enter your unique, 10 digit, tax id:");
			final String taxid = io.readLine().trim();

			if (!Validation.validateTaxid(taxid)) {
				io.println(taxid + " is not a valid tax id");
				if (!retry(io))
					return UserService.CHECK_NEW_USER_INVALID_TAX_ID;
				// else try again
			} else {
				final String newTaxId = taxid;
				user.setTaxID(newTaxId);
				final int newUserCheck = uService.checkNewUserTaxid(user);
				switch (newUserCheck) {
				// it's necessary to distinguish these two cases because if the user is already
				// in the system, the checkNewUserTaxId will set the User's user_key so that
				// when creating the login (user name and password) we can directly update the
				// row instead of having to redo the search for tax id (or worse, creating a new
				// record with a duplicate tax id).
				case UserService.CHECK_NEW_USER_BRAND_NEW:
					return UserService.CHECK_NEW_USER_BRAND_NEW;
				case UserService.CHECK_NEW_USER_VALID_ALREADY_EXISTS:
					return UserService.CHECK_NEW_USER_VALID_ALREADY_EXISTS;

				case UserService.CHECK_NEW_USER_HAS_OPEN_ACCOUNT:
					io.println("You already have an account." + " If you need help logging in please call"
							+ " our customer support line at 1-800-BAD-PHONE.");
					return UserService.CHECK_NEW_USER_HAS_OPEN_ACCOUNT;
				case UserService.CHECK_NEW_USER_INVALID_TAX_ID:
					io.println("The tax id and name you provided does not match" + " what we have on file. "
							+ "Did you enter the wrong tax id?");
					break;
				default:
					throw new RuntimeException("UserService returned an unrecognized value.");
				}
				// if the above switch statement didn't return anything, then we had a tax id
				// mismatch. Ask the user to try again (if they wish) or just return that status
				// code.
				if (!this.retry(io))
					return UserService.CHECK_NEW_USER_INVALID_TAX_ID;
				// else continue
			}
		}

	}

	public String getName(final String firstLast) throws IOException {
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
