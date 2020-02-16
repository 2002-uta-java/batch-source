package com.revature.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.banking.connections.ConnectionUtil;
import com.revature.banking.models.EncryptedBankAccount;
import com.revature.banking.models.EncryptedUser;

public class UserDaoImpl implements UserDao {

	private BankAccountDao bad;

	@Override
	public EncryptedUser getUserByTaxId(String encryptedTaxId) {
		final String sql = "select * from user where tax_id = ?";
		ResultSet rs = null;
		EncryptedUser eu = null;

		try (final Connection con = ConnectionUtil.getConnection();
				final PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, encryptedTaxId);

			rs = ps.executeQuery();

			while (rs.next()) {
				eu = new EncryptedUser();
				eu.setFirstName(rs.getString("firstname"));
				eu.setLastName(rs.getString("lastname"));
				eu.setTaxId(rs.getString("tax_id"));
				eu.setUsername(rs.getString("username"));
				eu.setEncryptedPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			Logger.getRootLogger().error(e.getStackTrace());
			return null;
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					Logger.getRootLogger().error(e.getStackTrace());
				}
		}

		return eu;
	}

	@Override
	public EncryptedBankAccount createNewUser(EncryptedUser user) {
		final String sql = "insert into users values(?,?,?,?,?)";
		EncryptedBankAccount eba = null;
		try (final Connection con = ConnectionUtil.getConnection();
				final PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, user.getTaxId());
			ps.setString(2, user.getFirstName());
			ps.setString(3, user.getLastName());
			ps.setString(4, user.getUsername());
			ps.setString(5, user.getEncryptedPassword());

			if (ps.execute()) {
				eba = bad.createNewBankAccount(user);
			} else {
				Logger.getRootLogger().error("Failed to create a new user: " + user);
				return null;
			}
		} catch (SQLException e) {
			Logger.getRootLogger().error(e.getStackTrace());
			return null;
		}

		return eba;
	}

	@Override
	public void setBankAccountDao(BankAccountDao bad) {
		this.bad = bad;
	}

}
