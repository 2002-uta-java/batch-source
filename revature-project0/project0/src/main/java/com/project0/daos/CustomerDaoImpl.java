package com.project0.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project0.models.Customer;
import com.project0.util.ConnectionUtil;

public class CustomerDaoImpl implements CustomerDao{

	@Override
	public Customer credential(String identifier, String passwd) {
		String sql = "select * from  customer where (user_name = ? and passwd = ?) or (email = ? and passwd = ?)";
		Customer c = new Customer();
		ResultSet rs = null;
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, identifier);
			ps.setString(2, passwd);
			ps.setString(3, identifier);
			ps.setString(4, passwd);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				c.setId(rs.getInt("cust_id"));
				c.setUserName(rs.getString("user_name"));
				c.setEmail(rs.getString("email"));
				c.setPassword(rs.getString("passwd"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return c;
	}
	
	@Override
	public List<Customer> getCustomers() {
		String sql = "select * from customer";
		
		List<Customer> customers = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnection();
				Statement s = conn.createStatement();
				ResultSet rs = s.executeQuery(sql)){
			
			while(rs.next()) {
				
				int custId = rs.getInt("cust_id");
				String custUserName = rs.getString("user_name");
				String email = rs.getString("email");
				String password = rs.getString("passwd");
				Customer c = new Customer(custId, custUserName, email, password);
				customers.add(c);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return customers;
	}

	@Override
	public Customer getCustomerById(int id) {
		String sql = "select * from customer where cust_id = ?";
		Customer c = null;
		ResultSet rs = null;
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);){
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int custId = rs.getInt("cust_id");
				String custUserName = rs.getString("user_name");
				String email = rs.getString("email");
				String password = rs.getString("passwd");
				c = new Customer(custId, custUserName, email, password);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return c;
	}
	
	// create customer and return int
//	@Override
//	public int createCustomer(Customer c) {
//		String sql = "insert into customer (user_name, email, passwd) values (?, ?, ?)";
//		int customersCreated = 0;
//		
//		try(Connection conn = ConnectionUtil.getConnection();
//				PreparedStatement ps = conn.prepareStatement(sql)){
//			
//			ps.setString(1, c.getUserName());
//			ps.setString(2, c.getEmail());
//			ps.setString(3, c.getPassword());
//			
//			customersCreated = ps.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return customersCreated;
//	}

	@Override
	public int updateCustomer(Customer c) {
		String sql = "update customer set user_name = ?, email = ?, passwd = ? where cust_id = ?";
		int customerUpdated = 0;
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, c.getUserName());
			ps.setString(2, c.getEmail());
			ps.setString(3, c.getPassword());
			ps.setInt(4, c.getId());
			
			customerUpdated = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customerUpdated;
	}

	@Override
	public int deleteCustomer(Customer c) {
		String sql = "delete from customer where cust_id = ?";
		int rowsDeleted = 0;
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, c.getId());
			rowsDeleted = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsDeleted;
	}

	@Override
	public Customer createCustomerWithFunction(Customer c) {
		String sql = "{call add_customer(?, ?, ?)}";
		ResultSet rs = null;
		
		try(Connection conn = ConnectionUtil.getConnection();
				CallableStatement cs = conn.prepareCall(sql)){
			cs.setString(1, c.getUserName());
			cs.setString(2, c.getEmail());
			cs.setString(3, c.getPassword());
		
			cs.execute();
			
			rs = cs.getResultSet();
			
			while(rs.next()) {
				int newId = rs.getInt("cust_id");
				c.setId(newId);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
			}
		}
	}
		
		return c;
	}


}
