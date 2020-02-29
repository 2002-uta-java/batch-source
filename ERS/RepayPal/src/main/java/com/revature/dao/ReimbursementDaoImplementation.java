package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Reimbursement;
import com.revature.util.ConnectionUtil;

public class ReimbursementDaoImplementation implements ReimbursementDao{

	@Override
	public List<Reimbursement> getReimbursements() {
		String sql = "select * reimbursement";
		List<Reimbursement> reimbursements = new ArrayList<>();
			
		try (Connection c = ConnectionUtil.getConnection();Statement s = c.createStatement();ResultSet rs = s.executeQuery(sql)){
			while(rs.next()) {
				reimbursements.add(new Reimbursement(rs.getInt("id"), rs.getString("username"), rs.getString("status"), 
						rs.getDouble("amount"), rs.getString("description"), rs.getString("resolved")));
			}
			
		} 
		catch (SQLException e) {
			//e.printStackTrace();
		}

		return reimbursements;
	}

	@Override
	public Reimbursement getReimbursementByUsername(String username) {
		String sql = "select * from reimbursement where username = ?";
		Reimbursement reimbursement = null;
		ResultSet rs = null;
		
		try(Connection c = ConnectionUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				reimbursement = new Reimbursement(rs.getInt("id"), rs.getString("username"), rs.getString("status"), 
						rs.getDouble("amount"), rs.getString("description"), rs.getString("resolved"));
			}
			
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		
		return reimbursement;
	}

	@Override
	public int createReimbursement(Reimbursement r) {
		String sql = "insert into reimbursement (username, amount, description) values (?, ?, ?)";
		int reimbursementCreated = 0;
		
		try(Connection c = ConnectionUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
			ps.setString(1, r.getUsername());
			ps.setDouble(2, r.getAmount());
			ps.setString(3, r.getDescription());
			reimbursementCreated = ps.executeUpdate();
			return reimbursementCreated;
			
		} 
		catch (SQLException e) {
			//e.printStackTrace();
		}
		
		return reimbursementCreated;
	}

	@Override
	public int updateReimbursement(Reimbursement r, String managerUsername) {
		String sql = "update reimbursement set status = ?, resolved = ? where id = ?";
		int reimbursementUpdated = 0;
		
		try(Connection c = ConnectionUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
			ps.setString(1, r.getStatus());
			ps.setString(2, r.getResolved()+" by: "+managerUsername);
			reimbursementUpdated = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reimbursementUpdated;
	}

	@Override
	public int deleteReimbursement(Reimbursement r) {
		return 0;
	}

	@Override
	public Reimbursement createReimbursementWithDefaultManager(Reimbursement r) {
		return null;
	}
}
