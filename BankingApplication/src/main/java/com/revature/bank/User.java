package com.revature.bank;

import java.io.Serializable;

import com.revature.bank.exceptions.InvalidUserNameException;
import com.revature.bank.validation.Validation;

public class User implements Serializable {
	/**
	 * generated by eclipse
	 */
	private static final long serialVersionUID = 2756811436947126182L;

	private final String username;
	private final Password password;
	final Person person;

	private User(final Person person, final String username, final Password password) {
		this.username = username;
		this.password = password;
		this.person = person;
	}

	public static User createUser(final Person person, final String username, final Password password,
			final Validation validator) throws InvalidUserNameException {
		if (!validator.validateUserName(username))
			throw new InvalidUserNameException(username);

		return new User(person, username, password);
	}
}
