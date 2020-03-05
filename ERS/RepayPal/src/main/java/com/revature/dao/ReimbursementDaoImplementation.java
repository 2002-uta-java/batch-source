package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.model.Reimbursement;
import com.revature.util.ConnectionUtil;

public class ReimbursementDaoImplementation implements ReimbursementDao{

	private static Logger log = Logger.getLogger(ReimbursementDaoImplementation.class);
	
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
		catch (SQLException | ClassNotFoundException e) {
			log.error(e.getStackTrace());
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
			
		} catch (SQLException | ClassNotFoundException e) {
			log.error(e.getStackTrace());
		} 
		finally {
			try {
				if(rs!=null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.error(e.getStackTrace());
			}
		}
		
		return reimbursements;
	}
	
	@Override
	public Reimbursement getReimbursementById(int id) {
		String sql = "select * from reimbursement where id = ?";
		Reimbursement reimbursement = null;
		ResultSet rs = null;
		
		try(Connection c = ConnectionUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				reimbursement = new Reimbursement(rs.getInt("id"), rs.getString("username"), rs.getString("status"), 
						rs.getDouble("amount"), rs.getString("description"), rs.getString("resolved"));
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			log.error(e.getStackTrace());
		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.error(e.getStackTrace());
			}
		}
		
		return reimbursement;
	}

	@Override
	public int createReimbursement(Reimbursement r) {
		String sql = "insert into reimbursement (username, amount, description) values (?, ?, ?)";
		int reimbursementCreated = 0;
		if(r.getAmount() >= 0) {
			try(Connection c = ConnectionUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
				ps.setString(1, r.getUsername());
				ps.setDouble(2, r.getAmount());
				ps.setString(3, r.getDescription());
				reimbursementCreated = ps.executeUpdate();
				return reimbursementCreated;
				
			} 
			catch (SQLException | ClassNotFoundException e) {
				log.error(e.getStackTrace());
			}
		}
		return reimbursementCreated;
	}

	@Override
	public int updateReimbursement(Reimbursement r, String managerUsername) {
		String sql = "update reimbursement set status = ?, resolved = ? where id = ?";
		int reimbursementUpdated = 0;
		String string = r.getResolved()+" by: "+ managerUsername;
		log.debug(string);
		try(Connection c = ConnectionUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
			ps.setString(1, r.getStatus());
			ps.setString(2, string);
			ps.setInt(3, r.getId());
			reimbursementUpdated = ps.executeUpdate();
			
		} 
		catch (SQLException | ClassNotFoundException e) {
			log.error(e.getStackTrace());
		}
		
		return reimbursementUpdated;
	}
}
