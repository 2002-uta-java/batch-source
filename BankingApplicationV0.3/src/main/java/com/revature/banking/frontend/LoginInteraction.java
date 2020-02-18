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
			// need to print menu for actions once logged in
		}
	}

}
