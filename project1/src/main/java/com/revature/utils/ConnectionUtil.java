package com.revature.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class ConnectionUtil {

	public static Connection getConnection() throws ServletException, SQLException {
		InitialContext cxt = null;
		try {
			cxt = new InitialContext();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (cxt == null) {
			throw new ServletException("Uh oh -- no context!");
		}

		BasicDataSource ds = null;
		// org.apache.tomcat.dbcp.dbcp.BasicDataSource bds;
		try {
			ds = (BasicDataSource) cxt.lookup("java:/comp/env/jdbc/postgres");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (ds == null) {
			throw new ServletException("Data source not found!");
		}
		return ds.getConnection();
	}

}
