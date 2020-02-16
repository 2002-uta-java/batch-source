package com.revature.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.banking.connections.ConnectionUtil;
import com.revature.banking.models.EncryptedBankAccount;
import com.revature.banking.models.EncryptedUser;
import com.revature.banking.services.BankAccountService;

public class BankAccountDaoImpl implements BankAccountDao {

	private BankAccountService bas;

	@Override
	public EncryptedBankAccount createNewBankAccount(EncryptedUser eu) {
		// I need to get a list of bank account numbers so that I can generate a new
		// unique number. This is going to require my service layer to give me a valid
		// bank account number. So I will provide a list of encrypted bank account
		// numbers first, give that to my service layer, then my service layer will
		// generate a new (encrypted) bank account number for this new account. The
		// balance will be initially zero.

		// I then need to setup my join table to reflect that this user owns this
		// account
		final String sql = "select account_no from accounts";
		final List<String> accountNos = new LinkedList<>();
		try (final Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql);) {
			while (rs.next()) {
				accountNos.add(rs.getString("account_no"));
			}
		} catch (SQLException e) {
			Logger.getRootLogger().error(e.getStackTrace());
			return null;
		}

		final EncryptedBankAccount eba = bas.createNewAccount(accountNos);
		final String insertSQL = "insert into accounts values(?,?)";

		try (final Connection con = ConnectionUtil.getConnection();
				final PreparedStatement ps = con.prepareStatement(insertSQL)) {
			ps.setString(1, eba.getAccountNo());
			ps.setString(2, eba.getBalance());
			if (!ps.execute())
				return null;
		} catch (SQLException e) {
			Logger.getRootLogger().error(e.getStackTrace());
			return null;
		}

		// now I need to update junction table
		final String junctionSQL = "insert into user_accounts values(?,?)";

		try (final Connection con = ConnectionUtil.getConnection();
				final PreparedStatement ps = con.prepareStatement(junctionSQL)) {
			ps.setString(1, eu.getTaxId());
			ps.setString(2, eba.getAccountNo());
			if (!ps.execute()) {
				// TODO I should attempt to remove the bank account if this fails.
				return null;
			}
		} catch (SQLException e) {
			Logger.getRootLogger().error(e.getStackTrace());
		}
		// if we made it this far, things went well...return the newly created
		// BankAccount
		return eba;
	}

	@Override
	public void setBankAccountService(BankAccountService bas) {
		this.bas = bas;
	}

}
