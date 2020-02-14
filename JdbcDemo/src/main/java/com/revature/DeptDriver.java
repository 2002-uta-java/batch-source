package com.revature;

import java.sql.SQLException;
import java.util.List;

import com.revature.model.Department;
import com.revature.services.DepartmentService;

public class DeptDriver {
	public static void main(String[] args) throws SQLException {
		final DepartmentService service = new DepartmentService();

		List<Department> depts = service.getAllDepartments();
		Department d = null;

		System.out.println("Initial departments");
		for (final Department dept : depts)
			System.out.println(dept);

		System.out.println();
		System.out.println();

//		System.out.println(service.getDepartmentById(10));

//		final Department dept = new Department("Legal", 8000);

//		System.out.println("departments created: " + service.createDepartment(dept));		final List<Department> depts = service.getAllDepartments();

//		for (final Department dept : depts)
//			System.out.println(dept);

//		d = service.getDepartmentById(14);
//		d.setMonthly_budget(9000);
//		d.setDept_name("Legal");
//
//		boolean result = service.updateDepartment(d);
//		System.out.println("updated? " + result);
//		depts = service.getAllDepartments();
//
//		System.out.println("After update:");
//		for (final Department dept : depts)
//			System.out.println(dept);
//
//		System.out.println();
//		System.out.println();
//
//		d = service.getDepartmentById(12);
//		System.out.println("deleting department: " + d);
//		System.out.println("deleted? " + service.deleteDepartment(d));
//
//		depts = service.getAllDepartments();
//
//		System.out.println("After delete:");
//		for (final Department dept : depts)
//			System.out.println(dept);

		d = new Department("Loss Mitigation", 4000);
		System.out.println(service.createDepartment(d));
	}
}
