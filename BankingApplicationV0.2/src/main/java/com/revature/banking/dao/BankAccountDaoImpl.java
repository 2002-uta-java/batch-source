package com.revature.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.banking.connections.ConnectionUtil;
import com.revature.banking.models.BankAccount;
import com.revature.banking.models.EncryptedBankAccount;
import com.revature.banking.models.EncryptedUser;
import com.revature.banking.security.SecurityService;
import com.revature.banking.services.BankAccountService;

public class BankAccountDaoImpl implements BankAccountDao {

	private BankAccountService bas;
	private SecurityService ss;

	@Override
	public BankAccount createNewBankAccount(EncryptedUser eu) {
		// I need to get a list of bank account numbers so that I can generate a new
		// unique number. This is going to require my service layer to give me a valid
		// bank account number. So I will provide a list of encrypted bank account
		// numbers first, give that to my service layer, then my service layer will
		// generate a new (encrypted) bank account number for this new account. The
		// balance will be initially zero.

		// I then need to setup my join table to reflect that this user owns this
		// account
		final String sql = "select account_no from accounts";
		final Set<String> accountNos = new HashSet<>();
		try (final Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql);) {
			while (rs.next()) {
				accountNos.add(ss.decrypt(rs.getString("account_no")));
			}
		} catch (SQLException e) {
			Logger.getRootLogger().error(e.getMessage());
			return null;
		}

		final BankAccount newAccount = bas.createNewAccount(accountNos);
		final EncryptedBankAccount eba = ss.encrypt(newAccount);
		final String insertSQL = "insert into accounts (account_no, balance) values(?,?)";

		try (final Connection con = ConnectionUtil.getConnection();
				final PreparedStatement ps = con.prepareStatement(insertSQL)) {
			ps.setString(1, eba.getAccountNo());
			ps.setString(2, eba.getBalance());
			ps.execute();
		} catch (SQLException e) {
			Logger.getRootLogger().error(e.getMessage());
			return null;
		}

		// now I need to update junction table
		final String junctionSQL = "insert into user_accounts values(?,?)";

		try (final Connection con = ConnectionUtil.getConnection();
				final PreparedStatement ps = con.prepareStatement(junctionSQL)) {
			ps.setString(1, eu.getTaxId());
			ps.setString(2, newAccount.getAccountNo());
			ps.execute();
		} catch (SQLException e) {
			Logger.getRootLogger().error(e.getMessage());
		}
		// if we made it this far, things went well...return the newly created
		// BankAccount
		return newAccount;
	}

	@Override
	public void setBankAccountService(BankAccountService bas) {
		this.bas = bas;
	}

	@Override
	public void setSecurityService(SecurityService ss) {
		this.ss = ss;
	}

}
