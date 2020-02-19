package com.revature.banking.frontend;

import com.revature.banking.services.BankAccountService;
import com.revature.banking.services.UserService;
import com.revature.banking.services.models.BankAccount;
import com.revature.banking.services.models.User;

public class AddNewUserToAccount extends AccountInteraction {
	public static final String TITLE = "Add a new user to this account.";

	private BankAccount account;
	private final CreateNewUser createNewUser;

	protected AddNewUserToAccount(CLI io, UserService uService, BankAccountService baService) {
		super(io, uService, baService);
		this.setTitle(TITLE);
		this.createNewUser = new CreateNewUser(io, uService, baService);
	}

	public void setAccount(final BankAccount account) {
		this.account = account;
	}

	@Override
	public int interact() {
		io.clearScreen();
		io.println("Create a New User:");
		User newUser = null;

		newUser = createNewUser.getNewUser();

		if (newUser == null)
			return FAILURE;

		// add this user to the account
		io.working();
		if (baService.addUserToAccount(newUser.getUserKey(), account)) {
			io.done();
			io.clearScreen();
			io.println(newUser.getUserName() + " was successfully added to the following account:");
			io.println('\t' + account.printAccountBalanceHideAccountno());
			return SUCCESS;
		} else {
			io.done();
			// need to try and remove new user.
			io.clearScreen();
			io.println("There was an error trying to link " + newUser.getUserName() + " to the account");
			if (uService.deleteUser(newUser))
				io.println("We have removed " + newUser.getUserName() + " from our system.");
			return FAILURE;
		}
	}

	@Override
	public int realInteraction() {
		// this is bad code but this needs to be a AccountInteraction
		return 0;
	}

}
