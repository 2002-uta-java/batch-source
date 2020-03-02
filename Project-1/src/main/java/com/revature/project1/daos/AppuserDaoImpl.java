package com.revature.project1.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.project1.models.Appuser;
import com.revature.project1.util.ConnectionUtil;

public class AppuserDaoImpl implements AppuserDao {


	@Override
	public List<Appuser> getAppuser() {

		String sql = "select * from app_user;";
		List<Appuser> appusers = new ArrayList<>();

		try (Connection c = ConnectionUtil.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql)) {

			while (rs.next()) {
				Appuser a = new Appuser();
				a.setId(rs.getInt("user_id"));
				a.setManager(rs.getBoolean("user_manager"));
				a.setEmail(rs.getString("user_email"));
				a.setPass(rs.getString("user_pass"));
				a.setFirstname(rs.getString("user_firstname"));
				a.setLastname(rs.getString("user_lastname"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return appusers;
	}
	
	@Override
	public Appuser getAppuserByEmail(String email) {
		String sql = "select * from app_user where user_email = ?;";
		ResultSet rs = null;
		Appuser a = null;

		
		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setString(1, email);
			rs = ps.executeQuery();

			while (rs.next()) {
				a = new Appuser();
				a.setId(rs.getInt("user_id"));
				a.setManager(rs.getBoolean("user_manager"));
				a.setEmail(rs.getString("user_email"));
				a.setPass(rs.getString("user_pass"));
				a.setFirstname(rs.getString("user_firstname"));
				a.setLastname(rs.getString("user_lastname"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs !=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return a;

	}

	@Override
	public int updateAppuser(Appuser a) {
		
		String sql = "update app_user; set user_email = ?, user_pass = ?, user_firstname = ?, user_lastname = ? where user_id = ?;";
		int check = 0;
		
		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			
			ps.setString(1, a.getEmail());
			ps.setString(2, a.getPass());
			ps.setString(3, a.getFirstname());
			ps.setString(4, a.getLastname());
			ps.setInt(5, a.getId());
			
			check = ps.executeUpdate();
			 System.out.println("updated user");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(check);
		return check;
	}

}
