package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Discussion;
import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.util.ConnectionUtil;

public class ReimbursementDaoImpl implements ReimbursementDao{

	@Override
	public Reimbursement createReimbursement(Reimbursement r) {
		
		String sql = "{call add_reimbursement(?, ?, ?, ?, ?, ?)}";
		
		ResultSet rs = null;
		
		try (Connection con = ConnectionUtil.getConnection();
				CallableStatement cs = con.prepareCall(sql)){
			
			cs.setInt(1, 11);
			cs.setInt(2, r.getEmployee());
			cs.setInt(3, r.getCategoryId());
			cs.setDouble(4, r.getAmount());
			cs.setInt(5, 1);
			cs.setString(6, r.getDiscussion());
			
			cs.execute();
			
			rs = cs.getResultSet();
			
			while(rs.next()) {
				r.setId(rs.getInt("id"));
				r.setBatch(rs.getInt("batch"));
				r.setDateCreated(rs.getString("date_created"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
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
	public Reimbursement getReimbursementById(int id) {
		
		String sql = "select * from reimbursement where id = ?";
		
//		String sql = "select * from {oj reimbursement left outer join employee on (reimbursement.employee = employee.id)}";
		
		Reimbursement r = new Reimbursement();
		
		ResultSet rs = null;
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1, id);
			
			ps.execute();
			
			rs = ps.getResultSet();
			
			while (rs.next()) {
				r.setId(rs.getInt("id"));
				r.setBatch(rs.getInt("batch"));
				r.setDateCreated(rs.getString("date_created"));
				r.setEmployee(rs.getInt("employee"));
				r.setCategoryById(rs.getInt("category"));
				r.setAmount(rs.getDouble("amount"));
				r.setStatusById(rs.getInt("status"));
				r.setApprovedBy(rs.getInt("approved_by"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
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
	public int updateReimbursement(Reimbursement r) {
		
		String sql = "update reimbursement set status = ? where id = ?";
		
		int didItWork = 0;
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1, r.getStatusId());
			ps.setInt(2, r.getId());
			
			didItWork = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return didItWork;
	}
	
	@Override
	public int approveReimbursement(Reimbursement r) {
		
		String sql = "update reimbursement set status = 4, approved_by = ? where id = ?";
		
		int didItWork = 0;
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1, r.getApprovedBy());
			ps.setInt(2, r.getId());
			
			didItWork = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return didItWork;
	}
	
	@Override
	public int denyReimbursement(Reimbursement r) {
		
		String sql = "update reimbursement set status = 5, approved_by = ? where id = ?";
		
		int didItWork = 0;
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1, r.getApprovedBy());
			ps.setInt(2, r.getId());
			
			didItWork = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return didItWork;
	}

	@Override
	public List<Reimbursement> getAllReimbursements() {
		
		String sql = "{call get_reimbursements()}";
		
		List<Reimbursement> remList = new ArrayList<>();
		
		ResultSet rs = null;
		
		try (Connection con = ConnectionUtil.getConnection();
				CallableStatement cs = con.prepareCall(sql);){
			
			cs.execute();
			
			rs = cs.getResultSet();
			
			while (rs.next()) {
				Reimbursement rem = new Reimbursement();
				
				rem.setId(rs.getInt("id"));
				rem.setBatch(rs.getInt("batch"));
				rem.setDateCreated(rs.getString("date_created"));
				rem.setEmployee(rs.getInt("employee"));
				rem.setCategoryById(rs.getInt("category"));
				rem.setAmount(rs.getDouble("amount"));
				rem.setStatusById(rs.getInt("status"));
				rem.setApprovedBy(rs.getInt("approved_by"));
				rem.setEmployeeName(rs.getString("emp_name"));
				rem.setApprovedByName(rs.getString("approved_by_name"));
				rem.setDiscussion(rs.getString("body"));
				
				remList.add(rem);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return remList;
	}
	
	@Override
	public List<Reimbursement> getAllReimbursementsWithId(Employee em) {
		
		String sql = "{call get_reimbursements_with_id(?)}";
		
		List<Reimbursement> remList = new ArrayList<>();
		
		ResultSet rs = null;
		
		try (Connection con = ConnectionUtil.getConnection();
				CallableStatement cs = con.prepareCall(sql);){
			
			cs.setInt(1, em.getId());
			cs.execute();
			
			rs = cs.getResultSet();
			
			while (rs.next()) {
				Reimbursement rem = new Reimbursement();
				
				rem.setId(rs.getInt("id"));
				rem.setBatch(rs.getInt("batch"));
				rem.setDateCreated(rs.getString("date_created"));
				rem.setEmployee(rs.getInt("employee"));
				rem.setCategoryById(rs.getInt("category"));
				rem.setAmount(rs.getDouble("amount"));
				rem.setStatusById(rs.getInt("status"));
				rem.setApprovedBy(rs.getInt("approved_by"));
				rem.setEmployeeName(rs.getString("emp_name"));
				rem.setApprovedByName(rs.getString("approved_by_name"));
				rem.setDiscussion(rs.getString("body"));
				
				remList.add(rem);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return remList;
	}
	
	
	
	
//	@Override
//	public List<Reimbursement> getAllReimbursements() {
//		
//		String sql = "select * from {oj reimbursement left outer join employee on (reimbursement.employee = employee.id)}";
//		
//		List<Reimbursement> remList = new ArrayList<>();
//		
//		try (Connection con = ConnectionUtil.getConnection();
//				Statement s = con.createStatement();
//				ResultSet rs = s.executeQuery(sql)){
//			
//			while (rs.next()) {
//				Reimbursement rem = new Reimbursement();
//				
//				rem.setId(rs.getInt("id"));
//				rem.setBatch(rs.getInt("batch"));
//				rem.setDateCreated(rs.getString("date_created"));
//				rem.setEmployee(rs.getInt("employee"));
//				rem.setCategoryById(rs.getInt("category"));
//				rem.setAmount(rs.getDouble("amount"));
//				rem.setStatusById(rs.getInt("status"));
//				rem.setApprovedBy(rs.getInt("approved_by"));
//				
//				remList.add(rem);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return remList;
//	}
	
	@Override
	public List<Reimbursement> getAllReimbursementsForEmployee(Employee em) {
		
		//String sql = "select * from reimbursement where employee = ?";
		
		String sql = "{call get_reimbursements_with_id_not_manager(?)}";
		
		List<Reimbursement> remList = new ArrayList<>();
		
		ResultSet rs = null;
		
		try (Connection con = ConnectionUtil.getConnection();
				CallableStatement cs = con.prepareCall(sql)){
			
			cs.setInt(1, em.getId());
			
			cs.execute();
			
			rs = cs.getResultSet();
			
			while (rs.next()) {
				Reimbursement rem = new Reimbursement();
				
				rem.setId(rs.getInt("id"));
				rem.setBatch(rs.getInt("batch"));
				rem.setDateCreated(rs.getString("date_created"));
				rem.setEmployee(rs.getInt("employee"));
				rem.setCategoryById(rs.getInt("category"));
				rem.setAmount(rs.getDouble("amount"));
				rem.setStatusById(rs.getInt("status"));
				rem.setDiscussion(rs.getString("body"));
				
				remList.add(rem);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return remList;
	}

	@Override
	public Discussion getDiscussionPost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Discussion> getDiscussion() {
		// TODO Auto-generated method stub
		return null;
	}

}
