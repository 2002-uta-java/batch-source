package com.revature.daos;

import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Request;
import com.revature.models.Account;
import com.revature.util.ConnectionUtil;

public class RequestDaoImpl implements RequestDao {

	private String reqTable = "reimburse.request";
	private Logger log = Logger.getRootLogger();
	
	@Override
	public List<Request> getAllRequests() {
		
		String sql = "select * from " + reqTable;
		List<Request> requests = new ArrayList<Request>();
		
		try (Connection c = ConnectionUtil.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql);) {
			
			while(rs.next()) {
				
				Request r = new Request();
				
				r.setId(rs.getInt("id"));
				r.setAmount(rs.getDouble("amount"));
				r.setDescription(rs.getString("description"));
				r.setEmplId(rs.getInt("empl_id"));
				r.setImageUrl(rs.getString("image_url"));
				r.setReimburseDate(((Date)rs.getObject("reimbursement_date")).toLocalDate());
				r.setSubmitted(((Timestamp)rs.getObject("date_submitted")).toLocalDateTime());
				if (rs.getObject("date_reviewed") != null) {
					r.setReviewed(((Timestamp)rs.getObject("date_reviewed")).toLocalDateTime());					
				}
				r.setStatus(rs.getString("status"));
				r.setCategory(rs.getString("category"));
				
				requests.add(r);
				
			}
			
		} catch (SQLException e) {
			log.error(e);
		}
		
