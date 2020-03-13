package com.revature.services;

import java.util.List;

import com.revature.daos.DepartmentDao;
import com.revature.daos.DepartmentDaoImpl;
import com.revature.model.Department;

public class DepartmentService {
	private DepartmentDao departmentDao = new DepartmentDaoImpl();

	public List<Department> getAllDepartments() {
		return departmentDao.getDepartments();
	}

	public Department getDepartmentById(final int id) {
		return departmentDao.getDepartmentById(id);
	}

	public Department createDepartment(final Department dept) {
//		return departmentDao.createDepartment(dept) != 0;
		return departmentDao.createDepartentWithFunction(dept);
	}

	public boolean updateDepartment(final Department d) {
		return departmentDao.updateDepartment(d) != 0;
	}

	public boolean deleteDepartment(Department d) {
		return departmentDao.deleteDepartment(d) != 0;
	}
}
