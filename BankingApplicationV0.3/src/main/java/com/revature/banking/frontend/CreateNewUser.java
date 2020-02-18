package com.revature.banking.frontend;

import java.io.IOException;

import com.revature.banking.frontend.validation.Validation;
import com.revature.banking.services.BankAccountService;
import com.revature.banking.services.UserService;
import com.revature.banking.services.models.BankAccount;
import com.revature.banking.services.models.User;
import com.revature.banking.services.security.models.EncryptedBankAccount;

public class CreateNewUser extends BankInteraction {
	public static final String TITLE = "Create new user and account.";

	public CreateNewUser(final CLI cli, final UserService uService, final BankAccountService baService) {
		super(cli, uService, baService);
		super.setTitle(TITLE);
	}

	@Override
	public int interact() throws IOException {
		final User newUser = getNewUser();

		if (newUser != null) {

			final BankAccount account = baService.createNewAccount();
			if (account != null && baService.addUserToAccount(newUser.getUserKey(), account)) {
				io.println("You have been added to the system.");
				io.println("Your new bank account number is " + account.getAccountNo());
				io.println("You should now be able to login to the system with your user name (" + newUser.getUserName()
						+ ") and password.");
				return BankInteraction.SUCCESS;
			} else {
				// attempt to delete the bank account and user
				if (account != null) {
					baService.deleteAccount(account);
				}
				// attempt to remove user
				uService.deleteUser(newUser);
			}
		}

		// it would have returned it it were successful
		io.println("There was an error and you were not added.");
		return BankInteraction.FAILURE;
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
			if (!this.retry())
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
			if (!this.retry())
				return null;
			// else try again
		}

	}

	private String getTaxId(final User user) throws IOException {
		while (true) {
			io.println("Please enter your unique, 10 digit, tax id:");
			final String taxid = io.readLine().trim();

			if (!Validation.validateTaxid(taxid)) {
				io.println(taxid + " is not a valid tax id");
				if (!retry())
					return null;
				// else try again
			} else
				return taxid;
		}

	}

	public String getName(final String firstLast) throws IOException {
		while (true) {
			io.println(firstLast + " Name:");
			final String name = io.readLine().trim();

			if (!Validation.validateName(name)) {
				if (name.length() == 0) {
					io.println("You didn't enter anything");
					if (!this.retry())
						return null;
				} else {
					io.println("You typed: " + name);
					io.println("This name is too long for our system. Do you go by another, shorter name?");
					if (!this.retry())
						return null;
				}
				// if they chose to retry loop again, try again
			}
			// else return their name
			return name;
		}
	}

	protected User getNewUser() throws IOException {

		final String firstName = getName("First");
		if (firstName == null)
			return null;

		final String lastName = getName("Last");
		if (lastName == null)
			return null;
		final User newUser = new User();
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);

		String taxId = getTaxId(newUser);
		if (taxId == null)
			return null;

		newUser.setTaxID(taxId);

		// this user has successfully given their first and last name and a "valid" tax
		// id. This user is ready to be added to the database and an account created.

		// get a username for this person
		String username = getUserName();
		if (username == null)
			return null;

		newUser.setUserName(username);

		int checkUserNameTaxIdStatus;

		while (true) {
			// check tax id and username against what's in the database
			checkUserNameTaxIdStatus = uService.checkNewUserTaxidAndUserName(newUser);

			if (checkUserNameTaxIdStatus == UserService.CHECK_NEW_USER_HAS_OPEN_ACCOUNT) {
				io.println("You already have an account." + " If you need help logging in please call"
						+ " our customer support line at 1-800-BAD-PHONE.");
				return null;
			}

			if (checkUserNameTaxIdStatus == UserService.CHECK_NEW_USER_INVALID_TAX_ID) {
				io.println("The tax id and name you provided does not match" + " what we have on file. "
						+ "Did you enter the wrong tax id?");
				if (!super.retry())
					return null;

				// try to get a new tax id
				taxId = getTaxId(newUser);
				if (taxId == null)
					return null;
				// else reset taxid of newUser
				newUser.setTaxID(taxId);
				// loop and check again
				continue;
			}

			if (checkUserNameTaxIdStatus == UserService.CHECK_NEW_USER_INVALID_USERNAME_EXISTS) {
				io.println("This username already exists.");
				if (!super.retry())
					return null;
				// else set username and try again
				username = getUserName();
				if (username == null)
					return null;
				// else reset newUser username and check again
				newUser.setUserName(username);
				continue;
			}

			// if we got this far, (without returning or continuing) the taxid and username
			// check passed so break out of loop
			break;
		}

		final String password = getPassword();
		if (password == null)
			return null;

		newUser.setPassword(password);

		BankAccount account = null;
		if (checkUserNameTaxIdStatus == UserService.CHECK_NEW_USER_VALID_ALREADY_EXISTS) {
			if (uService.updateUser(newUser))
				return newUser;
			else
				return null;

		} else {
			if (uService.createUser(newUser))
				return newUser;
			else
				return null;
		}

//	{
//		if (uService.createNewUserAndAccount(newUser)) {
//			return newUser;
//		}

//		switch (checkUserNameTaxIdStatus) {
//		case UserService.CHECK_NEW_USER_BRAND_NEW:
//			account = uService.createNewUserAndAccount(newUser);
//			break;
//		case UserService.CHECK_NEW_USER_VALID_ALREADY_EXISTS:
//			account = uService.updateUserCreateNewAccount(newUser);
//		}
	}
}
