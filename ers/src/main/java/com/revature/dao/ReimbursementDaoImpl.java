package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Reimbursement;

public class ReimbursementDaoImpl implements ReimbursementDAO {

	@Override
	public List<Reimbursement> getAllReimbursements() {
			List<Reimbursement> reimbursements = new ArrayList<>();
			Connection connection = null;
			Statement stmt = null;
			
			try {
				connection = DAOUtilities.getConnection();
				stmt = connection.createStatement();
				String sql = "SELECT * FROM Reimbursment";
				ResultSet rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
					Reimbursement re = new Reimbursement();
					
					re.setId(rs.getInt("id"));
					re.setAmount(rs.getInt("amount"));
					re.setStage(rs.getString("stage"));
					re.setTime(rs.getTimestamp("timestamp"));
					re.setEmployeeId(rs.getInt("employee_id"));
					
					reimbursements.add(re);
				}
			} catch (SQLException e){
				e.printStackTrace();
			} finally {
				try {
					if (stmt != null)
						stmt.close();
//					if (connection != null)
//						connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return reimbursements;
	}

	@Override
	public Reimbursement getReimbursement(int id) {
		Reimbursement re = new Reimbursement();
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM Reimbursment WHERE id = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				re.setId(rs.getInt("id"));
				re.setAmount(rs.getInt("amount"));
				re.setStage(rs.getString("stage"));
				re.setTime(rs.getTimestamp("timestamp"));
				re.setEmployeeId(rs.getInt("employee_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
//				if (connection != null)
//					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return re;
	}

	@Override
	public void addReimbursement(Reimbursement reimbursement) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO Reimbursment (amount, stage, timestamp, employee_id) VALUES (?, ?, ?, ?)";
			stmt = connection.prepareStatement(sql);
			
			Timestamp ts = (Timestamp) reimbursement.getTime();
			
			stmt.setInt(1, reimbursement.getAmount());
			stmt.setString(2, reimbursement.getStage());
			stmt.setTimestamp(3, ts);
			stmt.setInt(4, reimbursement.getEmployeeId());
			
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
//				if (connection != null)
//					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (success == 0) {
			throw new Exception("Insert Reimbursement failed: " + reimbursement);
		}
	}

	@Override
	public void deleteReimbursement(int id) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM Reimbursment WHERE id = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
//				if (connection != null)
//					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (success == 0) {
			throw new Exception("Delete Reimbursement failed: " + id);
		}
	}

	@Override
	public void updateReimbursement(Reimbursement reimbursement) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "Update Reimbursment (amount, stage, timestamp, employee_id) VALUES (?, ?, ?, ?) WHERE id = ?";
			stmt = connection.prepareStatement(sql);
			
			Timestamp ts = (Timestamp) reimbursement.getTime();
			
			stmt.setInt(1, reimbursement.getAmount());
			stmt.setString(2, reimbursement.getStage());
			stmt.setTimestamp(3, ts);
			stmt.setInt(4, reimbursement.getEmployeeId());
			stmt.setInt(5, reimbursement.getId());
			
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
//				if (connection != null)
//					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (success == 0) {
			throw new Exception("Insert Reimbursement failed: " + reimbursement);
		}
	}

}
