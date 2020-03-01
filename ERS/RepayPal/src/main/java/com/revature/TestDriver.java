package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.model.User;
import com.revature.service.ReimbursementService;
import com.revature.util.ConnectionUtil;

public class TestDriver {

	public static void main(String[] args) {
		try {
			Connection c = ConnectionUtil.getConnection();
			System.out.println(c.getMetaData().getDriverName());
			User u = new User("ingarcia", "Israel", "Garcia", "mpwd", false);
			ReimbursementService rs = new ReimbursementService();
			System.out.println(rs.getReimbursement(u.getUsername()));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
