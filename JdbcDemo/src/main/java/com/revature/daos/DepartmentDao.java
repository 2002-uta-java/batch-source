package com.revature.daos;

import java.util.List;

import com.revature.model.Department;

public interface DepartmentDao {
	public List<Department> getDepartments();

	public Department getDepartmentById(final int id);

	public int createDepartment(final Department d);

	public int updateDepartment(final Department d);

	public int deleteDepartment(final Department d);

	public Department createDepartentWithFunction(final Department d);
}
