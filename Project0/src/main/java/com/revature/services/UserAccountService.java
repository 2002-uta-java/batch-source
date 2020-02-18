package com.revature.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.revature.daos.AccountDao;
import com.revature.daos.AccountDaoImpl;
import com.revature.models.UserAccount;

public class UserAccountService {
	
	private AccountDao dao = new AccountDaoImpl();
	
	public void userAccountMenu() throws NoSuchAlgorithmException {
		Scanner s = new Scanner(System.in);
		
		while (true) {
			System.out.println("Enter your new username (1-25 characters). Press # to cancel.");
			
			try {
				String newUsername = s.nextLine();
				if (newUsername.equals("#")) {
					break;
				}
				else {
					// Validate length of input.
	    			int len = newUsername.length();
	    			
	    			if (len < 1 || len > 25) {
	    				System.out.println("Invalid input (username must be between 1-25 characters).");
	    			}
	    			else {
	    				if (userAccountExist(newUsername)) {
	    					System.out.println("Username already exists.");
	    				}
	    				else {
	    					System.out.println("Enter your password.");
	    					String newPassword = s.nextLine();
	    					len = newPassword.length();
	    					
	    					if (len < 1) {
	    	    				System.out.println("Invalid input (password cannot be empty).");
	    	    			}
	    					else {
	    						newPassword = encryptPassword(newPassword);
	    						UserAccount u = new UserAccount(newUsername, newPassword);
	    						u = dao.createAccount(u);
	    						System.out.println("New user and bank account created! (username: " + u.getUsername() 
	    																	 + "  bankId: " + u.getBankId() + ")");
	    						break;
	    					}
	    				}
	    			}
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid input.");
			}
		}
	}
	
	public boolean userAccountExist(String username) {
		List<UserAccount> userAccounts = dao.getUserAccounts();
		
		for (UserAccount u: userAccounts) {
			if (username.equals(u.getUsername())) {
				return true;
			}
		}
		
		return false;
	}
	
	public String encryptPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
		
		return (bytesToHex(encodedhash));
	}
	
	private static String bytesToHex(byte[] hash) { // Code taken from: https://www.baeldung.com/sha-256-hashing-java
	    StringBuffer hexString = new StringBuffer();
	    
	    for (int i = 0; i < hash.length; i++) {
	    	String hex = Integer.toHexString(0xff & hash[i]);
	    	
	    	if (hex.length() == 1) hexString.append('0');
	        hexString.append(hex);
	    }
	    
	    return hexString.toString();
	}
	
}
