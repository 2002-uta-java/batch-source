package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.util.ConnectionUtil;

public class ReimbursementDaoImpl implements ReimbursementDao{

	private static Logger log = Logger.getRootLogger();
	private EmployeeDao ed = new EmployeeDaoImpl();
	
	@Override
	public List<Reimbursement> getAllReimbursements(String status) {
		String sql;
		if("pending".contentEquals(status)) {
			sql = "select * from {oj reimbursement left outer join employee on (reimbursement.eid = employee.eid)} where status = ?";			
		} else {
			sql = "select * from {oj reimbursement left outer join employee on (reimbursement.eid = employee.eid)} where status = ? or status = ?";			
		}
		List<Reimbursement> reimbursements = new ArrayList<>();
		ResultSet rs = null;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			if("pending".equals(status)) {
				ps.setString(1, status);
			} else {
				ps.setString(1, "approved");
				ps.setString(2, "denied");
			}
			rs = ps.executeQuery();				
			while(rs.next()) {
				Reimbursement r = new Reimbursement();
				r.setRid(rs.getInt("rid"));
				Employee e = new Employee();
				e.setEid(rs.getInt("eid"));
				e.setLogin(rs.getString("login"));
				e.setPassword(rs.getString("pw"));
				e.setFirstName(rs.getString("first_name"));
				e.setLastName(rs.getString("last_name"));
				e.setTitle(rs.getString("title"));
				e.setEmail(rs.getString("email"));
				r.setE(e);
				r.setStatus(rs.getString("status"));
				r.setExpense(rs.getDouble("expense"));
				int rid = rs.getInt("resolver");
				if(rid != 0) {
					Employee resolver = ed.getEmployeeById(rid);					
					r.setResolver(resolver.getFirstName() + " " + resolver.getLastName());
				} else {
					r.setResolver("");
				}
				r.setRtype(rs.getString("rtype"));
				reimbursements.add(r);
			}
		} catch (SQLException e) {
			log.error("SQLException when getting all Reimbursements\n");
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.error("ResultSet couldn't close after getting all reimbursements by their status\n");
				}
			}
		}
		return reimbursements;
	}

	@Override
	public List<Reimbursement> getAllReimbursements(Employee e) {
		String sql = "select * from {oj reimbursement left outer join employee on (reimbursement.eid = employee.eid)} where reimbursement.eid = ?";
		List<Reimbursement> reimbursements = new ArrayList<>();
		ResultSet rs = null;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, e.getEid());
			rs = ps.executeQuery();
			while(rs.next()) {
				Reimbursement r = new Reimbursement();
				r.setRid(rs.getInt("rid"));
				r.setE(e);
				r.setStatus(rs.getString("status"));
				r.setExpense(rs.getDouble("expense"));
				int rid = rs.getInt("resolver");
				if(rid != 0) {
					Employee resolver = ed.getEmployeeById(rid);					
					r.setResolver(resolver.getFirstName() + " " + resolver.getLastName());
				} else {
					r.setResolver("");
				}
				r.setRtype(rs.getString("rtype"));
				reimbursements.add(r);
			}
		} catch (SQLException e1) {
			log.error("SQLException when getting all Reimbursements\n");
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					log.error("ResultSet couldn't close after getting all Reimbursements by their Employee\n");
				}
			}
		}
		return reimbursements;
	}

	@Override
	public List<Reimbursement> getAllReimbursements(Employee e, String status) {
		String sql;
		if("pending".contentEquals(status)) {
			sql = "select * from {oj reimbursement left outer join employee on (reimbursement.eid = employee.eid)} where reimbursement.eid = ? and status = ?";		
		} else {
			sql = "select * from {oj reimbursement left outer join employee on (reimbursement.eid = employee.eid)} where reimbursement.eid = ? and (status = ? or status = ?)";			
		}
		List<Reimbursement> reimbursements = new ArrayList<>();
		ResultSet rs = null;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, e.getEid());
			if("pending".equals(status)) {
				ps.setString(2, status);
			} else {
				ps.setString(2, "approved");
				ps.setString(3, "denied");
			}
			rs = ps.executeQuery();
			while(rs.next()) {
				Reimbursement r = new Reimbursement();
				r.setRid(rs.getInt("rid"));
				r.setE(e);
				r.setStatus(rs.getString("status"));
				r.setExpense(rs.getDouble("expense"));
				int rid = rs.getInt("resolver");
				if(rid != 0) {
					Employee resolver = ed.getEmployeeById(rid);					
					r.setResolver(resolver.getFirstName() + " " + resolver.getLastName());
				} else {
					r.setResolver("");
				}
				r.setRtype(rs.getString("rtype"));
				reimbursements.add(r);
			}
		} catch (SQLException e1) {
			log.error("SQLException when getting all Reimbursements\n");
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					log.error("ResultSet couldn't close after getting all Reimbursements by their Employee and status\n");
				}
			}
		}
		return reimbursements;
	}

	@Override
	public int createReimbursement(Employee e, double expense, String rtype) {
		String sql = "insert into reimbursement(eid, expense, rtype) values(?, ?, ?)";
		int reimbursementsCreated = 0;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, e.getEid());
			ps.setDouble(2, expense);
			ps.setString(3, rtype);
			reimbursementsCreated = ps.executeUpdate();
		} catch (SQLException e1) {
			log.error("SQLException when creating a Reimbursement\n");
		}
		return reimbursementsCreated;
	}

	@Override
	public Reimbursement getReimbursementById(int rid) {
		String sql = "select * from {oj reimbursement left outer join employee on (reimbursement.eid = employee.eid)} where rid = ?";
		Reimbursement r = null;
		ResultSet rs = null;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, rid);
			rs = ps.executeQuery();
			while(rs.next()) {
				r = new Reimbursement();
				r.setRid(rs.getInt("rid"));
				Employee e = new Employee();
				e.setEid(rs.getInt("eid"));
				e.setLogin(rs.getString("login"));
				e.setPassword(rs.getString("pw"));
				e.setTitle(rs.getString("title"));
				e.setFirstName(rs.getString("first_name"));
				e.setLastName(rs.getString("last_name"));
				e.setEmail(rs.getString("email"));
				r.setE(e);
				r.setStatus(rs.getString("status"));
				r.setExpense(rs.getDouble("expense"));
				int resEid = rs.getInt("resolver");
				if(rid != 0) {
					Employee resolver = ed.getEmployeeById(resEid);					
					r.setResolver(resolver.getFirstName() + " " + resolver.getLastName());
				} else {
					r.setResolver("");
				}
				r.setRtype(rs.getString("rtype"));
			}
		} catch (SQLException e1) {
			log.error("SQLException when getting a Reimbursement\n");
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException e1) {
				log.error("ResultSet couldn't close after getting Reimbursement by its rid\n");
			}
		}
		return r;
	}

	@Override
	public int updateReimbursement(int rid, String resolution, int eid) {
		String sql = "update reimbursement set status = ?, resolver = ? where rid = ?";
		int reimbursementUpdated = 0;
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, resolution);
			ps.setInt(2, eid);
			ps.setInt(3, rid);
			reimbursementUpdated = ps.executeUpdate();
		} catch (SQLException e1) {
			log.error("SQLException when updating an Reimbursement\n");
		}
		return reimbursementUpdated;
	}

	@Override
	public int deleteReimbursement(Reimbursement r) {
		// TODO Auto-generated method stub
		return 0;
	}



}
