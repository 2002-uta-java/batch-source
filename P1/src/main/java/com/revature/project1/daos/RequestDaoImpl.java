package com.revature.project1.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.project1.models.Request;
import com.revature.project1.util.ConnectionUtil;

public class RequestDaoImpl implements RequestDao {

	@Override
	public List<Request> getRequests() {

		String sql = "select * from request;";
		List<Request> requests = new ArrayList<>();

		try (Connection c = ConnectionUtil.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql)) {

			while (rs.next()) {
				Request r = new Request();
				r.setId(rs.getInt("req_id"));
				r.setUserId(rs.getInt("user_id"));
				r.setAmount(rs.getString("req_amount"));
				r.setReason(rs.getString("req_reason"));
				r.setStatus(rs.getInt("req_status"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return requests;
	}

	@Override
	public Request getRequestByStatus(int status) {
		String sql = "select * from request where req_status = ?;";
		ResultSet rs = null;
		Request r = null;

		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, status);
			rs = ps.executeQuery();

			while (rs.next()) {
				r = new Request();
				r.setId(rs.getInt("req_id"));
				r.setUserId(rs.getInt("user_id"));
				r.setAmount(rs.getString("req_amount"));
				r.setReason(rs.getString("req_reason"));
				r.setStatus(rs.getInt("req_status"));
			}

		} catch (SQLException e) {
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
	public List<Request> getRequestByUserId(int userId) {

		String sql = "select * from request where user_id = ?;";
		ResultSet rs = null;
		List<Request> requests = new ArrayList<>();

		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, userId);
			rs = ps.executeQuery();

			while (rs.next()) {
				Request r = new Request();
				r.setId(rs.getInt("req_id"));
				r.setUserId(rs.getInt("user_id"));
				r.setAmount(rs.getString("req_amount"));
				r.setReason(rs.getString("req_reason"));
				r.setStatus(rs.getInt("req_status"));
			}

		} catch (SQLException e) {
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
		return requests;

	}

	@Override
	public int updateRequest(Request r) {

		String sql = "update request; set req_id = ?, user_id = ?, req_amount = ?, req_reason = ? req_status = ? where req_id = ?;";
		int check = 0;

		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, r.getId());
			ps.setInt(2, r.getUserId());
			ps.setString(3, r.getAmount());
			ps.setString(4, r.getReason());
			ps.setInt(5, r.getStatus());
			ps.setInt(6, r.getId());

			check = ps.executeUpdate();
			System.out.println("updated request");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("check is" + check);
		return check;
	}


}
