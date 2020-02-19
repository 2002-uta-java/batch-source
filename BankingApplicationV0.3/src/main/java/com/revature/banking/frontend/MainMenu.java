package com.revature.banking.frontend;

import java.io.IOException;

import com.revature.banking.services.BankAccountService;
import com.revature.banking.services.UserService;

public class MainMenu extends BankInteraction {
	public static final String TITLE = "Login/Create Account";

	public MainMenu(final CLI cli, final UserService uService, final BankAccountService baService) {
		super(cli, uService, baService);
		super.setTitle(TITLE);

		this.addMenuOption(new LoginInteraction(cli, uService, baService));
		this.addMenuOption(new CreateNewUser(cli, uService, baService));
	}

	@Override
	public int interact() throws IOException {
		io.clearScreen();
		io.println("Welcome to the BoB");
		while (true) {
			final int option = super.getMenu();

			switch (option) {
			case BankInteraction.EXIT:
				io.clearScreen();
				System.exit(0);

				// if they fail to choose an option or they choose to logout, start over with
				// the main menu
			case BankInteraction.FAILURE:
				io.clearScreen();
				io.println("Please type the number of your choice.");
				continue;
			case BankInteraction.LOGOUT:
				io.clearScreen();
				io.println("Dude, you're like, already logged out...please try again");
				continue;// if they type logout, just give them the main menu again
			}

			io.clearScreen();

			// this is the "normal" behavior, do one of the options.
			switch (super.interact(option)) {
			case BankInteraction.FAILURE:
				io.clearScreen();
				io.println("That failed, I'm taking you back to the main menu");
				continue;
			case BankInteraction.LOGOUT:
				io.clearScreen();
				io.println("You've been Logged Out.");
				continue;// continue loop
			case BankInteraction.EXIT:
				io.clearScreen();
				System.exit(0);
			default:
				// the default would be a success, in which case the interaction has finished
				// and should go back to the main menu
				io.clearScreen();
			}
		}
	}

}
