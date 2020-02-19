package com.revature.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDaoImpl implements UserDao {

	User u = new User();
	
	public void createUser() throws SQLException {
		String userPassword = ""; // define password strings for validation
		String userPassword2 = "";
		@SuppressWarnings("resource")
		Scanner userInput = new Scanner(System.in);

		System.out.println("Please enter an email or username to create account: ");
		String userEmail = userInput.nextLine(); // take in email or username to create account
		
		do { // do while loop to ensure that the password is the same
			System.out.println("Please enter a password to create account: ");
			userPassword = userInput.nextLine();
			System.out.println("Please confirm the same password to create account: ");
			userPassword2 = userInput.nextLine();
		} while (!(userPassword.equals(userPassword2))); 
		
		Double startingBalance = new Double(0.00); // sets the value of the bank account as 0 initially
		System.out.println("Your username or email to login is " + userEmail + ".");
		String sql = "insert into bank_user (user_email, user_password, user_balance) values ('" + userEmail + "', '" + userPassword + "', '" + startingBalance + "')";
		// sql statement to input data into the bank account for the user
		Connection c = ConnectionUtil.getConnection(); // connects to the database
		Statement s = c.createStatement(); // statement to put data into database
		s.executeQuery(sql); // executes the statement
	}

	public void login() throws SQLException {
		
		ResultSet rs = null; // initializes result set to null
		Scanner userInput = new Scanner(System.in);

		System.out.println("Please enter username or email to login: ");
		String userEmail = userInput.nextLine();
		System.out.println("Please enter password to login: ");
		String userPassword = userInput.nextLine(); // uses login information to get to second menu

        String sql = "select * from bank_user where user_email = ? and user_password = ?";
		Connection myConn= ConnectionUtil.getConnection();
		PreparedStatement ps = myConn.prepareStatement(sql); // prepared statement to use correct email and password into sql
		ps.setString(1, userEmail);
		ps.setString(2, userPassword);
		rs = ps.executeQuery();	
		
		while(rs.next()) {
			
			String dbUserEmail = rs.getString("user_email"); // receives the data from the database
			String dbUserPassword = rs.getString("user_password");
			Double dbUserBalance = rs.getDouble("user_balance");
			
			u = new User(dbUserEmail, dbUserPassword, dbUserBalance); // constructor for the retreived data
		}
		rs.close();
		try {
		if (u.password != null || u.password.equals(userPassword)) {
			showLoggedInMenu(u.email, u.balance); // goes to second menu is all conditions are correct
		} 
		  } catch (NullPointerException e) { // ensures that the correct login is used
			System.out.println("Invalid username or password provided\n");
		}
	}

	private void showLoggedInMenu(String userEmail, Double userBalance) throws SQLException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); // shows the timestamp
		Date date = new Date();
		char option = '0';
		Scanner scanner = new Scanner(System.in);
	
		System.out.println(dateFormat.format(date)); 
		System.out.println("Welcome Back Valued Customer!\n");
		
		do { 
		System.out.println("What would you like to do today?\n"); // menu for the bank user
		System.out.println("a. Withdraw");
		System.out.println("b. Deposit");
		System.out.println("c. Check Current Balance");
		System.out.println("d. Log out");
		System.out.println("\n");
		System.out.println("Please enter an option: ");
	
		option = scanner.next().charAt(0);
	
		switch(option) { // switch statement to determine which method will be used based off the user input
			case 'a':
				System.out.println("Withdraw selected\n");
				withdraw(userEmail, userBalance);
				break;
			case 'b':
				System.out.println("Deposit selected\n");
				deposit();
				break;
			case 'c':
				System.out.println("Check current balance selected\n");
				checkBalance(userEmail);
				break;
			case 'd':
				System.out.println("Log out selected\n");
				break;
			default:
				System.out.println("Not a valid option. Try again.\n");
				break; 
			} 
		} while (option != 'd');
		System.out.println("You have successfully logged out.\n");	// ends the case statement if d is selected	
	}

	private void checkBalance(String userEmailFromStack) throws SQLException { 
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection c= ConnectionUtil.getConnection();

		String sql = "select user_balance from bank_user where user_email = ?";
		ps = c.prepareStatement(sql); // receives the username or email 
		ps.setString(1, userEmailFromStack);
		// Execute SQL query
		rs = ps.executeQuery();
		
		// Process result set
		while (rs.next()) {
			Double userBalance = rs.getDouble("user_balance");
			System.out.printf("%s%.2f", "Your Current Available balance is ", userBalance); // prints out balance of user bank with 2 decimals
			System.out.print(" USD\n");
			u.balance = userBalance; // saves the user balance
		}
	}

	public static boolean withdraw(String userEmailWithdrawal, Double userBalance) throws SQLException {
		Connection myConn= ConnectionUtil.getConnection();
		CallableStatement myCallable = null;
		String sql = "";
		Scanner userInput = new Scanner(System.in);
		String inputAmount = "";
		String confirmation = "";
		Double amount = new Double(0.00);
		
		do{
			System.out.println("Please enter amount to withdraw: ");
			inputAmount = userInput.nextLine();
			System.out.println("You would like to withdraw $" + inputAmount + ".\n" + 
			"Is this amount correct? (enter 'y' to proceed)"); // verifies the amount withdrawn is correct
			confirmation = userInput.nextLine().toLowerCase(); 
		}while(!(confirmation.equals("y"))); // continues to run loop until correct input is performed
		
		amount = new Double(inputAmount);
		
		if (amount.compareTo(userBalance)==1) { // validation using compareTo to ensure that the withdrawn amount is less than balance
			System.out.println("The withdrawal exceeds existing balance.\n"
					+ "Enter a smaller value instead.");
		} else {
			// prepare the stored procedure call
			sql = "{call withdraw(?, ?)}";
			myCallable = myConn.prepareCall(sql);
			// set the parameters
			myCallable.setString(1, userEmailWithdrawal); 
			myCallable.setDouble(2, amount);
			// call the stored procedure
			myCallable.execute();
			System.out.println("Withdrawl was successful!\n");
			}
	
		// return boolean flag for testing
		return true;
	}

	public void deposit() throws SQLException {
		Connection myConn= ConnectionUtil.getConnection();
		CallableStatement myCallable = null;
		String sql = "";
		@SuppressWarnings("resource")
		Scanner userInput = new Scanner(System.in);
		String userEmailDeposit = "";
		String userEmail2 = "1";
		String inputAmount = "";
		Double amount = new Double(0.00);
		do{
			System.out.println("Please enter username or email to make deposit: "); // ensure that the username or email is correct 
			userEmailDeposit = userInput.nextLine();
			System.out.println("Please confirm the same username or email to make deposit: ");
			userEmail2 = userInput.nextLine();
		}while(!(userEmailDeposit.equals(userEmail2))); // used for fast transfer if sending money to someone
		
		System.out.println("Please enter amount to deposit: "); 
		inputAmount = userInput.nextLine();
		amount = new Double(inputAmount); // gets the user input for dollars deposited
		
		// prepare the stored procedure call
		sql = "{call deposit(?, ?)}";
		myCallable = myConn.prepareCall(sql);
		// set the parameters
		myCallable.setString(1, userEmailDeposit);
		myCallable.setDouble(2, amount);
		// call the stored procedure
		myCallable.execute();
		
		System.out.println("Deposit was successful!\n");
	}
}
