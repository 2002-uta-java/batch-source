package com.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.models.Login;
import com.revature.util.ConnectionUtil;

public class LoginDaoImpl implements LoginDao {

	@Override
	public Login getLoginByUser(String user) {
		Login l = new Login();

		String sql = "select * from p1_login where username = ?";
		ResultSet rs = null;
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, user);

			rs = ps.executeQuery();

			while (rs.next()) {
				int empId = rs.getInt("emp_id");
				l.setId(empId);

				String username = rs.getString("username");
				l.setUser(username);

				String pwsd = rs.getString("password");
				l.setPwsd(pwsd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return l;
	}

	@Override
	public List<Login> getLogins() {
		List<Login> loginList = new ArrayList<Login>();

		String sql = "select * from p1_login";

		try (Connection con = ConnectionUtil.getConnection(); Statement s = con.createStatement();) {
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				Login l = new Login();
				int empId = rs.getInt("emp_id");
				l.setId(empId);

				String user = rs.getString("username");
				l.setUser(user);

				String pwsd = rs.getString("password");
				l.setPwsd(pwsd);

				loginList.add(l);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loginList;
	}

}
