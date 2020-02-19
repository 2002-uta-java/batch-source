package com.revature.banking.frontend;

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
		super.addMenuOption(new WithdrawFunds(cli, uService, baService));
		super.addMenuOption(new OpenNewAccount(cli, uService, baService));
		super.addMenuOption(new AddUserToAccount(cli, uService, baService));
		super.addMenuOption(new TransferFunds(cli, uService, baService));
	}

	@Override
	public int interact() {
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
				io.clearScreen();
				continue;
			}

			io.clearScreen();
			io.println("You are now logged in");
			loginUser(user);
			break;
		}

		while (true) {
			// need to print menu for actions once logged in

			final int choice = getMenu();

			if (choice == BankInteraction.FAILURE) {
				if (!retry())
					return BankInteraction.LOGOUT;
				io.clearScreen();
				continue;
			}

			switch (choice) {
			case BankInteraction.EXIT:
				return EXIT;
			case BankInteraction.LOGOUT:
				logoutUser();
				return BankInteraction.LOGOUT;
			}

			// clear the screen for the next interaction
			io.clearScreen();

			// do the interaction
			switch (this.interact(choice)) {
			case EXIT:
				return EXIT;
			case LOGOUT:
				return LOGOUT;
			// really doesn't matter if it passed or failed, we're going to redisplay the
			// menu
			default:
				io.clearScreen();
			}
		}
	}

	private void loginUser(User user) {
		final int numInteractions = super.getNumMenuOptions();
		for (int i = 0; i < numInteractions; ++i) {
			final AccountInteraction ai = (AccountInteraction) super.getMenuOption(i);
			ai.setUser(user);
		}
	}

	private void logoutUser() {
		final int numInteractions = super.getNumMenuOptions();
		for (int i = 0; i < numInteractions; ++i) {
			final AccountInteraction ai = (AccountInteraction) super.getMenuOption(i);
			ai.removeUser();
		}
	}

}
