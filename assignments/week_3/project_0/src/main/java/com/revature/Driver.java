package com.revature;

import java.sql.Connection;
import java.util.List;

import com.revature.models.BankAccount;
import com.revature.models.Client;
import com.revature.models.UserAccount;
import com.revature.service.BankAccountService;
import com.revature.service.ClientService;
import com.revature.service.UserAccountService;
import com.revature.service.UserToBankAccountService;
import com.revature.util.ConnectionUtil;

public class Driver {
	
	public static void main(String[] args) {
		
		
		Welcome w = new Welcome();
		w.welcomeScreen();
		
		
		UserAccountService uas = new UserAccountService();
		ClientService cs = new ClientService();
		BankAccountService bas = new BankAccountService();
		UserToBankAccountService utbas = new UserToBankAccountService();
		
		
		
//		Client c = new Client("John", "Kruptka");
//		System.out.println(cs.createClient(c));
//		
//		UserAccount ua = new UserAccount(c.getId(), "jKruptka", "j.kruptka@revature.com", "pass$$$$");
//		uas.createUserAccount(ua);
		
//		BankAccount ba = new BankAccount(2, 555);
//		System.out.println(bas.createBankAccount(ba));
		
//		try {
//			Connection c = ConnectionUtil.getConnection();
//			String driverName = c.getMetaData().getDriverName();
//			System.out.println(driverName);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}


