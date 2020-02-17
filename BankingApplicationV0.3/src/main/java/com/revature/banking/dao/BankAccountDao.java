package com.revature.banking.dao;

import java.util.Set;

import com.revature.banking.services.BankAccountService;
import com.revature.banking.services.security.models.EncryptedBankAccount;
import com.revature.banking.services.security.models.EncryptedUser;

/**
 * Interface for interacting with data. This is intended to be used by the
 * service layer.
 * 
 * @author Jared F Bennatt
 *
 */
public interface BankAccountDao {
	/**
	 * Set the BankAccountService used by this DAO (this is necessary because the
	 * service layer determines new bank account numbers).
	 * 
	 * @param baService
	 */
//	public void setBankAccountService(final BankAccountService baService);

	/**
	 * This adds the passed bank account to the database.
	 * 
	 * @param eba Bank account to be added.
	 * @return Whether or not this action was successful.
	 */
	public boolean createNewAccount(final EncryptedBankAccount eba);

	/**
	 * Gets all of the accounts in the database.
	 * 
	 * @return A {@link Set} of {@link EncryptedBankAccount} objects representing
	 *         all present in the database.
	 */
	public Set<EncryptedBankAccount> getAllAccounts();

	/**
	 * This essentially adds an existing account to a user.
	 * 
	 * @param eUser   User to add an account to. The passed user <i>must</i> contain
	 *                a valid user_key.
	 * @param account The bank account to link to this user. The passed account
	 *                <i>must</i> contain a valid account_id.
	 */
	public boolean linkUserAndAccount(final EncryptedUser eUser, final EncryptedBankAccount account);

}
