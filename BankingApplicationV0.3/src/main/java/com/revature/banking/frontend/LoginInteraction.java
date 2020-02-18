package com.revature.banking.frontend;

import java.io.IOException;

import com.revature.banking.services.BankAccountService;
import com.revature.banking.services.UserService;
import com.revature.banking.services.models.User;

public class LoginInteraction extends BankInteraction {
	public static final String TITLE = "Login";

	public LoginInteraction(final CLI cli, final UserService uService, final BankAccountService baService) {
		super(cli, uService, baService);
		super.setTitle(TITLE);
		super.addMenuOption(new ViewAccountBalance(cli, uService, baService));
		super.addMenuOption(new AddFunds(cli, uService, baService));
	}

	@Override
	public int interact() throws IOException {
		while (true) {
			io.println("Username: ");
			final String username = io.readLine();
			io.println("Password: ");
			final String password = io.readPassword();

			final User user = uService.login(username, password);
			if (user == null) {
				io.println("username/password pair is invalid.");
				if (!super.retry())
					return BankInteraction.FAILURE;
			}

			io.println("You are now logged in");
			loginUser(user);
			break;
		}

		while (true) {
			// need to print menu for actions once logged in

			final int choice = getMenu();

			switch (choice) {
			case BankInteraction.EXIT:
				return BankInteraction.EXIT;
			case BankInteraction.LOGOUT:
				logoutUser();
				return BankInteraction.LOGOUT;
			case BankInteraction.FAILURE:
				io.println("You've been logged out.");
				logoutUser();
				return BankInteraction.FAILURE;
			default:
				this.interact(choice);
			}

			switch (this.interact()) {
			case BankInteraction.EXIT:
				return BankInteraction.EXIT;
			case BankInteraction.LOGOUT:
				logoutUser();
				return BankInteraction.LOGOUT;
			// default is to just return to menu above
			}
		}
	}

	private void loginUser(User user) {
		final int numInteractions = super.getNumMenuOptions();
		for (int i = 0; i < numInteractions; ++i) {
			final AccountInteraction ai = (AccountInteraction) super.getInteraction(i);
			ai.setUser(user);
		}
	}

	private void logoutUser() {
		final int numInteractions = super.getNumMenuOptions();
		for (int i = 0; i < numInteractions; ++i) {
			final AccountInteraction ai = (AccountInteraction) super.getInteraction(i);
			ai.removeUser();
		}
	}

}
