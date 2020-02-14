package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Department;
import com.revature.models.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao {
	
	private DepartmentDao dd = new DepartmentDaoImpl();

	@Override
	public List<Employee> getEmployees() {
		List<Employee> employees = new ArrayList<>();
		String sql = "select * from {oj employee left outer join "
				+ "department on (employee.dept_id=department.dept_id)}";
		
		try(Connection c = ConnectionUtil.getConnection();
				Statement ps = c.createStatement();
				ResultSet rs = ps.executeQuery(sql)){
			
			for(int i=0; i<rs.getMetaData().getColumnCount(); i++) {
				System.out.println(rs.getMetaData().getColumnName(i+1));
			}
			
			while(rs.next()) {
				// get data from each employee
				Employee e = new Employee();
				e.setId(rs.getInt("empl_id"));
				e.setName(rs.getString("empl_name"));
				e.setMonthlySalary(rs.getDouble("monthly_salary"));
				e.setPosition(rs.getString("empl_position"));
				e.setManagerId(rs.getInt("manager_id"));
				int deptId = rs.getInt("dept_id");
				if(deptId!=0) {
					Department d = new Department(deptId);
					d.setName(rs.getString("dept_name"));
					d.setMonthlyBudget(rs.getDouble("monthly_budget"));
					e.setDepartment(d);
				}
				employees.add(e);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employees;
	}

	@Override
	public Employee getEmployeeById(int id) {
		String sql = "select * from employee where empl_id = ?";
		
		Employee employee = null;
		ResultSet rs = null;
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				employee = new Employee();
				employee.setId(id);
				employee.setName(rs.getString("empl_name"));
				employee.setMonthlySalary(rs.getDouble("monthly_salary"));
				employee.setPosition(rs.getString("empl_position"));
				employee.setManagerId(rs.getInt("manager_id"));
				employee.setDepartment(new Department(rs.getInt("dept_id")));
			}
			
			Department employeeDept = dd.getDepartmentById(employee.getDepartment().getId());
			employee.setDepartment(employeeDept);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return employee;
	}

}
