package com.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.models.Profile;
import com.revature.util.ConnectionUtil;

public class ProfileDaoImpl implements ProfileDao {
	public static Logger log = Logger.getRootLogger();
	@Override
	public Profile getProfileById(int id) {
		Profile p = new Profile();

		String sql = "select * from p1_profile where emp_id = ?";
		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);

			rs = ps.executeQuery();
			while (rs.next()) {
				int empId = rs.getInt("emp_id");
				p.setId(empId);

				String firstname = rs.getString("firstname");
				p.setFirstname(firstname);

				String lastname = rs.getString("lastname");
				p.setLastname(lastname);

				Long phone = rs.getLong("phone");
				p.setPhone(phone);

				String email = rs.getString("email");
				p.setEmail(email);
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
		return p;
	}

	@Override
	public List<Profile> getProfiles() {
		List<Profile> profileList = new ArrayList<Profile>();

		String sql = "select * from p1_profile";

		try (Connection con = ConnectionUtil.getConnection(); Statement s = con.createStatement();) {
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				Profile p = new Profile();
				int empId = rs.getInt("emp_id");
				p.setId(empId);

				String firstname = rs.getString("firstname");
				p.setFirstname(firstname);

				String lastname = rs.getString("lastname");
				p.setLastname(lastname);

				Long phone = rs.getLong("phone");
				p.setPhone(phone);

				String email = rs.getString("email");
				p.setEmail(email);

				profileList.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("email");
		return profileList;
	}

	@Override
	public int updateProfile(Profile profile) {
		int profsUpdated = 0;
		String sql = "update p1_profile " + "set firstname = ? ," + " lastname = ? ," + " phone = ? ," + " email = ? "
				+ "where emp_id = ?";

		try (Connection con = ConnectionUtil.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);) {
		ps.setString(1, profile.getFirstname());
		ps.setString(2, profile.getLastname());
		ps.setLong(3, profile.getPhone());
		ps.setString(4, profile.getEmail());
		ps.setInt(5, profile.getId());
		profsUpdated = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return profsUpdated;
	}

}
