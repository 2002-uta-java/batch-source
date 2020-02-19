package com.revature.doas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Bank;
import com.revature.util.ConnectionUtil;
import com.revature.util.UserUI;

public class BankDaoImpl implements BankDao{
/**
 *creating our account: we connect to DB and add the information
 *to our bank table
 */
	public int createAccount(Bank b) {
		String sql = "insert into \"Bank\" (\"UserName\", \"PassWord\", \"AccountNumber\", \"Amount\") "
				+ "values (?, ?, ?, ?)";
		int accountsMade = 0;
		try(Connection c = ConnectionUtil.getHardConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			
			ps.setString(1, b.getUserName());
			ps.setString(2, b.getPassWord());
			ps.setString(3, b.getAccountNumber());
			ps.setDouble(4, b.getBalance());
			
			accountsMade = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return accountsMade;
	}


/**
 * updates the table by allowing us to deposit money
 */
	public int depositMoney(Bank b, double amount) {
		
		if (amount < 0) {
			return 0;
		}
		String sql = "update \"Bank\" set \"Amount\" = \"Amount\" + ? where \"AccountNumber\" = ?";
		int depositCreated = 0;
		
		try(Connection c = ConnectionUtil.getHardConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setDouble(1, amount);
			ps.setString(2, b.getAccountNumber());
			
			depositCreated = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return depositCreated;
	}
/**
 * takes the amount that the user entered and subtracts it from the balance column in our database.
 */
	public int withdrawMoney(Bank b, double amount) {
		if (amount < 0) {
			return 0;
		}
		String sql = "update \"Bank\" set \"Amount\" = \"Amount\" - ? where \"AccountNumber\" = ?";
		int afterWithdraw = 0;
		
		try(Connection c = ConnectionUtil.getHardConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setDouble(1, amount);
			ps.setString(2, b.getAccountNumber());
			
			afterWithdraw = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return afterWithdraw;
	
	}
/**
 * querys our table to get our balance
 */
	public Void viewBallance(Bank b) {
		
		String sql = "select \"Amount\" from \"Bank\" where \"AccountNumber\" = ?";
		ResultSet rs = null;
//		used to store our ballance
		double bal = 0;
		
		try(Connection c = ConnectionUtil.getHardConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setString(1, b.getAccountNumber());
			
			rs =  ps.executeQuery();
			while(rs.next()) {
				bal = rs.getDouble("Amount");
			}
			System.out.println("Your balance is: " + bal);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
/**logging the user in it fist checks if the username and password
 * then asks the user for a transaction
 * 
 */
	public Void login(String userName, String passWord, Bank b) {
		while(!checkUserNameAndPassWord(userName, passWord, b)) {
			System.out.println("Problem with username and password. Please try again");
			///call firstMenu method
			UserUI.firstMenu();
		}
		//TODO call userUI class
		UserUI.accountOptions();
		
		return null;
	}

	public Void logout() {
		System.out.println("Thank you have wonderful day");
		return null;
	}
/**
 * checks if both our userName and password are valid
 */
	@Override
		public boolean checkUserNameAndPassWord(String userName, String passWord, Bank b) {
		String sql = "select * from \"Bank\" where \"UserName\" = ? and \"PassWord\" = ?";
		try(Connection c = ConnectionUtil.getHardConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
				ps.setString(1, userName);
				ps.setString(2, passWord);
			    // using result set to check our password
				final ResultSet resultSet = ps.executeQuery();
				if (resultSet.next()) {
//					using resultSet.getString to get accountNumber for this row
//					we use the accountNumber to check the username and password because
//					the accountNumber is the only unique value
					 b.setAccountNumber(resultSet.getString("AccountNumber"));
					return true;
				}
				
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
		
		return false;
		}


}
