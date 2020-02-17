package com.revature.banking.dao.postgres;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.banking.connections.ConnectionUtil;
import com.revature.banking.dao.BankAccountDao;
import com.revature.banking.services.security.models.EncryptedBankAccount;
import com.revature.banking.services.security.models.EncryptedUser;

public class BankAccountDaoPostgres implements BankAccountDao {

	public BankAccountDaoPostgres() {
		super();
	}

	@Override
	public Set<EncryptedBankAccount> getAllAccounts() {
		// get all accounts
		final String sql = "select * from accounts";
		final Set<EncryptedBankAccount> accounts = new HashSet<EncryptedBankAccount>();

		try (final Connection con = ConnectionUtil.getConnection();
				final Statement s = con.createStatement();
				final ResultSet rs = s.executeQuery(sql);) {

			while (rs.next()) {
				// setup account from db
				final EncryptedBankAccount eba = new EncryptedBankAccount();
				eba.setAccountkey(rs.getInt("account_key"));
				eba.setAccountNo(rs.getString("account_no"));
				eba.setBalance(rs.getString("balance"));

				accounts.add(eba);
			}

		} catch (SQLException e) {
			Logger.getRootLogger().fatal("Failed to get all bank accounts: " + e.getMessage());
			// returning null signals that this method failed
			return null;
		}

		// return set of bank accounts
		return accounts;
	}

	@Override
	public boolean addUserToAccount(EncryptedUser eUser, EncryptedBankAccount account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createNewAccount(EncryptedBankAccount eba) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeUserFromAccount(EncryptedUser eUser, EncryptedBankAccount eba) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteAccount(EncryptedBankAccount eba) {
		// TODO Auto-generated method stub
		
	}
}
