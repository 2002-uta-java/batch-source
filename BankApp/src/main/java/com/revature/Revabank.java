package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.util.ConnectionUtil;

public class Revabank {
//	public int sum(int... i) {
//		int sum = 0;
//		for(int j:i) {
//			sum+=j;
//		}
//		return sum;
//	}
	
	public static void main(String[] args) {
		try {
			Connection c = ConnectionUtil.getHardCodedConnection();
			String driver = c.getMetaData().getDriverName();
			System.out.println(driver);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
