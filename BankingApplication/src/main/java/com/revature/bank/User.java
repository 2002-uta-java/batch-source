package com.revature.bank;

import com.revature.bank.exceptions.InvalidUserName;
import com.revature.bank.validation.Validation;

public class User {
	private final String username;
	private final Password password;

	private User(final String username, final Password password) {
		this.username = username;
		this.password = password;
	}

	public static User createUser(final String username, final Password password, final Validation validator)
			throws InvalidUserName {
		if (!validator.validateUserName(username))
			throw new InvalidUserName(username);

		return new User(username, password);
	}
}
