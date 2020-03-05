package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class UserDAOImpl implements UserDAO {

	@Override
	public boolean checkPassword(String username, String password) {
		String query = "select crypt(?, (select password from person where username=?)) "
				+ " = password from person where username = ?;";
		ResultSet rs = null;
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(query)) {
			ps.setString(1, password);
			ps.setString(2, username);
			ps.setString(3, username);

			rs = ps.executeQuery();

			if (rs.next()) {
				Boolean b = rs.getBoolean(1);
				return b;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ServletException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean isManager(String username) {
		//User u = null;
		String query = "select m.id as id from manager m left join person p on m.person_id = p.id where p.username = ?;";
		ResultSet rs = null;
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(query)) {
			ps.setString(1, username);

			rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ServletException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

}
