package com.revature;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.models.Department;
import com.revature.services.DepartmentService;
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
		List<Department> departments = dService.getAllDepartments();
//		for(Department d: departments) {
//			System.out.println(d);
//		}
		System.out.println(dService.getDepartmentById(7));
		
		
	}
	

}
