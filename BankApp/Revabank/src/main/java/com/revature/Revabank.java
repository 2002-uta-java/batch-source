package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.util.ConnectionUtil;

public class Revabank {

	public static void main(String[] args) {
		System.out.println("Test");
		try (Connection c = ConnectionUtil.getConnection()){
			System.out.println(c.getClientInfo());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
