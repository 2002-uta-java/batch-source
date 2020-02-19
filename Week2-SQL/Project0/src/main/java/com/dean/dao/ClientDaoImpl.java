package com.dean.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.dean.models.Client;
import com.dean.util.ConnectionUtil;

public class ClientDaoImpl implements ClientDao {

	static final Logger logger = Logger.getRootLogger();
	
	@Override
	public Client getClientById(int id) {
		
		Client client = null;
		ResultSet rs = null;
		
		String sql = "select * from client where client_id = ?";
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			String username;
			String userPassword;
			while(rs.next()) {
				id = rs.getInt("client_id");
				username = rs.getString("username");
				userPassword = rs.getString("password");
				client = new Client(id, username, userPassword);
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		} finally {
			if( rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.info(e.getMessage());
				}
			}
		}
		
		return client;
	}
	
	// check if a client has an account
	public boolean clientHasAccount(String name) {
		boolean hasAccount = false;
		Client client = getClientByUsername(name);
		ResultSet rs = null;
		String sql = "select * from account where client_id = ?";
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setInt(1, client.getId());
			rs = ps.executeQuery();
			
			if(rs.isBeforeFirst()) {
				hasAccount = true;
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.info(e.getMessage());
				}
			}
		}
		
		return hasAccount;
	}
	
	// get client ID only by their user name
	public int getUserIdByName(String username) {
		ResultSet rs = null;
		int id = 0;
		String sql = "select * from client where username = ?";
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, username);
			rs = ps.executeQuery();

			while(rs.next()) {
				id = rs.getInt("client_id");
			}
			
			
		} catch (SQLException e) {
			
			logger.info(e.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.info(e.getMessage());
				}
			}
		}
		
		return id;
	}

	// get entire client object from user name
	@Override
	public Client getClientByUsername(String username) {
		ResultSet rs = null;
		Client client = null;
		String sql = "select * from client where username = ?";
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			int id = 0;
			String uname;
			String userPassword;
			while(rs.next()) {
				id = rs.getInt("client_id");
				username = rs.getString("username");
				userPassword = rs.getString("password");
				client = new Client(id, username, userPassword);
			}
			
			
		} catch (SQLException e) {
			
			logger.info(e.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.info(e.getMessage());
				}
			}
		}
		
		return client;
	}

	@Override
	public int createClient(Client client) {
		int clientsCreated = 0;
		String sql = "insert into client (username, password) values (?,?)";
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, client.getUsername());
			ps.setString(2, client.getPassword());
			clientsCreated = ps.executeUpdate();
		} catch (SQLException e) {
			
			logger.info(e.getMessage());
		}
		
		if(clientsCreated > 0) {
			logger.info("Your account was created successfully! \n");
		} else {
			logger.info("An account could not be created. Try again. \n");
		}
		
		return clientsCreated;
	}

	@Override
	public boolean isUsernameUnique(Client client) {
		boolean isUnique = false;
		String username = client.getUsername();
		String sql = "select * from client where username = ?";
		ResultSet rs = null;
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			if(!rs.isBeforeFirst()) {
				isUnique = true;
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.info(e.getMessage());
					
				}
			}
		}
		
		
		return isUnique;
	}

}
