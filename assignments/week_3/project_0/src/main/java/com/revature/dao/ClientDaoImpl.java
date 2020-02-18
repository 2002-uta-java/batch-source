package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.models.Client;
import com.revature.util.ConnectionUtil;

public class ClientDaoImpl implements ClientDao{

	@Override
	public int createClient() {
		return 0;
	}
	
	@Override
	public Client createClient(Client c) {
		
//		int response = 0;
		String sql = "{call add_client(?, ?)}";
		
		ResultSet rs = null;
		
		try (Connection con = ConnectionUtil.getConnection();
				CallableStatement cs = con.prepareCall(sql)){
			
			cs.setString(1, c.getFirstName());
			cs.setString(2, c.getLastName());
			
			cs.execute();
			
			rs = cs.getResultSet();

			while (rs.next()) {
				int newId = rs.getInt("id");
				c.setId(newId);
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
		
		return c;
	}
	
//	public static int createClient(String firstName, String lastName) {
//		
//		int response = 0;
//		String sql = "insert into client (first_name, last_name) values (?, ?)";
//		
//		try (Connection	c = ConnectionUtil.getConnection();
//			PreparedStatement ps = c.prepareStatement(sql);) {
//			
//			ps.setString(1, firstName);
//			ps.setString(2, lastName);
//			
//			response = ps.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return response;					
//	}

	@Override
	public int updateClient() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeClient() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Client getClientById(int id) {
		
		Client c = new Client();
		
		String sql = "select * from client where id = ?";
		
		ResultSet rs = null;
		
		try (Connection	con = ConnectionUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)) {
			
			ps.setInt(1, id);
			
			ps.execute();
			
			rs = ps.getResultSet();
			
			while(rs.next()) {
				c.setId(rs.getInt("id"));
				c.setFirstName(rs.getString("first_name"));
				c.setLastName(rs.getString("last_name"));
			}
			
		} catch (SQLException e) {
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
		return c;
	}

}