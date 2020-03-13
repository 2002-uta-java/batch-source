package com.revature.banking.dao;

import com.revature.banking.models.EncryptedBankAccount;
import com.revature.banking.models.EncryptedUser;
import com.revature.banking.security.SecurityService;
import com.revature.banking.services.BankAccountService;

public interface BankAccountDao {
	public void setBankAccountService(final BankAccountService bas);

	public EncryptedBankAccount createNewBankAccount(final EncryptedUser eu);

	public void setSecurityService(final SecurityService ss);
}
