package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.revature.models.Request;
import com.revature.util.ConnectionUtil;

public class RequestDaoImpl implements RequestDao{

	@Override
	public Request getRequestById(int id) {
		String sql = "select * from \"REQUEST\" where \"request_id\" = ?";
		Request r = new Request();
		
		try (Connection c = ConnectionUtil.getHardConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery(sql);

			if (rs.next()) {
				r.setEmployeeName(rs.getString("employee_name"));
				r.setStatus(rs.getBoolean("status"));
				r.setAprovedBy(rs.getString("aproved_by"));
				r.setId(rs.getInt("request_id"));
				r.setName(rs.getString("name"));

				return r;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Request> getAllRequets() {
		String sql = "select * from \"REQUEST\"";
		List<Request> re = new ArrayList<Request>();
		
		try (Connection c = ConnectionUtil.getHardConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql)) {
			
			
			if (rs.next()) {
				String empName = rs.getString("employee_name");
				boolean status = rs.getBoolean("status");
				String aprovedBy = rs.getString("aproved_by");
				int id = rs.getInt("request_id");
				String name = rs.getString("name");
				Request r = new Request(empName, status, aprovedBy, id, name);
				re.add(r);
				

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return re;
	}

	@Override
	public List<Request> getRequestByEmployeeName(String empName) {
		String sql = "select * from \"REQUEST\" where \"employee_Name\" = ?";
		List<Request> re = new ArrayList<Request>();
		
		try (Connection c = ConnectionUtil.getHardConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

			ps.setString(1, empName);
			ResultSet rs = ps.executeQuery(sql);

			if (rs.next()) {
				String emName = rs.getString("employee_name");
				boolean status = rs.getBoolean("status");
				String aprovedBy = rs.getString("aproved_by");
				int requestId = rs.getInt("request_id");
				String name = rs.getString("name");

				Request r = new Request(emName, status, aprovedBy, requestId, name);
				re.add(r);
				

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return re;
	}
/**
 * getting our reemburstment requests by Status. status is either true or false
 */
	@Override
	public List<Request> getRequestByStatus(boolean status) {
		String sql = "select * from \"REQUEST\" where \"status\" = ?";
		List<Request> re = new ArrayList<Request>();
		
		try (Connection c = ConnectionUtil.getHardConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

			ps.setString(1, Boolean.toString(status));
			ResultSet rs = ps.executeQuery(sql);

			if (rs.next()) {
				String emName = rs.getString("employee_name");
				boolean status1 = rs.getBoolean("status");
				String aprovedBy = rs.getString("aproved_by");
				int requestId = rs.getInt("request_id");
				String name = rs.getString("name");

				Request r = new Request(emName, status1, aprovedBy, requestId, name);
				re.add(r);
				

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return re;
		
	}

	@Override
	public List<Request> getRequestByStatusAndEmployeeName(boolean status, String empName) {
		String sql = "select * from \"REQUEST\" where \"status\" = ? and \"employee_name\" = ?";
		List<Request> re = new ArrayList<Request>();
		
		try (Connection c = ConnectionUtil.getHardConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

			ps.setString(1, Boolean.toString(status));
			ps.setString(2, empName);
			ResultSet rs = ps.executeQuery(sql);

			if (rs.next()) {
				String emName = rs.getString("employee_name");
				boolean status1 = rs.getBoolean("status");
				String aprovedBy = rs.getString("aproved_by");
				int requestId = rs.getInt("request_id");
				String name = rs.getString("name");

				Request r = new Request(emName, status1, aprovedBy, requestId, name);
				re.add(r);
				

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return re;
		
		
	}
/**
 * changing the status of our request and adding the name of person who aproved it.
 * 
 */
	@Override
	public int updateRequest(boolean status, String aprovedBy, int reqNum) {
		String sql = "update \"REQUEST\" set \"status\" = ?, \"aproved_by\" = ? where \"request_id\" = ?";
		int updated = 0;
		
		try(Connection c = ConnectionUtil.getHardConnection();
					PreparedStatement ps = c.prepareStatement(sql)) {
			
			ps.setBoolean(1, status);
			ps.setString(2, aprovedBy);
			ps.setInt(3, reqNum);
			updated = ps.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return updated	;
	}
/**
 * adding a new request to our table
 */
	@Override
	public int makeRequest(Request request) {
		String sql = "insert into \"REQUEST\" (\"employee_name\", \"status\", \"aproved_by\", \"name\") values (?, ?, ?, ?)";
		int requestCreated = 0;
		
		try(Connection c = ConnectionUtil.getHardConnection();
					PreparedStatement ps = c.prepareStatement(sql)) {
			
			ps.setString(1, request.getEmployeeName());
			ps.setBoolean(2, request.isStatus());
			ps.setString(3, request.getAprovedBy());
			ps.setString(4, request.getName());
			
			
			requestCreated = ps.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return requestCreated;
	}
	

}
