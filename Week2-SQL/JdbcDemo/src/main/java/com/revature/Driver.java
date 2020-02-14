package com.revature;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.models.Department;
import com.revature.models.Employee;
import com.revature.services.DepartmentService;
import com.revature.services.EmployeeService;
import com.revature.util.ConnectionUtil;

public class Driver {
	
	public static void main(String[] args) {
		
//		try {
//			Connection c = ConnectionUtil.getConnection();
//			String driverName = c.getMetaData().getDriverName();
//			System.out.println(driverName);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
		DepartmentService dService = new DepartmentService();
//		List<Department> departments = dService.getAllDepartments();
//		for(Department d: departments) {
//			System.out.println(d);
//		}
//		System.out.println(dService.getDepartmentById(7));
		
//		Department newDepartment = new Department("Legal",8000);
//		System.out.println(dService.createDepartment(newDepartment));
		
//		Department legalDept = dService.getDepartmentById(9);
//		System.out.println(legalDept);
//		
//		legalDept.setMonthlyBudget(9000);
//		legalDept.setName("; drop table department;");
//		
//		boolean result = dService.updateDepartment(legalDept);
//		System.out.println(result);
		
//		boolean result = dService.deleteDepartment(new Department(8));
//		System.out.println(result);
//		
//		List<Department> departments = dService.getAllDepartments();
//		for(Department d: departments) {
//			System.out.println(d);
//		}
		
//		Department newDepartment = new Department("Loss Mitigation", 4000);
//		System.out.println(dService.createDepartment(newDepartment));
	
//		boolean result = dService.deleteDepartment(new Department());
		
		EmployeeService es = new EmployeeService();
//		Employee e = es.getEmployeeById(4);
//		System.out.println(e);
		List<Employee> employees = es.getAllEmployees();
		for(Employee e: employees) {
			System.out.println(e);
		}
		
		
	}
	

}
