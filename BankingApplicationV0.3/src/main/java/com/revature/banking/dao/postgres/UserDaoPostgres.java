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
			// Returning null will likely cause a program crash (which is the desired effect
			// since I don't want to call this method and get an empty result if the results
			// shouldn't be empty)
			Logger.getRootLogger().fatal(e.getMessage());
			return null;
		}
		return users;
	}

	@Override
	public EncryptedBankAccount createNewUser(EncryptedUser eUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EncryptedBankAccount updateUserCreateNewAccount(EncryptedUser eUser) {
		// TODO Auto-generated method stub
		return null;
	}
}
