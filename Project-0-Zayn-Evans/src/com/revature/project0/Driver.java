package com.revature.project0;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.project0.daos.CustomerAccountDaoImpl;
import com.revature.project0.models.CustomerAccount;
import com.revature.project0.services.CustomerAccountService;
import com.revature.project0.ui.Menu;
import com.revature.project0.util.ConnectionUtil;

public class Driver {
	
	public static void main(String[] args) {
		
//	try {
//		Connection c = ConnectionUtil.getConnection();
//		String driverName = c.getMetaData().getDriverName();
//		System.out.println(driverName);
//	} catch (SQLException e) {
//		e.printStackTrace();
//	}
		
		new Menu();
		
		
	}
	
}
