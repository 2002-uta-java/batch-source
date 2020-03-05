package com.revature.daos;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Reimbursement;
import com.revature.util.ConnectionUtil;

public class ReimbursementDaoImp implements ReimbursementDao {
	
		private static Logger log = Logger.getRootLogger();
		
		@Override
		public List<Reimbursement> getAll() {
			String sql = "select * from reimbursement";
			
			List<Reimbursement> reimbursements = new ArrayList<>();
			try (Connection c = ConnectionUtil.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql)){
				while(rs.next()) {
					int reimbursementId = rs.getInt("ReimbursementId");
					int employeeId = rs.getInt("EmployeeId");
					String status = rs.getString("Status");
					String category = rs.getString("Category");
					String resolver = rs.getString("Resolver");
					double amount = rs.getDouble("Amount");
					
					Reimbursement r =  new Reimbursement(reimbursementId, employeeId, status, category, amount, resolver);
					
					reimbursements.add(r);
				}
			}
			
			catch (SQLException e) {
				e.printStackTrace();
			}
			
			return reimbursements;
		}

		public List<Reimbursement> getAllReimbursementByStatus(String status) {
			String sql = "select * from Reimbursement where Status = ?";
			
			List<Reimbursement> reimbursements = new ArrayList<>();
			ResultSet rs = null;
			try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
				ps.setString(1, status);
				rs = ps.executeQuery();
				while(rs.next()) {
					int reimbursementId = rs.getInt("ReimbursementId");
					int employeeId = rs.getInt("EmployeeId");
					String sts = rs.getString("Status");
					String category = rs.getString("Category");
					String resolver = rs.getString("Resolver");
					double amount = rs.getDouble("Amount");
					
					Reimbursement r = new Reimbursement(reimbursementId, employeeId, status, amount, resolver, category);
					
					reimbursements.add(r);
				}
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					if(rs != null) {
						rs.close();
					}
				}
				catch(SQLException e) {
					e.printStackTrace();
					log.error("SQLException");
				}
			}
			return reimbursements;
		}

		@Override
		public Reimbursement getById(int id) {
			String sql = "select * from Reimbursement where ReimbursementId = ?";
				
			Reimbursement r = null;
			ResultSet rs = null;
			try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
				ps.setInt(1, id);
				rs = ps.executeQuery();
				while(rs.next()) {
					String employeeId = rs.getString("EmployeeId");
					String category = rs.getString("Category");
					double amount = rs.getDouble("Amount");
					
					r = new Reimbursement(id, category, amount);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					if(rs != null) {
						rs.close();
					}
				}
				catch(SQLException e) {
					e.printStackTrace();
					log.error("SQLException");
				}
			}		
			return r;
		}

		@Override 
		public int createReimbursement(Reimbursement r) {
			String sql = "insert into Reimbursement (status, EmployeeId, Category, Amount) values ('pending',?,?,?)";
			int numRowsAffected = 0;
			try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
				ps.setInt(1, r.getEmployeeId());
				ps.setString(2, r.getCategory());
				ps.setDouble(3, r.getAmount());
				numRowsAffected = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return numRowsAffected;
		}

		@Override
		public int updateReimbursement(Reimbursement r) {
			String sql = "update Reimbursement set Status = ? where ReimbursementId = ?";
			int numRowsAffected = 0;
			try (Connection c = ConnectionUtil.getConnection();
					PreparedStatement ps = c.prepareStatement(sql)){
					ps.setString(1, r.getStatus());
					ps.setInt(2, r.getReimbersementId());
					numRowsAffected = ps.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			return numRowsAffected;
		}

	@Override
	public List<Reimbursement> getAllReimbursementByUserId(int employeeId) {
		String sql = "select * from Reimbursement where EmployeeId = ?";

		List<Reimbursement> reimbursements = new ArrayList<>();
		ResultSet rs = null;
		try (Connection c = ConnectionUtil.getConnection();
			 PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, employeeId);
			rs = ps.executeQuery();
			while(rs.next()) {
				int reimbursementId = rs.getInt("ReimbursementId");
				int empId = rs.getInt("EmployeeId");
				String sts = rs.getString("Status");
				String category = rs.getString("Category");
				String resolver = rs.getString("Resolver");
				double amount = rs.getDouble("Amount");

				Reimbursement r = new Reimbursement(reimbursementId, employeeId, sts, amount, resolver, category);

				reimbursements.add(r);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null) {
					rs.close();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
				log.error("SQLException");
			}
		}
		return reimbursements;
	}
}