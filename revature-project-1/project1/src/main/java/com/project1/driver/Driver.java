package com.project1.driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.project1.models.Employee;
import com.project1.models.Reimbursement;
import com.project1.services.EmployeeService;
import com.project1.services.ReimbursementService;
import com.project1.util.ConnectionUtil;

public class Driver {

	public static void main(String[] args) {
//		// test connection
//		try {
//		Connection c = ConnectionUtil.getConnection();
//		String driverName = c.getMetaData().getDriverName();
//		System.out.println(driverName);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		Employee emp = new Employee();
//		EmployeeService es = new EmployeeService();
		
		// how to access field of each object
//		List<Employee> emps = es.getAllEmployee("Employee");
//		for (Employee e: emps) {
//			System.out.println(e);
//		}
//		emp = es.credential("hoang1", "hoang1pass");
//		System.out.println(emp);
//		emp.setAge(25);
//		es.updateEmployee(emp);
//		System.out.println(emp);
		
		
		// reimbursement
//		ReimbursementService rService = new ReimbursementService();
//		Reimbursement reimb = new Reimbursement();
//		
		// list
//		List<Reimbursement> rList = rService.getReimbursementByEmpId(2);
//		for (Reimbursement r: rList) {
//			System.out.println(r);
//		}
//		
//		Reimbursement rNew = new Reimbursement();
//		rNew.setEmpId(3);
//		rNew.setAmount(250);
//		rNew.setPurpose("Relocation");
//		rService.createReimbWithFunction(rNew);
//		
//		System.out.println(rNew);
		
		
		
		
		
		
	}

}
