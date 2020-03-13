package com.revature.banking.security;

import org.apache.log4j.Logger;
import org.jasypt.util.password.PasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;

import com.revature.banking.models.BankAccount;
import com.revature.banking.models.EncryptedBankAccount;
import com.revature.banking.models.EncryptedUser;
import com.revature.banking.models.User;

public class SecurityService {
	private final StrongTextEncryptor ste = new StrongTextEncryptor();
	private final PasswordEncryptor pe = new StrongPasswordEncryptor();

	public void setKey(final String key) {
		Logger.getRootLogger().debug("Setting key for text encryption to: " + key);
		this.ste.setPassword(key);
	}

	public User decryptUser(EncryptedUser eu) {
		if (eu == null)
			return null;
		// else decrypt user

		// everything gets decrypted except rowKey, username, and password (password is
		// a hash so it doesn't need to be "extra encrypted")
		final User user = new User();
		user.setFirstName(this.decrypt(eu.getFirstName()));
		user.setLastName(this.decrypt(eu.getLastName()));
		user.setTaxId(this.decrypt(eu.getTaxId()));

		if (eu.getUsername() != null) {
			// if the user name exists, the password should also
			user.setUsername(eu.getUsername());
			user.setPassword(eu.getPassword());
		}

		return user;
	}

	public EncryptedBankAccount encrypt(final BankAccount ba) {
		// everything gets encrypted except rowKey
		final EncryptedBankAccount eba = new EncryptedBankAccount();
		eba.setRowKey(ba.getRowKey());
		eba.setAccountNo(this.encrypt(ba.getAccountNo()));
		eba.setBalance(this.encrypt("" + ba.getBalance()));
		return eba;
	}

	public BankAccount decrypt(final EncryptedBankAccount eba) {
		if (eba == null)
			return null;

		// everything gets decrypted except rowKey
		final BankAccount ba = new BankAccount();
		ba.setAccountNo(this.decrypt(eba.getAccountNo()));
		ba.setBalance(Double.parseDouble(this.decrypt(eba.getBalance())));
		ba.setRowKey(eba.getRowKey());
		return ba;
	}

	public EncryptedUser encryptNewUser(User newUser) {
		// everything gets encrypted except rowKey and username
		final EncryptedUser eu = new EncryptedUser();
		eu.setRowKey(newUser.getRowKey());
		eu.setFirstName(this.encrypt(newUser.getFirstName()));
		eu.setLastName(this.encrypt(newUser.getLastName()));
		eu.setTaxId(this.encrypt(newUser.getTaxId()));
		eu.setUsername(newUser.getUsername());

		// when adding a new user, the password will be their plain text password. This
		// needs to be securely hashed.
		eu.setPassword(pe.encryptPassword(newUser.getPassword()));

		return eu;
	}

	public String decrypt(final String encrypted) {
		Logger.getRootLogger().debug("Decrypting string \"" + encrypted + "\" to \"" + ste.decrypt(encrypted) + "\"");
		return ste.decrypt(encrypted);
	}

	public String encrypt(final String message) {
		Logger.getRootLogger()
				.debug("Encryping Message: " + message + " to " + ste.encrypt(message) + " with " + ste.toString());
		return ste.encrypt(message);
	}

	/**
	 * Validates a password (first argument) with the value held in the database
	 * (second value).
	 * 
	 * @param password plain text password
	 * @param dbValue  hidden value in db.
	 * @return true if password's match, false otherwise.
	 */
	public boolean checkPassword(final String password, final String dbValue) {
		return pe.checkPassword(password, dbValue);
	}

}