		return requests;
	}

	@Override
	public List<Request> getAllRequests(int offset, int limit) {
		
		String sql = "select * from " + reqTable + " order by id offset ? limit ?";
		ResultSet rs = null;
		List<Request> requests = new ArrayList<Request>();
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			
			ps.setInt(1, offset);
			ps.setInt(2, limit);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Request r = new Request();
				
				r.setId(rs.getInt("id"));
				r.setAmount(rs.getDouble("amount"));
				r.setDescription(rs.getString("description"));
				r.setEmplId(rs.getInt("empl_id"));
				r.setImageUrl(rs.getString("image_url"));
				r.setReimburseDate(((Date)rs.getObject("reimbursement_date")).toLocalDate());
				r.setSubmitted(((Timestamp)rs.getObject("date_submitted")).toLocalDateTime());
				if (rs.getObject("date_reviewed") != null) {
					r.setReviewed(((Timestamp)rs.getObject("date_reviewed")).toLocalDateTime());					
				}
				r.setStatus(rs.getString("status"));
				r.setCategory(rs.getString("category"));
				
				requests.add(r);
				
			}
			
		} catch (SQLException e) {
			log.error(e);
		} finally {
			try { if(rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
		return requests;
	}

	@Override
	public List<Request> getManagerRequests(int mngId) {
		String sql = "select * from " + reqTable + " r join reimburse.account a on r.empl_id = a.id where manager_id=? order by r.date_submitted desc";
		ResultSet rs = null;
		List<Request> requests = new ArrayList<Request>();
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			
			ps.setInt(1, mngId);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Request r = new Request();
				
				r.setId(rs.getInt("id"));
				r.setAmount(rs.getDouble("amount"));
				r.setDescription(rs.getString("description"));
				r.setEmplId(rs.getInt("empl_id"));
				r.setImageUrl(rs.getString("image_url"));
				r.setReimburseDate(((Date)rs.getObject("reimbursement_date")).toLocalDate());
				r.setSubmitted(((Timestamp)rs.getObject("date_submitted")).toLocalDateTime());
				if (rs.getObject("date_reviewed") != null) {
					r.setReviewed(((Timestamp)rs.getObject("date_reviewed")).toLocalDateTime());					
				}
				r.setStatus(rs.getString("status"));
				r.setCategory(rs.getString("category"));
				
				Account a = new Account();
				a.setId(rs.getInt("empl_id"));
				a.setName(rs.getString("name"));
				a.setEmail(rs.getString("email"));
				a.setAcctType(rs.getString("account_type"));
				a.setManagerId(rs.getInt("manager_id"));
				
				r.setEmplAccount(a);
				
				requests.add(r);
			}
			
		} catch (SQLException e) {
			log.error(e);
		} finally {
			try { if(rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
		return requests;
	}
	
	@Override
	public List<Request> getEmployeeRequests(int empl_id) {
		String sql = "select * from " + reqTable + " where empl_id=? order by id";
		ResultSet rs = null;
		List<Request> requests = new ArrayList<Request>();
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			
			ps.setInt(1, empl_id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Request r = new Request();
				
				r.setId(rs.getInt("id"));
				r.setAmount(rs.getDouble("amount"));
				r.setDescription(rs.getString("description"));
				r.setEmplId(rs.getInt("empl_id"));
				r.setImageUrl(rs.getString("image_url"));
				r.setReimburseDate(((Date)rs.getObject("reimbursement_date")).toLocalDate());
				r.setSubmitted(((Timestamp)rs.getObject("date_submitted")).toLocalDateTime());
				if (rs.getObject("date_reviewed") != null) {
					r.setReviewed(((Timestamp)rs.getObject("date_reviewed")).toLocalDateTime());					
				}
				r.setStatus(rs.getString("status"));
				r.setCategory(rs.getString("category"));
				
				requests.add(r);
				
			}
			
		} catch (SQLException e) {
			log.error(e);
		} finally {
			try { if(rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
		return requests;
	}

	@Override
	public Request getRequestById(int id) {
		String sql = "select * from " + reqTable + " where id=?";
		ResultSet rs = null;
		Request r = null;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				r = new Request();
				
				r.setId(rs.getInt("id"));
				r.setAmount(rs.getDouble("amount"));
				r.setDescription(rs.getString("description"));
				r.setEmplId(rs.getInt("empl_id"));
				r.setImageUrl(rs.getString("image_url"));
				r.setReimburseDate(((Date)rs.getObject("reimbursement_date")).toLocalDate());
				r.setSubmitted(((Timestamp)rs.getObject("date_submitted")).toLocalDateTime());
				if (rs.getObject("date_reviewed") != null) {
					r.setReviewed(((Timestamp)rs.getObject("date_reviewed")).toLocalDateTime());					
				}
				r.setStatus(rs.getString("status"));
				r.setCategory(rs.getString("category"));
				
			}
			
		} catch (SQLException e) {
			log.error(e);
		} finally {
			try { if(rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
		return r;
	}

	@Override
	public Request createRequest(Request r) {
		
		String sql = "insert into " + reqTable + " (amount, description, empl_id, image_url, reimbursement_date, date_submitted, status, category) values (?, ?, ?, ?, ?, now(), ?, ?) returning id";
		ResultSet rs = null;
		Request newR = r;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			
			ps.setDouble(1, r.getAmount());
			ps.setString(2, r.getDescription());
			ps.setInt(3, r.getEmplId());
			ps.setString(4, r.getImageUrl());
			ps.setDate(5, Date.valueOf(r.getReimburseDate()));
			ps.setString(6, String.valueOf(r.getStatus()));
			ps.setString(7, String.valueOf(r.getCategory()));
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				newR.setId(rs.getInt(1));
			}
			
			return newR;
			
		} catch (SQLException e) { 
			log.error(e);
		} finally {
			try { if(rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
		return null;
	}

	@Override
	public int updateRequest(Request r) {
		
		String sql = "update " + reqTable + " set amount=?, description=?, empl_id=?, image_url=?, reimbursement_date=?, date_submitted=?, date_reviewed=?, status=?, category=? where id=?";
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			
			ps.setDouble(1, r.getAmount());
			ps.setString(2, r.getDescription());
			ps.setInt(3, r.getEmplId());
			ps.setString(4, r.getImageUrl());
			ps.setObject(5, Date.valueOf(r.getReimburseDate()));
			ps.setObject(6, Timestamp.valueOf(r.getSubmitted()));
			ps.setObject(7, Timestamp.valueOf(r.getReviewed()));
			ps.setString(8, String.valueOf(r.getStatus()));
			ps.setString(9, String.valueOf(r.getCategory()));
			ps.setInt(10, r.getId());
			
			return ps.executeUpdate();
			
		} catch (SQLException e) { 
			log.error(e);
		}
		
		return 0;
	}

	@Override
	public int deleteRequest(Request r) {
		String sql = "delete from " + reqTable + " where id=?";
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			
			ps.setInt(1, r.getId());
			
			return ps.executeUpdate();
			
		} catch (SQLException e) {
			log.error(e);
		}
		
		return 0;
	}
	
}
