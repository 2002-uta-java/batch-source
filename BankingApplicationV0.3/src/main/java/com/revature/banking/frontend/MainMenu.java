package com.revature.banking.frontend;

import java.io.IOException;

public class MainMenu extends BankInteraction {
	public static final String TITLE = "Login/Create Account";

	public static final String LOGIN_OPTION_STRING = "1. Login";
	public static final String NEW_USER_OPTION_STRING = "2. Create New User";

	public static final int LOGIN_OPTION = 1;
	public static final int NEW_USER_OPTION = 2;

	public MainMenu() {
		super();
		super.setTitle(TITLE);
	}

	@Override
	public boolean hasMenu() {
		return true;
	}

	@Override
	public void printMenu() {
		io.println(LOGIN_OPTION_STRING);
		io.println(LOGIN_OPTION_STRING);
		io.println("Please choose an option (or type \"exit\" to exit program)");
	}

	@Override
	public int interact() throws IOException {
		
	}

}
