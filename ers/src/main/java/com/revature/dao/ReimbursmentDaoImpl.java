package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Reimbursment;

public class ReimbursmentDaoImpl implements ReimbursementDAO {

	@Override
	public List<Reimbursment> getAllReimbursments() {
			List<Reimbursment> reimbursments = new ArrayList<>();
			Connection connection = null;
			Statement stmt = null;
			
			try {
				connection = DAOUtilities.getConnection();
				stmt = connection.createStatement();
				String sql = "SELECT * FROM Reimbursment";
				ResultSet rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
					Reimbursment re = new Reimbursment();
					
					re.setId(rs.getInt("reimbursment_id"));
					re.setAmount(rs.getInt("amount"));
					re.setStage(rs.getString("stage"));
					re.setTime(rs.getTime("time"));
					re.setEmployeeId(rs.getInt("employee_id"));
				}
			} catch (SQLException e){
				e.printStackTrace();
			} finally {
				try {
					if (stmt != null)
						stmt.close();
					if (connection != null)
						connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return reimbursments;
	}

	@Override
	public Reimbursment getReimbursment(int id) {
		Reimbursment re = new Reimbursment();
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM Reimbursment WHERE reimbursment_id = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				re.setId(rs.getInt("reimbursment_id"));
				re.setAmount(rs.getInt("amount"));
				re.setStage(rs.getString("stage"));
				re.setTime(rs.getTime("time"));
				re.setEmployeeId(rs.getInt("employee_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return re;
	}

	@Override
	public void addReimbursment(Reimbursment reimbursment) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO Reimbursment (amount, stage, time, employee_id) VALUES (?, ?, ?, ?)";
			stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, reimbursment.getAmount());
			stmt.setString(2, reimbursment.getStage());
			stmt.setTime(3, reimbursment.getTime());
			stmt.setInt(4, reimbursment.getEmployeeId());
			
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (success == 0) {
			throw new Exception("Insert Reimbursment failed: " + reimbursment);
		}
	}

	@Override
	public void deleteReimbursment(int id) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM Reimbursment WHERE reimbursment_id = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (success == 0) {
			throw new Exception("Delete Reimbursment failed: " + id);
		}
	}

	@Override
	public void updateReimbursment(Reimbursment reimbursment) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "Update Reimbursment (amount, stage, time, employee_id) VALUES (?, ?, ?, ?) WHERE reimbursment_id = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, reimbursment.getAmount());
			stmt.setString(2, reimbursment.getStage());
			stmt.setTime(3, reimbursment.getTime());
			stmt.setInt(4, reimbursment.getEmployeeId());
			stmt.setInt(5, reimbursment.getId());
			
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (success == 0) {
			throw new Exception("Insert Reimbursment failed: " + reimbursment);
		}
	}

}
