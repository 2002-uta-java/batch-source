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
		String sql = "select * from reimbursement";
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		Reimbursement r;
		try (Connection c = ConnectionUtil.getConnection();Statement s = c.createStatement();ResultSet rs = s.executeQuery(sql)){
			while(rs.next()) {
				r = new Reimbursement(rs.getInt("id"), rs.getString("username"), rs.getString("status"), 
						rs.getDouble("amount"), rs.getString("description"), rs.getString("resolved"));
				reimbursements.add(r);
			}
			
		} 
		catch (SQLException e) {
			//e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return reimbursements;
	}

	@Override
	public List<Reimbursement> getReimbursementsByUsername(String username) {
		String sql = "select * from reimbursement where username = ?";
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		ResultSet rs = null;
		
		try(Connection c = ConnectionUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				reimbursements.add(new Reimbursement(rs.getInt("id"), rs.getString("username"), rs.getString("status"), 
						rs.getDouble("amount"), rs.getString("description"), rs.getString("resolved")));
			}
			
		} catch (SQLException e) {
			//e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		
		return reimbursements;
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
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return reimbursementCreated;
	}

	@Override
	public int updateReimbursement(Reimbursement r, String managerUsername) {
		String sql = "update reimbursement set status = ?, resolved = ? where id = ?";
		int reimbursementUpdated = 0;
		String string = r.getResolved()+" by: "+ managerUsername;
		System.out.println(string);
		try(Connection c = ConnectionUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
			ps.setString(1, r.getStatus());
			ps.setString(2, string);
			ps.setInt(3, r.getId());
			reimbursementUpdated = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
