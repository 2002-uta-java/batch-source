package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.util.ConnectionUtil;

public class ReimbursementDaoImpl implements ReimbursementDao{

	@Override
	public List<Reimbursement> getReimbursementsAll() {
		String sql = "select * from reimbursement";
		
		List<Reimbursement> reimbursements = new ArrayList<>();
		
		try (Connection c = ConnectionUtil.getConnection()) {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("id"); 
				String purpose = rs.getString("purpose");
				float amount = rs.getFloat("amount");
				int idEmployee = rs.getInt("id_employee");
				int idManager = rs.getInt("id_manager");
				String status = rs.getString("status");
				Reimbursement r = new Reimbursement(id, purpose, amount, idEmployee, idManager, status);
				reimbursements.add(r);
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return reimbursements;
	}

	@Override
	public List<Reimbursement> getReimbursementsByEmployeeId(int id) {
		String sql = "select * from reimbursement where id_employee = ?";
		List<Reimbursement> reimbursements = new ArrayList<>();
		ResultSet rs = null;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String purpose = rs.getString("purpose");
				Float amount = rs.getFloat("amount");
				int idEmployee = rs.getInt("id_employee");
				int idManager = rs.getInt("id_manager");
				String status = rs.getString("status");
				Reimbursement r = new Reimbursement(id, purpose, amount, idEmployee, idManager, status);
				reimbursements.add(r);
			}
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return reimbursements;
	}

	@Override
	public void createReimbursement(Reimbursement r) {
		String sql = "insert into reimbursement (id, purpose, amount, id_employee, id_manager, status) "
				+ "values (?, ?, ?, ?, ?, ?)";
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, r.getId());
			ps.setString(2, r.getPurpose());
			ps.setFloat(3, r.getAmount());
			ps.setInt(4, r.getIdEmployee());
			ps.setInt(5, r.getIdManager()); // will be a dummy manager at first (id: 0) cuz FK cannot be null.
			ps.setString(6, r.getStatus());
			
			ps.executeUpdate();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	public void updateReimbursement(Reimbursement r) {
		String sql = "update reimbursement set status = ?, id_manager = ? where id = ?";
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, r.getStatus());
			ps.setInt(2, r.getIdManager()); // the manager who resolved the reimbursement.
			ps.setInt(3, r.getId());
			
			ps.executeUpdate();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		
	}

	@Override
	public List<Reimbursement> getPendingReimbursements() {
		String sql = "select * from reimbursement";
		
		List<Reimbursement> reimbursements = new ArrayList<>();
		
		try (Connection c = ConnectionUtil.getConnection()) {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("id"); 
				String purpose = rs.getString("purpose");
				float amount = rs.getFloat("amount");
				int idEmployee = rs.getInt("id_employee");
				int idManager = rs.getInt("id_manager");
				String status = rs.getString("status");
				if (status.equals("pending")) {
					Reimbursement r = new Reimbursement(id, purpose, amount, idEmployee, idManager, status);
					reimbursements.add(r);
				}
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return reimbursements;
	}

	@Override
	public List<Reimbursement> getResolvedReimbursements() {
		String sql = "select * from reimbursement";
		
		List<Reimbursement> reimbursements = new ArrayList<>();
		
		try (Connection c = ConnectionUtil.getConnection()) {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("id"); 
				String purpose = rs.getString("purpose");
				float amount = rs.getFloat("amount");
				int idEmployee = rs.getInt("id_employee");
				int idManager = rs.getInt("id_manager");
				String status = rs.getString("status");
				if (!status.equals("pending")) {
					Reimbursement r = new Reimbursement(id, purpose, amount, idEmployee, idManager, status);
					reimbursements.add(r);
				}
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return reimbursements;
	}

	@Override
	public Reimbursement getReimbursementById(int id) {
		String sql = "select * from reimbursement where id = ?";
		Reimbursement r = null;
		ResultSet rs = null;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String purpose = rs.getString("purpose");
				Float amount = rs.getFloat("amount");
				int idEmployee = rs.getInt("id_employee");
				int idManager = rs.getInt("id_manager");
				String status = rs.getString("status");
				r = new Reimbursement(id, purpose, amount, idEmployee, idManager, status);
			}
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return r;
	}
	
}
