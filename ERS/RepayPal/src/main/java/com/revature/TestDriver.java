package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.service.ReimbursementService;
import com.revature.util.ConnectionUtil;

public class TestDriver {

	public static void main(String[] args) {
		try {
			Connection c = ConnectionUtil.getConnection();
			System.out.println(c.getMetaData().getDriverName());
//			User u = new User("ing", "Israel", "Garcia", "mpwd", false);
//			UserService us = new UserService();
//			System.out.println(us.createUser(u));
//			System.out.println(us.validateUser(u.getUsername(), u.getPassword()));
//			System.out.println(us.getUsers());
//			
			ReimbursementService rs = new ReimbursementService();
			System.out.println(rs.getReimbursements());
			System.out.println(rs.getReimbursement("ingarcia"));
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
