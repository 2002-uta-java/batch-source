package com.project1.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project1.models.Employee;
import com.project1.models.Reimbursement;
import com.project1.util.ConnectionUtil;

public class ReimbursementDaoImpl implements ReimbursementDao{

	@Override
	public List<Reimbursement> getReimbursementByEmpId(int id) {
		String sql = "select * from reimbursement where emp_id = ?";
		List<Reimbursement> rList = new ArrayList<>();
		ResultSet rs = null;
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);){
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int reimbId = rs.getInt("reimb_id");
				int empId = rs.getInt("emp_id");
				int approve = rs.getInt("approve_by");
				double amount = rs.getDouble("amount");
				String purpose = rs.getString("purpose");
				String reimbStatus = rs.getString("reimb_status");
				
				Reimbursement reimb = new Reimbursement(reimbId, empId, approve, amount, purpose, reimbStatus);
				rList.add(reimb);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return rList;
	}

	@Override
	public int updateReimbursement(Reimbursement r) {
		String sql = "update reimbursement set approve_by = ?, reimb_status = ? where reimb_id = ?";
		int reimbUpdated = 0;
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, r.getApproveBy());
			ps.setString(2, r.getReimbStatus());
			ps.setInt(3, r.getReimbId());
			
			reimbUpdated = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reimbUpdated;
	}

	@Override
	public Reimbursement createReimbWithFunction(Reimbursement r) {
		String sql = "{call add_reimbursement(?, ?, ?)}";
		ResultSet rs = null;
		
		try(Connection conn = ConnectionUtil.getConnection();
				CallableStatement cs = conn.prepareCall(sql)){
			cs.setInt(1, r.getEmpId());
			cs.setDouble(2, r.getAmount());
			cs.setString(3, r.getPurpose());
		
			cs.execute();
			
			rs = cs.getResultSet();
			
			while(rs.next()) {
				r.setReimbId(rs.getInt("reimb_id"));
				r.setEmpId(rs.getInt("emp_id"));
				r.setAmount(rs.getDouble("amount"));
				r.setPurpose(rs.getString("purpose"));
				r.setApproveBy(rs.getInt("approve_by"));
				r.setReimbStatus(rs.getString("reimb_status"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
			}
		}
	}
		
		return r;
	}

	@Override
	public List<Reimbursement> getAllReimbByStatus(String status) {
		String sql = "select * from reimbursement where reimb_status = ?";
		List<Reimbursement> reimbList = new ArrayList<>();
		ResultSet rs = null;
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);){
			ps.setString(1, status);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int reimbId = rs.getInt("reimb_id");
				int empId = rs.getInt("emp_id");
				int approve = rs.getInt("approve_by");
				double amount = rs.getDouble("amount");
				String purpose = rs.getString("purpose");
				String reimbStatus = rs.getString("reimb_status");
				
				Reimbursement reimb = new Reimbursement(reimbId, empId, approve, amount, purpose, reimbStatus);
				reimbList.add(reimb);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return reimbList;
	}

	@Override
	public List<Reimbursement> getEmpReimbByIdPending(int id) {
		String status= "Pending";
		String sql = "select * from reimbursement where emp_id = ? and reimb_status = ?";
		List<Reimbursement> reimbList = new ArrayList<>();
		ResultSet rs = null;
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);){
			ps.setInt(1, id);
			ps.setString(2, status);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int reimbId = rs.getInt("reimb_id");
				int empId = rs.getInt("emp_id");
				int approve = rs.getInt("approve_by");
				double amount = rs.getDouble("amount");
				String purpose = rs.getString("purpose");
				String reimbStatus = rs.getString("reimb_status");
				
				Reimbursement reimb = new Reimbursement(reimbId, empId, approve, amount, purpose, reimbStatus);
				reimbList.add(reimb);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return reimbList;
	}

	@Override
	public List<Reimbursement> getEmpReimbByIdResolve(int id) {
		String status= "Approve";
		String status2= "Deny";
		String sql = "select * from reimbursement where emp_id = ? and (reimb_status = ? or reimb_status = ?)";
		List<Reimbursement> reimbList = new ArrayList<>();
		ResultSet rs = null;
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);){
			ps.setInt(1, id);
			ps.setString(2, status);
			ps.setString(3, status);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int reimbId = rs.getInt("reimb_id");
				int empId = rs.getInt("emp_id");
				int approve = rs.getInt("approve_by");
				double amount = rs.getDouble("amount");
				String purpose = rs.getString("purpose");
				String reimbStatus = rs.getString("reimb_status");
				
				Reimbursement reimb = new Reimbursement(reimbId, empId, approve, amount, purpose, reimbStatus);
				reimbList.add(reimb);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return reimbList;
	}

	@Override
	public Reimbursement getReimbById(int id) {
		String sql = "select * from reimbursement where reimb_id = ?";
		ResultSet rs = null;
		Reimbursement reimb = null;
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);){
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int reimbId = rs.getInt("reimb_id");
				int empId = rs.getInt("emp_id");
				int approve = rs.getInt("approve_by");
				double amount = rs.getDouble("amount");
				String purpose = rs.getString("purpose");
				String reimbStatus = rs.getString("reimb_status");
				
				reimb = new Reimbursement(reimbId, empId, approve, amount, purpose, reimbStatus);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return reimb;
	}

	

}
