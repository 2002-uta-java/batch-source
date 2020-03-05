package xyz.revature.daos;

import java.util.List;

import xyz.revature.models.Employee;

public interface EmployeeDao {
	public List<Employee> getAllEmployees();
	public Employee getEmployee(int id);
	public int getIdByName(String name);
	public int addEmployee(Employee employee);
	public Employee getAuthEmployee(String name, String password);
}
