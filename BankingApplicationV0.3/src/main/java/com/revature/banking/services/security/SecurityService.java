package com.revature.banking.services.security;

import org.apache.log4j.Logger;

import org.jasypt.util.password.PasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;

import com.revature.banking.services.models.BankAccount;
import com.revature.banking.services.models.User;
import com.revature.banking.services.security.models.EncryptedBankAccount;
import com.revature.banking.services.security.models.EncryptedUser;

public class SecurityService {
	private final StrongTextEncryptor ste = new StrongTextEncryptor();
	private final PasswordEncryptor pe = new StrongPasswordEncryptor();

	public void setKey(final String key) {
		Logger.getRootLogger().debug("Setting key for text encryption to: " + key);
		this.ste.setPassword(key);
	}

	public String decrypt(final String string) {
		return ste.decrypt(string);
	}

	public String encrypt(final String string) {
		return ste.encrypt(string);
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

	/**
	 * This decrypts the user returned from the database and transforms it to an
	 * (unencrypted) User object. Everything in the database is encrypted except the
	 * user_key and username.
	 * 
	 * Note: the password field for the User object will not be set because it's not
	 * possible for it to be set from the information in the database.
	 * 
	 * @param eUser The EcnryptedUser object retrieved from the database.
	 * @return A User object containing plain text fields containing the user's
	 *         first name, last name, tax id, and user name.
	 */
	public User decrypt(EncryptedUser eUser) {
		final User user = new User();
		user.setUserKey(eUser.getUserKey());
		user.setFirstName(this.decrypt(eUser.getFirstName()));
		user.setLastName(this.decrypt(eUser.getLastName()));
		user.setTaxID(this.decrypt(eUser.getTaxId()));
		user.setUserName(eUser.getUserName());

		return user;
	}

	/**
	 * This encrypts the User object into an EncryptedUser object that can be added
	 * to the database. Everything is encrypted except the user_key and username
	 * fields.
	 * 
	 * @param user Plain text User object.
	 * @return An EncryptedUser object suitable for adding to the database.
	 */
	public EncryptedUser encrypt(final User user) {
		final EncryptedUser eUser = new EncryptedUser();
		eUser.setUserKey(user.getUserKey());
		eUser.setFirstName(this.encrypt(user.getFirstName()));
		eUser.setLastName(this.encrypt(user.getLastName()));
		eUser.setTaxId(this.encrypt(user.getTaxID()));
		eUser.setUserName(user.getUserName());
		eUser.setPassword(pe.encryptPassword(user.getPassword()));

		return eUser;
	}

	/**
	 * This decrypts the encrypted bank account information into a plain text
	 * BankAccount object. Everything is encrypted except the account_key.
	 * 
	 * @param eba An {@link EncryptedBankAccount} object from the database to be
	 *            decrypted.
	 * @return A plain text {@link BankAccount} object.
	 */
	public BankAccount decrypt(final EncryptedBankAccount eba) {
		final BankAccount account = new BankAccount();
		account.setAccountKey(eba.getAccountkey());
		account.setAccountNo(this.decrypt(eba.getAccountNo()));
		account.setBalance(Double.parseDouble(this.decrypt(eba.getBalance())));

		return account;
	}

	/**
	 * This takes a plain text {@link BankAccount} object and encrypts it to an
	 * {@link EncryptedBankAccount} object.
	 * 
	 * Note: the given {@link BankAccount} should have a valid account_key set so
	 * that the database can find the correct record to update.
	 * 
	 * @param account A plain text bank account object.
	 * @return An encrypted bank account object.
	 */
	public EncryptedBankAccount encrypt(final BankAccount account) {
		final EncryptedBankAccount eba = new EncryptedBankAccount();
		eba.setAccountkey(account.getAccountKey());
		eba.setAccountNo(this.encrypt(account.getAccountNo()));
		eba.setBalance(this.encrypt("" + account.getBalance()));

		return eba;
	}
}
