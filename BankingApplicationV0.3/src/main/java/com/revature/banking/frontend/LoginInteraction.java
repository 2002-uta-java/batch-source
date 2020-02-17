package com.revature.banking.frontend;

import java.io.IOException;

import com.revature.banking.services.BankAccountService;
import com.revature.banking.services.UserService;

public class LoginInteraction extends BankInteraction {
	public static final String TITLE = "Login";

	public LoginInteraction(final CLI cli, final UserService uService, final BankAccountService baService) {
		super(cli, uService, baService);
		super.setTitle(TITLE);
	}

	@Override
	public int interact() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

}
