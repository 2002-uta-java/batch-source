package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


import com.revature.model.Reimbursement;
import com.revature.utli.ConnectionUtil;

public class ReimbursementDaoImpl implements ReimbursementDao{

	private static Logger log = Logger.getRootLogger();
	public ReimbursementDaoImpl() {
		
	}

	@Override
	public List<Reimbursement> getReimbursement() {
		String sql = "select * from reimbursement";
		
		List<Reimbursement> reimbursements = new ArrayList<>();
		
		try (Connection c = ConnectionUtil.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql)) {
		
		   while(rs.next()) {
			   
			   int id = rs.getInt("rem_id");
			   String type = rs.getString("rem_type");
			   double ramount = rs.getDouble("rem_requested_amount");
			   String rdate = rs.getString("rem_date_requested");
			   String reciept = rs.getString("rem_reciept_url");
			   String notes = rs.getString("rem_notes");
			   int eId = rs.getInt("empl_id");
			   int mId = rs.getInt("assigned_manager");
			   String adate = rs.getString("rem_date_approved");
			   double amount = rs.getDouble("rem_amount_approved");
			   String comment = rs.getString("rem_comment");
			   String status = rs.getString("rem_status");
			   
			   Reimbursement rl1 = new Reimbursement(id, type, ramount, rdate, reciept, notes, eId, mId, adate,amount, comment, status);
			   reimbursements.add(rl1);	   
		   }
			
		} catch (SQLException e) {
			log.error("Unable to pull client list", e);
		}
		
