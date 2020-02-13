package com.revature;

import java.sql.SQLException;
import java.util.List;

import com.revature.model.Department;
import com.revature.services.DepartmentService;

public class Driver {
	public static void main(String[] args) throws SQLException {
		final DepartmentService service = new DepartmentService();

		final List<Department> depts = service.getAllDepartments();

		for (final Department dept : depts)
			System.out.println(dept);

		System.out.println(service.getDepartmentById(10));
	}
}
