package com.hylicmerit.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hylicmerit.models.Reimbursement;
import com.hylicmerit.util.ConnectionUtil;

public class ReimbursementDaoImpl implements ReimbursementDao{
	@Override
	public List<Reimbursement> getAll() {
		
		String sql = "select * from reimbursement";
		
		List<Reimbursement> reimbursements = new ArrayList<>();
		try (Connection c = ConnectionUtil.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql)){
			while(rs.next()) {
				int id = rs.getInt("rmb_id");
				String employee_email = rs.getString("employee_email");
				String manager_email = rs.getString("manager_email");
				String date = rs.getString("date");
				String status = rs.getString("status");
				String description = rs.getString("description");
				double amount = rs.getDouble("amount");
				
				Reimbursement r = new Reimbursement(id, employee_email, manager_email, status, description, date, amount);
				
				reimbursements.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimbursements;
	}

	@Override
	public List<Reimbursement> getAllByEmployee(String email) {
		
		String sql = "select * from reimbursement where employee_email=?";
		
		List<Reimbursement> reimbursements = new ArrayList<>();
		ResultSet rs = null;
		try (Connection c = ConnectionUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql)){
			ps.setString(1, email);
			rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("rmb_id");
				String employee_email = rs.getString("employee_email");
				String manager_email = rs.getString("manager_email");
				String date = rs.getString("date");
				String status = rs.getString("status");
				String description = rs.getString("description");
				double amount = rs.getDouble("amount");
				
				Reimbursement r = new Reimbursement(id, employee_email, manager_email, status, description, date, amount);
				
				reimbursements.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimbursements;
	}
	
	@Override
	public List<Reimbursement> getAllByManager(String email) {
		
		String sql = "{call get_reimbursements_by_manager(?)}";
		
		List<Reimbursement> reimbursements = new ArrayList<>();
		ResultSet rs = null;
		try (Connection c = ConnectionUtil.getConnection();
			CallableStatement cs = c.prepareCall(sql)){
			cs.setString(1, email);
			rs = cs.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("rmb_id");
				String employee_email = rs.getString("employee_email");
				String manager_email = rs.getString("manager_email");
				String date = rs.getString("date");
				String status = rs.getString("status");
				String description = rs.getString("description");
				double amount = rs.getDouble("amount");
				
				Reimbursement r = new Reimbursement(id, employee_email, manager_email, status, description, date, amount);
				
				reimbursements.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimbursements;
	}
	
	@Override
	public Reimbursement getById(int id) {
		
		String sql = "select * from reimbursement where rmb_id=?";
		
		Reimbursement r = null;
		ResultSet rs = null;
		try (Connection c = ConnectionUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				String employee_email = rs.getString("employee_email");
				String manager_email = rs.getString("manager_email");
				String date = rs.getString("date");
				String status = rs.getString("status");
				String description = rs.getString("description");
				double amount = rs.getDouble("amount");
				
				r = new Reimbursement(id, employee_email, manager_email, status, description, date, amount);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public int createReimbursement(Reimbursement r) {
		String sql = "insert into reimbursement (employee_email, manager_email, description, \"date\", amount, status) values(?,?,?,?,?,'pending')";
		int numRowsAffected = 0;
		try (Connection c = ConnectionUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql)){
			ps.setString(1, r.getEmployee_email());
			ps.setString(2, r.getManager_email());
			ps.setString(3, r.getDescription());
			ps.setString(4, r.getDate());
			ps.setDouble(5, r.getAmount());
			numRowsAffected = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numRowsAffected;
	}

	@Override
	public int updateReimbursement(Reimbursement r) {
		String sql = "update reimbursement set status=? where rmb_id=?";
		int numRowsAffected = 0;
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
				ps.setString(1, r.getStatus());
				ps.setInt(2, r.getId());
				numRowsAffected = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return numRowsAffected;
	}

	@Override
	public int deleteReimbursement(Reimbursement r) {
		String sql = "delete from reimbursement where rmb_id=?";
		int numRowsAffected = 0;
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
				ps.setInt(1, r.getId());
				numRowsAffected = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return numRowsAffected;
	}

}
