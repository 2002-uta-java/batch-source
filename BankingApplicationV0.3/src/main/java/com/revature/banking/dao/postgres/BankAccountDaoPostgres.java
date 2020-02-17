package com.revature.banking.dao.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	public boolean addUserToAccount(EncryptedUser eUser, EncryptedBankAccount eba) {
		final String sql = "insert into user_accounts values(?,?)";

		try (final Connection con = ConnectionUtil.getConnection();
				final PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, eUser.getUserKey());
			ps.setInt(2, eba.getAccountkey());

			final int updated = ps.executeUpdate();

			if (updated != 1) {
				Logger.getRootLogger()
						.error("Adding user (user_key = " + eUser.getUserKey() + ") to account (account_key = "
								+ eba.getAccountkey() + ") updated " + updated + " rows (should have been 1)");
				return false;
			}

		} catch (SQLException e) {
			Logger.getRootLogger()
					.fatal("Failed to add user, " + eUser + ", to account " + eba + ": " + e.getMessage());
			return false;
		}

		// if we made it this far, we succeeded
		return true;
	}

	@Override
	public boolean createNewAccount(EncryptedBankAccount eba) {
		final String sql = "insert into accounts (account_no, balance) values (?, ?)";
		ResultSet rs = null;
		try (final Connection con = ConnectionUtil.getConnection();
				final PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, eba.getAccountNo());
			ps.setString(2, eba.getBalance());

			final int updated = ps.executeUpdate();

			if (updated != 1) {
				Logger.getRootLogger().error("Create New bank account updated " + updated + " rows (should be 1)");
				return false;
			}
			rs = ps.getGeneratedKeys();

			while (rs.next()) {
				eba.setAccountkey(rs.getInt("account_key"));
			}
		} catch (SQLException e) {
			Logger.getRootLogger().fatal("Failed to create new account " + eba + ": " + e.getMessage());
			return false;
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					Logger.getRootLogger().error("Failed to close rs: " + e.getMessage());
				}
		}

		// if we made it this far we successfully added the new account
		return true;
	}

	@Override
	public boolean removeUserFromAccount(EncryptedUser eUser, EncryptedBankAccount eba) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAccount(EncryptedBankAccount eba) {
		final String sql = "delete from accounts where account_key = ?";

		try (final Connection con = ConnectionUtil.getConnection();
				final PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, eba.getAccountkey());
			final int updated = ps.executeUpdate();

			if (updated != 1) {
				Logger.getRootLogger().error("Delete Account, account_key = " + eba.getAccountkey() + ", deleted "
						+ updated + " rows (should have been 1)");
				return false;
			}
		} catch (SQLException e) {
			Logger.getRootLogger().error(
					"Failed to delete account with account_key = " + eba.getAccountkey() + ": " + e.getMessage());
		}
		return true;
	}
}
