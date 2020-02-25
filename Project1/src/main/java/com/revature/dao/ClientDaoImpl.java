package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.model.Client;
import com.revature.utli.ConnectionUtil;

public class ClientDaoImpl implements ClientDao {

	private static Logger log = Logger.getRootLogger();
	
	
	public ClientDaoImpl() {
		super();
	}

	@Override
	public List<Client> getClient() {
		String sql = "select * from client";
		
		List<Client> clients = new ArrayList<>();
		
		try (Connection c = ConnectionUtil.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql)) {
		
		   while(rs.next()) {
			   int clientId = rs.getInt("client_id");
			   String clientEmail = rs.getNString("client_email");
			   String clientPassword = rs.getNString("client_password");
			   int clientPermission = rs.getInt("client_permission");
			   Client cl1 = new Client(clientId, clientEmail, clientPassword, clientPermission);
			   clients.add(cl1);	   
		   }
			
		} catch (SQLException e) {
			log.error("Unable to pull client list", e);
		}
		
		return (List<Client>) clients;
	}


	@Override
	public int getClientId(String email) {
		String sql = "select client_id from client where client_email = ?";
		ResultSet rs = null; 
		int cId = 0;
		
		try (Connection c = ConnectionUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setNString(1, email);
			rs = ps.executeQuery(sql); 
				
				while (rs.next());
				int id = rs.getInt("client_id");
				Client cl1 = new Client();
				cId = cl1.setClientId(id);				
					
			} catch (SQLException e) {
				log.error("Unable to retrieve client id" + e);
			}
			finally {
				try { if (rs!=null) {
					rs.close();
				}
				} catch (SQLException e) {
					log.error("Unable to close resource client id search" + e);
				}
	}

		
		return cId;
	}

	@Override
	public String getClientEmail(int id) {
		String sql = "select client_email from client where client_id = ?";
		ResultSet rs = null;
		String email = null;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
				ps.setInt(1, id);
				rs = ps.executeQuery(sql);
				
				while(rs.next()) {
					String userEmail = rs.getString("client_email");
					Client clientEmail = new Client();
					email = clientEmail.setClientEmail(userEmail);
					
				}
					
				} catch (SQLException e) {
					log.error("Unable to get back client email" + e);
				} finally {
					try { if (rs!=null) {
						rs.close();
						}
					} catch (SQLException e) {
						log.error("Unable to close resource for client email" + e);
					}
				}

		return email;
	}

	@Override
	public String getClientPassword(int id) {
		String sql = "select client_password from client where client_id = ?";
		ResultSet rs = null;
		String password = null;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
				ps.setInt(1, id);
				rs = ps.executeQuery(sql);
				
				while(rs.next()) {
					String uPass = rs.getString("client_password");
					Client pass = new Client();
					password = pass.setClientPassword(uPass);
				}
				
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("Unable to get user password" + e);
		} finally {
			try { if (rs!=null) {
				rs.close();
				}
			} catch (SQLException e) {
				log.error("Unable to close resource for client password" + e);
			}
		}
		return password;
	}

	@Override
	public Client createClientByFunction(Client create) {
		String sql = "{call new_client(?,?,?,?)}";
		ResultSet rs = null;
		
			try(Connection c = ConnectionUtil.getConnection(); 
				CallableStatement cs = c.prepareCall(sql);) {
				cs.setInt(1, create.getClientId());
				cs.setString(2, create.getClientEmail());
				cs.setString(3, create.getClientPassword());
				cs.setInt(4, create.getClientPermissionId());
				cs.execute();
				rs = cs.getResultSet();
				
				while(rs.next()) {
					int cId = rs.getInt("client_id");
				
					create.setClientId(cId);
				}
					
				} catch (SQLException e) {
					log.error("Unable to create a new client" + e);
				} finally {
					try { if (rs!=null) {
						rs.close();
						}
					} catch (SQLException e) {
						log.error("Unable to close resource for create client" + e);
					}
				}
		return create;
	}

	@Override
	public Client updateClientPasswordByFunction(Client update) {
		String sql = "{call update_client(?,?)}";
		ResultSet rs = null;
		
			try(Connection c = ConnectionUtil.getConnection();
					CallableStatement cs = c.prepareCall(sql);){
				cs.setInt(1, update.getClientId());
				cs.setString(2, update.getClientPassword());
				cs.execute();
				rs= cs.getResultSet();
				
				while(rs.next() ) {
					String password = rs.getString("client_password");
					update.setClientPassword(password);
				}
				
			} catch (SQLException e) {
				log.error("Unable to update password" + e);
			} finally {
				try { if (rs!=null) {
					rs.close();
					}
				} catch (SQLException e) {
					log.error("Unable to close resource for client password" + e);
				}
			}
		return update;
	}


	@Override
	public int getClientPermission(int id) {
		String sql = "select client_permission from client where client_id = ?";
		ResultSet rs = null;
		int result = 0;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
				ps.setInt(1, id);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					int i = rs.getInt("client_permission");
					Client perm = new Client();
					result = perm.setClientPermissionId(i);
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(" Unable to get permission" + e);
		} finally {
			try { if (rs!=null) {
				rs.close();
				}
			} catch (SQLException e) {
				log.error("Unable to close resource for get permission" + e);
			}
		}
		return result;
	}

	@Override
	public int updateClientPermission(int permission, int id) {
		String sql = "update from client set client_permission = ? where client_id = ?";
		ResultSet rs = null;
		int result = 0;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
				ps.setInt(1, permission);
				ps.setInt(2, id);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					int i = rs.getInt("client_permission");
					Client perm = new Client();
					result = perm.setClientPermissionId(i);
				}
			
		} catch (SQLException e) {
			log.error(" Unable to update client permission" + e);
		} finally {
			try { if (rs!=null) {
				rs.close();
				}
			} catch (SQLException e) {
				log.error("Unable to close resource for update permission" + e);
			}
		}
		return result;
	}
	@Override
	public int deleteClient(int id) {
		String sql = "delete from client where client_id = ?";
		ResultSet rs = null;
		int result = 0;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
				ps.setInt(1, id);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					int i = rs.getInt("client_id");
					Client perm = new Client();
					result = perm.setClientId(i);
				}
			
		} catch (SQLException e) {
			log.error(" Unable to delete" + e);
		} finally {
			try { if (rs!=null) {
				rs.close();
				}
			} catch (SQLException e) {
				log.error("Unable to close resource for delete" + e);
			}
		}
		return result;
	}

}
