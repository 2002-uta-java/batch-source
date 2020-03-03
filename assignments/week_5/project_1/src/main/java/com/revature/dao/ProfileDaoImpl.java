package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Profile;
import com.revature.util.ConnectionUtil;

public class ProfileDaoImpl implements ProfileDao{

	// connects to database and creates a new user using a stored function. Returns the id of the new entry created.
	@Override
	public int createProfile(Profile p) {
		
		String sql = "{call add_profile(?, ?, ?)}";
		
		int usedId = 0;
		
		ResultSet rs = null;
		
		try (Connection con = ConnectionUtil.getConnection();
				CallableStatement cs = con.prepareCall(sql)){
			
			cs.setString(1, p.getUser());
			cs.setString(2, p.getPassword());
			cs.setInt(3, p.getIsManager());
			
			cs.execute();
			
			rs = cs.getResultSet();
			
			while(rs.next()) {
				usedId = rs.getInt("id");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return usedId;
	}

	// receives user log in input and checks the database for a match. Returns a profile object with employee id included
	@Override
	public Profile validateProfile(Profile p) {
		
		String sql = "select * from {oj profile left outer join employee on (profile.id = employee.profile)} where ((profile.user_name = ?) and (profile.user_password = ?))";
		
		ResultSet rs = null;
		
		Profile pro = new Profile();
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setString(1, p.getUser());
			ps.setString(2, p.getPassword());
			
			ps.execute();
			
			rs = ps.getResultSet();
			
			while (rs.next()) {
				pro.setId(rs.getInt("id"));
				pro.setIsManager(rs.getInt("is_manager"));
				pro.setEmployeeId(rs.getInt(5));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return pro;
	}

//	@Override
//	public int updateProfile(Profile p) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

}
