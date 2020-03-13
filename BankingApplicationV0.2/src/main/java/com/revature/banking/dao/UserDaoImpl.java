package com.revature.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.revature.banking.connections.ConnectionUtil;
import com.revature.banking.models.BankAccount;
import com.revature.banking.models.EncryptedBankAccount;
import com.revature.banking.models.EncryptedUser;
import com.revature.banking.models.User;
import com.revature.banking.security.SecurityService;

public class UserDaoImpl implements UserDao {

	private BankAccountDao bad;
	private SecurityService ss;

	@Override
	public User getUserByTaxId(String taxId) {
		// the tax id's are encrypted so there's no way to directly search the database
		// to find a tax id. Instead I'm going to have to pull the entire list of users
		// and do the search here.
		final String sql = "select * from users";

		try (final Connection con = ConnectionUtil.getConnection();
				final Statement ps = con.createStatement();
				final ResultSet rs = ps.executeQuery(sql)) {
			while (rs.next()) {
				final String encryptedTaxId = rs.getString("tax_id");

				if (ss.decrypt(encryptedTaxId).equals(taxId)) {
					Logger.getRootLogger().debug("Found a matching tax id in db, creating user object");

					// create an EncryptedUser object, then have the security service decrypt it.
					final EncryptedUser eu = new EncryptedUser();
					eu.setRowKey(rs.getInt("user_key"));
					eu.setFirstName(rs.getString("firstname"));
					eu.setLastName(rs.getString("lastname"));
					eu.setTaxId(rs.getString("tax_id"));
					eu.setUsername(rs.getString("username"));
					eu.setPassword(rs.getString("password"));
					Logger.getRootLogger().debug("Retrieved encrypted user: " + eu);

					// return the decrypted user
					return ss.decryptUser(eu);
				}
			}
		} catch (SQLException e) {
			Logger.getRootLogger().error(e + e.getMessage());
			return null;
		}

		// if we reach this point there was no matching tax id in the db
		return null;
	}

	@Override
	public BankAccount createNewUser(EncryptedUser user) {
		final String sql = "insert into users (tax_id, firstname, lastname, username, password) values(?,?,?,?,?)";
		BankAccount newAccount = null;
		try (final Connection con = ConnectionUtil.getConnection();
				final PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, user.getTaxId());
			ps.setString(2, user.getFirstName());
			ps.setString(3, user.getLastName());
			ps.setString(4, user.getUsername());
			ps.setString(5, user.getPassword());

			ps.execute();
			eba = bad.createNewBankAccount(user);
		} catch (SQLException e) {
			Logger.getRootLogger().error(e.getStackTrace());
			return null;
		}

		return newAccount;
	}

	@Override
	public void setBankAccountDao(BankAccountDao bad) {
		this.bad = bad;
	}

	@Override
	public void setSecurityService(SecurityService ss) {
		this.ss = ss;
	}

}
