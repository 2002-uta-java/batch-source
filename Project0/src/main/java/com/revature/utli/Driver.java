package com.revature.utli;

import java.sql.Connection;
import java.sql.SQLException;

import MenuInteraction.MainMenu;
import userinteraction.UserInterfaceImpl;

public class Driver {

	public static void main(String[] args) {
	
		try {
		Connection c = ConnectionUtil.getConnection();
		String driverName = c.getMetaData().getDriverName();
		System.out.println(driverName);
	} catch (SQLException e) {
		e.printStackTrace();
	}		
	

//		MainMenu m = new MainMenu();
//		m.MainMenuInput();

	}	
}
