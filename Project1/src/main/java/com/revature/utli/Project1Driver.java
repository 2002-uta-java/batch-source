package com.revature.utli;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.dao.ClientDaoImpl;
import com.revature.model.Client;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.service.ClientService;
import com.revature.service.EmployeeService;
import com.revature.service.ReimbursementService;

public class Project1Driver {

	private static Logger log = Logger.getRootLogger();


	public static void main(String[] args) {
		
//		try {
//		Connection c = ConnectionUtil.getConnection();
//		String driverName = c.getMetaData().getDriverName();
//		System.out.println(driverName);
//	} catch (SQLException e) {
//		log.error("Sorry no connection", e);
//	}
	    
		
		ClientService list = new ClientService();
		List<Client> x = list.getClient();
		System.out.println(x);
		
		EmployeeService email = new EmployeeService();
		int id = email.getEmployeeId("jimmybob23@gmail.com");
		System.out.println(id);
		
		ClientService choice = new ClientService();
		boolean a = choice.clientAuth("jimmybob23@gmail.com", "password");
		System.out.println(a);
		
		ClientService id2 = new ClientService();
		int clientId = id2.getClientId("jimmybob23@gmail.com");
		System.out.println(clientId);
		
		ReimbursementService listR = new ReimbursementService();
		List<Reimbursement> r = listR.getReimbursement();
		System.out.println(r);
		
		ReimbursementService remid = new ReimbursementService();
		List<Reimbursement> remid2 = remid.managerStatus(1);
		System.out.println(remid2);
		
		ReimbursementService remid4 = new ReimbursementService();
		String remid3 = remid4.getStatus(1);
		System.out.println(remid3);
		
		EmployeeService empl = new EmployeeService();
		List<Employee> e = empl.getEmployee();
		System.out.println(e);
	
		ReimbursementService remid5 = new ReimbursementService();
		List<Reimbursement> remid6 = remid5.getReimbursementId(3);
		System.out.println(remid6);
		
	}
	

}
