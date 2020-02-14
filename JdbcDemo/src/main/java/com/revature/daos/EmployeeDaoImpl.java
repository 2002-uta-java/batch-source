package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Department;
import com.revature.model.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao {

	private DepartmentDao dd = null;

	@Override
	public List<Employee> getEmployees() {
		// use oj for outer join (Carolynn doesn't think you can do an inner join)
		final String sql = "select * from {oj employee left outer join department on (employee.dept_id=department.dept_id)}";
		final List<Employee> employees = new ArrayList<>();

		try (final Connection c = ConnectionUtil.getConnection();
				final PreparedStatement ps = c.prepareStatement(sql);
				final ResultSet rs = ps.executeQuery();) {

			// can get meta data (like number of columns
			rs.getMetaData();
			for (int i = 0; i < rs.getMetaData().getColumnCount(); ++i) {
				System.out.println(rs.getMetaData().getColumnLabel(i + 1));
			}

			while (rs.next()) {
				final Employee e = new Employee();
				e.setId(rs.getInt("empl_id"));
				e.setName(rs.getString("empl_name"));
				e.setMonthlySalary(rs.getDouble("monthly_salary"));
				e.setPosition(rs.getString("empl_position"));
				e.setManagerId(rs.getInt("manager_id"));
				int deptId = rs.getInt("dept_id");
				if (deptId != 0) {
					Department d = new Department(deptId);
					d.setDept_name(rs.getString("dept_name"));
					d.setMonthly_budget(rs.getDouble("monthly_budget"));
					e.setDept(d);
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
		final String sql = "select * from employee where empl_id = ?";
		Employee e = null;
		ResultSet rs = null;

		try (final Connection c = ConnectionUtil.getConnection();
				final PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setInt(1, id);

			if (ps.execute()) {
				rs = ps.getResultSet();
				while (rs.next()) {
					e = new Employee();
					e.setId(rs.getInt("empl_id"));
					e.setName(rs.getString("empl_name"));
					e.setMonthlySalary(rs.getDouble("monthly_salary"));
					e.setPosition(rs.getString("empl_position"));
					e.setManagerId(rs.getInt("manager_id"));
					e.setDept(new Department(rs.getInt("dept_id")));
				}

				final Department d = dd.getDepartmentById(e.getDeptId().getId());
				e.setDept(d);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return e;
	}

	@Override
	public void setDepartmentDao(DepartmentDao dd) {
		this.dd = dd;
	}
}
