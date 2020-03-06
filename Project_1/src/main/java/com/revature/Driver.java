package com.revature;

import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.services.EmployeeService;
import com.revature.services.ReimbursementService;

public class Driver {
	
	private static ReimbursementService rs = new ReimbursementService();

	public static void main(String[] args) {
		
		Reimbursement r = new Reimbursement();
		
//		r.setEmployeeId(7);
//		r.setCategory("Office");
//		r.setAmount(30.45);
//	
//		System.out.println(rs.createReimbursement(r));
		
//		System.out.println(rs.getAll());
		
//		System.out.println(rs.getAllReimbursementByStatus("pending"));
		
		r.setStatus("Pending");
		r.setReimbersementId(7);
		
		System.out.println(rs.updateReimbursement(r));
		
		
	}
}
		


//		private static EmployeeService es = new EmployeeService();
//	
//		public static void main(String[] args) {
		
//		System.out.println(es.getAllEmployee());
		
//		System.out.println(es.getEmployeeByUsername("adams_andrew", "adams80"));

//		Employee emp = new Employee();
//		emp.setEmployeeId(11);
//		emp.setEmail("felix_s@hotmail.com");
//		emp.setUsername("felix-schmidt");
//		emp.setPasswrd("felix");
//	
//		System.out.println(es.updateEmployee(emp));