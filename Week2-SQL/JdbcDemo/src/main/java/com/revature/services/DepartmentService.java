package com.revature.services;

import java.util.List;

import com.revature.daos.DepartmentDao;
import com.revature.daos.DepartmentDaoImpl;
import com.revature.models.Department;

public class DepartmentService {
	
	private DepartmentDao departmentDao = new DepartmentDaoImpl();
	
	public List<Department> getAllDepartments(){
		return departmentDao.getDepartments();
	}

	public Department getDepartmentById(int id) {
		return departmentDao.getDepartmentById(id);
	}
	
	public boolean createDepartment(Department d) {
		int deptsCreated = departmentDao.createDepartment(d);
		if(deptsCreated != 0 ) {
			return true;
		}
		return false;
	}
	
	public boolean updateDepartment(Department d) {
		int deptsUpdated = departmentDao.updateDepartment(d);
		if(deptsUpdated != 0) {
			return true;
		}
		return false;
	}
	
	public boolean deleteDepartment(Department d) {
		int deptsDeleted = departmentDao.deleteDepartment(d);
		if(deptsDeleted != 0) {
			return true;
		}
		return false;
	}
	
}