		return reimbursements;
	}

	@Override
	public List<Reimbursement> getReimbursementId(int id) {
		String sql = "select * from reimbursement where rem_id = ?";
		ResultSet rs = null;
		List<Reimbursement> reimbursements = new ArrayList<>();
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
		        ps.setInt(1, id);
				rs = ps.executeQuery();
			
				   while(rs.next()) {
					   
					   int rid = rs.getInt("rem_id");
					   String type = rs.getString("rem_type");
					   double ramount = rs.getDouble("rem_requested_amount");
					   String rdate = rs.getString("rem_date_requested");
					   String reciept = rs.getString("rem_reciept_url");
					   String notes = rs.getString("rem_notes");
					   int eId = rs.getInt("empl_id");
					   int mId = rs.getInt("assigned_manager");
					   String adate = rs.getString("rem_date_approved");
					   double amount = rs.getDouble("rem_amount_approved");
					   String comment = rs.getString("rem_comment");
					   String status = rs.getString("rem_status");
					   
					   Reimbursement rl1 = new Reimbursement(rid, type, ramount, rdate, reciept, notes, eId, mId, adate,amount, comment, status);
					   reimbursements.add(rl1);	
				}
		} catch (SQLException e) {
			log.error("Unable to get rem id" + e);
		} finally {
			try { if (rs!=null) {
				rs.close();
			}
			} catch (SQLException e) {
				log.error("Unable to close resource rem id search" + e);
			}
		}
		return reimbursements;
	}
	@Override
	public Reimbursement createReimbursementByFunction(Reimbursement r1) {
		String sql = "{call r_entry(?,?,?,?,?,?,?,?,?,?,?)}";
		ResultSet rs = null;
		
			try(Connection c = ConnectionUtil.getConnection(); 
				CallableStatement cs = c.prepareCall(sql);) {
				cs.setString(1, r1.getRemType());
				cs.setDouble(2, r1.getRemRequestedAmount());
				cs.setString(3, r1.getRemRequestDate());
				cs.setString(4, r1.getRemReciept());
				cs.setString(5, r1.getRemNotes());
				cs.setInt(6, r1.getEmployeeId());
				cs.setInt(7, r1.getManagerId());
				cs.setString(8, r1.getRemApprovedDate());
				cs.setDouble(9, r1.getRemApprovedAmount());
				cs.setString(10, r1.getRemComment());
				cs.setString(11, r1.getRemStatus());
				
				cs.execute();
				rs = cs.getResultSet();
				
				while(rs.next()) {
					int rId = rs.getInt("rem_id");
				
					r1.setRemId(rId);
				}
					
				} catch (SQLException e) {
					log.error("Unable to create a new employee" + e);
				} finally {
					try { if (rs!=null) {
						rs.close();
						}
					} catch (SQLException e) {
						log.error("Unable to close resource for create employee" + e);
					}
				}
		return r1;
	}


	@Override
	public Reimbursement updateReimbursementByFunction(Reimbursement r1) {
		String sql = "{call update_r(?,?,?,?,?)}";
		ResultSet rs = null;
		
			try(Connection c = ConnectionUtil.getConnection(); 
				CallableStatement cs = c.prepareCall(sql);) {
				cs.setString(1, r1.getRemType());
				cs.setString(8, r1.getRemApprovedDate());
				cs.setDouble(9, r1.getRemApprovedAmount());
				cs.setString(10, r1.getRemComment());
				cs.setString(11, r1.getRemStatus());
				
				cs.execute();
				rs = cs.getResultSet();
				
				while(rs.next()) {
					int rId = rs.getInt("rem_id");
				
					r1.setRemId(rId);
				}
					
				} catch (SQLException e) {
					log.error("Unable to create a new employee" + e);
				} finally {
					try { if (rs!=null) {
						rs.close();
						}
					} catch (SQLException e) {
						log.error("Unable to close resource for create employee" + e);
					}
				}
		return r1;
	}


	@Override
	public int deleteReimbursement(int id) {
			String sql = "delete from reimbursement where rem_id = ?)";
			ResultSet rs = null;
			
			
			try(Connection c = ConnectionUtil.getConnection();
					PreparedStatement ps = c.prepareStatement(sql);){
			        ps.setInt(1, id);
					rs = ps.executeQuery();
				
			} catch (SQLException e) {
				log.error("Unable to delete rem_id" + e);
			} finally {
				try { if (rs!=null) {
					rs.close();
				}
				} catch (SQLException e) {
					log.error("Unable to close resource rem delete" + e);
				}
			}
			return 1;
		}

	@Override
	public Reimbursement generateReportReimbursementByEmployee(Reimbursement r1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reimbursement generateReportReimbursementByManager(Reimbursement r1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStatus(int id) {
			String sql = "select rem_status from reimbursement where rem_id = ?";
			ResultSet rs = null;
			String r2 = null;
			
			try(Connection c = ConnectionUtil.getConnection();
					PreparedStatement ps = c.prepareStatement(sql);){
			        ps.setInt(1, id);
					rs = ps.executeQuery();
				
					while(rs.next()) {
						String rId = rs.getString("rem_status");
						Reimbursement r1 = new Reimbursement();
						r2 = r1.setRemStatus(rId); 
					}
			} catch (SQLException e) {
				log.error("Unable to get rem status" + e);
			} finally {
				try { if (rs!=null) {
					rs.close();
				}
				} catch (SQLException e) {
					log.error("Unable to close resource rem status search" + e);
				}
			}
			return r2;
		}

	@Override
	public String getReciept(int id) {
		String sql = "select rem_reciept from reimbursement where rem_id = ?)";
		ResultSet rs = null;
		String r2 = null;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
		        ps.setInt(1, id);
				rs = ps.executeQuery();
			
				while(rs.next()) {
					String rId = rs.getString("rem_reciept");
					Reimbursement r1 = new Reimbursement();
					r2 = r1.setRemReciept(rId); 
				}
		} catch (SQLException e) {
			log.error("Unable to get rem reciept" + e);
		} finally {
			try { if (rs!=null) {
				rs.close();
			}
			} catch (SQLException e) {
				log.error("Unable to close resource rem reciept search" + e);
			}
		}
		return r2;
	}

	@Override
	public String updateReciept(String url, int id) {
		String sql = "update reimbursement set rem_reciept = ? where rem_id = ?)";
		ResultSet rs = null;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
		        ps.setString(1, url);
		        ps.setInt(2, id);
				rs = ps.executeQuery();
			
		} catch (SQLException e) {
			log.error("Unable to get rem reciept" + e);
		} finally {
			try { if (rs!=null) {
				rs.close();
			}
			} catch (SQLException e) {
				log.error("Unable to close resource rem reciept search" + e);
			}
		}
		return "complete";
	}

	public List<Reimbursement> managerStatus(int id) {
		String sql = "select * from reimbursement where assigned_manager = ?";
		ResultSet rs = null;
		List<Reimbursement> reimbursements = new ArrayList<>();
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
		        ps.setInt(1, id);
				rs = ps.executeQuery();
			
				   while(rs.next()) {
					   
					   int rid = rs.getInt("rem_id");
					   String type = rs.getString("rem_type");
					   double ramount = rs.getDouble("rem_requested_amount");
					   String rdate = rs.getString("rem_date_requested");
					   String reciept = rs.getString("rem_reciept_url");
					   String notes = rs.getString("rem_notes");
					   int eId = rs.getInt("empl_id");
					   int mId = rs.getInt("assigned_manager");
					   String adate = rs.getString("rem_date_approved");
					   double amount = rs.getDouble("rem_amount_approved");
					   String comment = rs.getString("rem_comment");
					   String status = rs.getString("rem_status");
					   
					   Reimbursement rl1 = new Reimbursement(rid, type, ramount, rdate, reciept, notes, eId, mId, adate,amount, comment, status);
					   reimbursements.add(rl1);	
				}
		} catch (SQLException e) {
			log.error("Unable to get rem id" + e);
		} finally {
			try { if (rs!=null) {
				rs.close();
			}
			} catch (SQLException e) {
				log.error("Unable to close resource rem id search" + e);
			}
		}
		return reimbursements;
	}
	public List<Reimbursement> employeeStatus(int id) {
		String sql = "select * from reimbursement where empl_id = ?";
		ResultSet rs = null;
		List<Reimbursement> reimbursements = new ArrayList<>();
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
		        ps.setInt(1, id);
				rs = ps.executeQuery();
			
				   while(rs.next()) {
					   
					   int rid = rs.getInt("rem_id");
					   String type = rs.getString("rem_type");
					   double ramount = rs.getDouble("rem_requested_amount");
					   String rdate = rs.getString("rem_date_requested");
					   String reciept = rs.getString("rem_reciept_url");
					   String notes = rs.getString("rem_notes");
					   int eId = rs.getInt("empl_id");
					   int mId = rs.getInt("assigned_manager");
					   String adate = rs.getString("rem_date_approved");
					   double amount = rs.getDouble("rem_amount_approved");
					   String comment = rs.getString("rem_comment");
					   String status = rs.getString("rem_status");
					   
					   Reimbursement rl1 = new Reimbursement(rid, type, ramount, rdate, reciept, notes, eId, mId, adate,amount, comment, status);
					   reimbursements.add(rl1);	
				}
		} catch (SQLException e) {
			log.error("Unable to get rem id" + e);
		} finally {
			try { if (rs!=null) {
				rs.close();
			}
			} catch (SQLException e) {
				log.error("Unable to close resource rem id search" + e);
			}
		}
		return reimbursements;
	}
}
