package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Customer;
import com.revature.utli.ConnectionUtil;

public class CustomerDaoImpl implements CustomerDao {


	@Override
	public List<Customer> getCustomers() {
		String sql = "select * from customer";
		
		List<Customer> customers = new ArrayList<>();
		
		try (Connection c = ConnectionUtil.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql)) {
		
		   while(rs.next()) {
			   int cust_id = rs.getInt("customer_id");
			   String username = rs.getNString("user_name");
			   String password = rs.getNString("user_password");
			   String name = rs.getNString("first_name");
			   String lastname = rs.getNString("last_name");
			   String address = rs.getNString("address");
			   int zipcode = rs.getInt("zipcde");
			   String email = rs.getNString("user_email");
			   Customer c1 = new Customer(cust_id, username, password, name, lastname, address, zipcode, email);
			   customers.add(c1);
			   
		   }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return (List<Customer>) customers;
	}


	@Override
	public int getCustomerById(int id) {
		
		return 0;
		
		
	}

	@Override
	public int getCustomerByUsername(String username)  {
		String sql = "select customer_id from customer where user_name = ?";
		ResultSet rs = null;
		int c3 = 0;
	    
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1, username);
			rs = ps.executeQuery();
			
		while(rs.next()) {
			int id = rs.getInt("customer_id");
		    Customer c1 = new Customer();
		    c3 = c1.customerId(id);    
		   		
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if (rs!=null) {
				rs.close();
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return c3;
			
	}

	@Override
	public String getCustomerByPassword(String password) {
			String sql = "select user_password from customer where user_password = ?";
			ResultSet rs = null;
			String c1 = null;
			
			try(Connection c = ConnectionUtil.getConnection();
					PreparedStatement ps = c.prepareStatement(sql);){
				ps.setString(1, password);
				rs = ps.executeQuery();
				
			while(rs.next()) {
				c1 = rs.getNString("user_password");		
					}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try { if (rs!=null) {
					rs.close();
				}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	
				if(c1 != null) {
				return c1;
				} else {
					return "Invalid password";
				}
		}

	@Override
	public int createCustomer(Customer c1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateCustomer(Customer c1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCustomer(Customer c1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Customer createCustomerByFunction(Customer c1) {
		String sql = "{call customer_entry(?, ?, ?, ?, ?, ?, ?)}";
		
		ResultSet rs = null;
		
		try(Connection c = ConnectionUtil.getConnection();
				CallableStatement cs = c.prepareCall(sql)) {
			cs.setString(1, c1.getUsername());
			cs.setString(2, c1.getPassword());
			cs.setString(3, c1.getName());
			cs.setString(4, c1.getLastname());
			cs.setString(5, c1.getAddress());
			cs.setInt(6, c1.getZipcode());
			cs.setString(7, c1.getEmail());
			 
			cs.execute();
			
			rs = cs.getResultSet();
			while(rs.next()) {
				int cust_id = rs.getInt("customer_id");
				c1.setId(cust_id);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if (rs!=null) {
				rs.close();
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return c1;
	}


	@Override
	public String vaildationPassword(int id) {
		String sql = "select * from customer where customer_id = ?";
		ResultSet rs = null;
	    String pass = null ;
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
		while(rs.next()) {
			String password = rs.getString("user_password");
			Customer newC = new Customer(password);
			pass = newC.setPassword(password);	
	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if (rs!=null) {
				rs.close();
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return pass;
	}

	}

// if rs.first return true


