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
import com.revature.banking.dao.UserDao;
import com.revature.banking.services.security.models.EncryptedBankAccount;
import com.revature.banking.services.security.models.EncryptedUser;

/**
 * This is the implementation for {@link UserDao} to interact with my PostgreSQL
 * database.
 * 
 * @author Jared F Bennatt
 *
 */
public class UserDaoPostgres implements UserDao {
	private BankAccountDao baDao = null;

	public UserDaoPostgres() {
		super();
	}

	@Override
	public void setBankAccountDao(BankAccountDao baDao) {
		this.baDao = baDao;
	}

	@Override
	public Set<EncryptedUser> getAllUsers() {
		// grab all users from database
		final String sql = "select * from users";

		// setup set to be returned
		final Set<EncryptedUser> users = new HashSet<>();

		try (final Connection con = ConnectionUtil.getConnection();
				final Statement s = con.createStatement();
				final ResultSet rs = s.executeQuery(sql)) {
			// iterate through result set
			while (rs.next()) {
				// setup EncryptedUser
				final EncryptedUser eUser = new EncryptedUser();
				eUser.setUserKey(rs.getInt("user_key"));
				eUser.setFirstName(rs.getString("firstname"));
				eUser.setLastName(rs.getString("lastname"));
				eUser.setUserName(rs.getString("username"));
				eUser.setPassword(rs.getString("password"));

				users.add(eUser);
			}
		} catch (SQLException e) {
			// this is a fatal error (probably means we weren't able to connect)
			// returning null signals that this method failed
			Logger.getRootLogger().fatal("getAllUsers failed: " + e.getMessage());
			return null;
		}
		return users;
	}

	@Override
	public boolean createNewUser(final EncryptedUser eUser, final EncryptedBankAccount eba) {
		// add new user to database
		final String newUserSQL = "insert into users (tax_id, firstname, lastname, "
				+ "username, password) values (?,?,?,?,?)";
		ResultSet rs = null;

		try (final Connection con = ConnectionUtil.getConnection();
				final PreparedStatement ps = con.prepareStatement(newUserSQL);) {
			ps.setString(1, eUser.getTaxId());
			ps.setString(2, eUser.getFirstName());
			ps.setString(3, eUser.getLastName());
			ps.setString(4, eUser.getUserName());
			ps.setString(5, eUser.getPassword());

			rs = ps.executeQuery();

			if (!rs.first()) {
				// check to make sure this actually inserted a new row
				Logger.getRootLogger().error("Failed to create new user: " + eUser);
				return false;
			}

			while (rs.next()) {
				// give eUser the user_key
				eUser.setUserKey(rs.getInt("user_key"));
			}
		} catch (SQLException e) {
			// this is a fatal error (probably means we weren't able to connect)
			// returning null signals that this failed
			Logger.getRootLogger().fatal("Create New User, " + eUser + ", failed: " + e.getMessage());
			return false;
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					Logger.getRootLogger().error("Failed to close ResultSet: " + e.getMessage());
				}
		}

		// TODO need to add account to database and then link them
		return true;
	}

	@Override
	public EncryptedBankAccount updateUserCreateNewAccount(EncryptedUser eUser) {
		// TODO Auto-generated method stub
		return null;
	}
}
