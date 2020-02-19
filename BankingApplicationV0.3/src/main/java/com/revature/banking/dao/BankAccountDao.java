package com.revature.banking.dao;

import java.util.List;
import java.util.Set;

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
	public static final int BANK_ACCOUNT_NO_LENGTH = 10;
	public static final double MAX_DEPOSIT = 10000;

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
	 * @param eba Bank account to be added. This method should set eba's
	 *            account_key.
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
	 * Adds a user to an account.
	 * 
	 * @param eUser User to add an account to. The passed user <i>must</i> contain a
	 *              valid user_key.
	 * @param eba   The bank account to link to this user. The passed account
	 *              <i>must</i> contain a valid account_id.
	 */
	public boolean addUserToAccount(final int userKey, final EncryptedBankAccount eba);

	/**
	 * Removes this user from an account.
	 * 
	 * @param eUser User to be removed (must have user_key set).
	 * @param eba   Bank account to be remove from (must have account_key set).
	 * @return whether or not this action was successful.
	 */
	public boolean removeUserFromAccount(final EncryptedUser eUser, final EncryptedBankAccount eba);

	/**
	 * Deletes this bank account from the database. There are no checks to make sure
	 * that this account isn't linked to someone else (or does any de-linking
	 * whatsoever).
	 * 
	 * @param eba Bank account to be deleted. This must include, at least the
	 *            account_key.
	 * @returns Whether or not this delete was successful.
	 */
	public boolean deleteAccount(final EncryptedBankAccount eba);

	/**
	 * Returns the accounts associated with this user account based on the user's
	 * key.
	 * 
	 * @param userKey user_key from the User stored in the database.
	 * @return A list of encrypted bank accounts from database.
	 */
	public List<EncryptedBankAccount> getAccountsByUserKey(int userKey);

	/**
	 * Updates the account given.
	 * 
	 * @param eba The account to updated (the balance and account_no will be
	 *            overrided). It is assumed that the account_no does not actually
	 *            change, only the encrypted value.
	 * @return Whether or not this operation was successful.
	 */
	public boolean updateAccount(EncryptedBankAccount eba);

}
