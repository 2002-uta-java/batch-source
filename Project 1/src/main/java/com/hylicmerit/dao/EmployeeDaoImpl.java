package com.hylicmerit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hylicmerit.models.Employee;
import com.hylicmerit.util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao {
	
	@Override
	public List<Employee> getAll() {
		String sql = "select * from employee";
		List<Employee> employees = new ArrayList<>();
		try (Connection c = ConnectionUtil.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql);) {
			while (rs.next()) {
				String email = rs.getString("employee_email");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String bio = rs.getString("bio");
				String bday = rs.getString("birthday");
				String role = rs.getString("role");
				String manager = rs.getString("reports_to");

				Employee e = new Employee(email, password, name, bio, bday, role, manager);
				employees.add(e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}

	public Employee getById(String emp_email) {
		String sql = "select * from employee where employee_email=?";
		ResultSet rs = null;
		Employee e = null;
		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setString(1, emp_email);
			rs = ps.executeQuery();
			while (rs.next()) {
				String email = rs.getString("employee_email");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String bio = rs.getString("bio");
				String bday = rs.getString("birthday");
				String role = rs.getString("role");
				String manager = rs.getString("reports_to");

				e = new Employee(email, password, name, bio, bday, role, manager);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return e;
	}

	public int create(Employee e) {
		String sql = "insert into employee (employee_email, password, name, bio, birthday, reports_to) values(?,?,?,?,?,?)";
		int numRowsAffected = 0;
		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			//add data to statement
			numRowsAffected = ps.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return numRowsAffected;
	}

	public int update(Employee e) {
		String sql = "update employee set password = ?, name = ?, bio = ?, birthday = ?, reports_to = ? where employee_email = ?";
		int numRowsAffected = 0;
		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setString(1, e.getPassword());
			ps.setString(2, e.getName());
			ps.setString(3, e.getBio());
			ps.setString(4, e.getBirthday());
			ps.setString(5, e.getManager());
			ps.setString(6, e.getEmployee_email());
			numRowsAffected = ps.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return numRowsAffected;
	}

	public int delete(Employee e) {
		String sql = "delete from employee where employee_email = ?";
		int numRowsAffected = 0;
		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			//add data to statement
			numRowsAffected = ps.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return numRowsAffected;
	}

}
