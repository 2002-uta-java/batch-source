package com.revature.project0.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.project0.models.CustomerAccount;
import com.revature.project0.util.ConnectionUtil;

public class CustomerAccountDaoImpl implements CustomerAccountDao {
	
	// dont need
	@Override
	public List<CustomerAccount> getCustomerAccounts(){		
		String sql = "select * from customeraccount";
		
		List<CustomerAccount> customeraccounts = new ArrayList<>();
		
		try (Connection c = ConnectionUtil.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql)){
			while(rs.next()) {
				int accountId = rs.getInt("accountId");
				String username = rs.getString("username");
				String pass = rs.getString("pass");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String email = rs.getString("email");
				double balance = rs.getDouble("balance");
				CustomerAccount cu = new CustomerAccount(accountId, username, pass, firstName, lastName, email, balance);
				customeraccounts.add(cu);
			}
		} catch (SQLException e) {
			// TODO add stack trace
			e.printStackTrace();
		}
		
		return customeraccounts;
	}
	
	@Override
	public CustomerAccount getCustomerAccountById(int aId) {
		String sql = "select * from customeraccount where accountId = ?";
		CustomerAccount ca = null;
		ResultSet rs = null;
				
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, aId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int accId = rs.getInt("accountId");
				String username = rs.getString("username");
				String pass = rs.getString("pass");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String email = rs.getString("email");
				double balance = rs.getDouble("balance");
				ca = new CustomerAccount(accId, username, pass, firstName, lastName, email, balance);
			}
		} catch (SQLException e) {
			// TODO print stack trace
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e){
				// TODO print stack trace
				e.printStackTrace();
			}
		}
		
		return ca;
	}

	@Override
	public double getCustomerAccountBalance(int aId) {
		// TODO Auto-generated method stub
		String sql = "select balance from customeraccount where accountId = ?";
		double balance = 0;
		ResultSet rs = null;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setInt(1, aId);
			rs = ps.getResultSet();
			
			while (rs.next()) {
				balance = rs.getDouble("balance");
				
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return balance;
	}

	@Override
	public boolean checkCustomerAccountUsername(String username) {
		String sql = "select username from customeraccount where username = ?";
		//String usernameCheck = null;
		ResultSet rs = null;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			ps.setString(1, username);
			rs = ps.getResultSet();
			
			// if no rows, cant go back to 1st result
			if (rs != null && rs.first()) {
				return false;
			}
			
		} catch (SQLException e) {
			// TODO print stack trace
			e.printStackTrace();
		}
		
		return true;
	}

	@Override
	public String getCustomerAccountByPassword(String pass) {
		String sql = "select pass from customeraccount where pass = ?";
		String passwordCheck = null;
		ResultSet rs = null;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			ps.setString(1, pass);
			rs = ps.getResultSet();
			
			while(rs.next()) {
				passwordCheck = rs.getString("pass");
			}
		} catch (SQLException e) {
			// TODO print stack trace
			e.printStackTrace();
		}
		
		return passwordCheck;
	}
		
	@Override
	public int updateCustomerAccount(CustomerAccount ca) {
		String sql = "update customer set firstName = ?, lastName = ?, email = ? where customerId = ?";
		int customeraccountsUpdated = 0;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			ps.setString(1, ca.getFirstName());
			ps.setString(2, ca.getLastName());
			ps.setString(3, ca.getEmail());
			ps.setInt(4, ca.getaId());
			
			customeraccountsUpdated = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return customeraccountsUpdated;
	}

	@Override
	public CustomerAccount addNewCustomerAccount(CustomerAccount ca) {
		// TODO Auto-generated method stub
		String sql = "{call add_customer(?, ?, ?, ?, ?, ?)}";
		ResultSet rs = null;
		
		try (Connection c = ConnectionUtil.getConnection();
				CallableStatement cs = c.prepareCall(sql)){
			cs.setString(1, ca.getUsername());
			cs.setString(2, ca.getPass());
			cs.setString(3, ca.getFirstName());
			cs.setString(4, ca.getLastName());
			cs.setString(5, ca.getEmail());
			cs.setDouble(6, ca.getBalance());
			
			cs.execute();
			
			rs = cs.getResultSet();
			
			while(rs.next()) {
				int newId = rs.getInt("accountId");
				ca.setaId(newId);
			}
			
		} catch (SQLException e) {
			// TODO print
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO
					e.printStackTrace();
				}
			}
		}
		
		return ca;
	}


}
