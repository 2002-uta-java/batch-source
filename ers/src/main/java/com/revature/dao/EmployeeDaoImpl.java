package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Employee;

public class EmployeeDaoImpl implements EmployeeDAO {

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<>();
		Connection connection = null;
		Statement stmt = null;
		
		try {
			connection = DAOUtilities.getConnection();
			stmt = connection.createStatement();
			String sql = "SELECT * FROM Employee";
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Employee e = new Employee();
				
				e.setId(rs.getInt("employee_id"));
				e.setfName(rs.getString("firstname"));
				e.setlName(rs.getString("lastname"));
				e.setEmail(rs.getString("email"));
				e.setPhone(rs.getString("phone"));
				e.setPass(rs.getString("pass"));
				e.setIsManager(rs.getBoolean("isManager"));
			}
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return employees;
	}

	@Override
	public Employee getEmployee(int id) {
		Employee em = new Employee();
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM Employee WHERE employee_id = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				em.setId(rs.getInt("employee_id"));
				em.setfName(rs.getString("firstname"));
				em.setlName(rs.getString("lastname"));
				em.setEmail(rs.getString("email"));
				em.setPhone(rs.getString("phone"));
				em.setPass(rs.getString("pass"));
				em.setIsManager(rs.getBoolean("isManager"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return em;
	}
	
	@Override
	public Employee getEmployee(String uName) {
		Employee em = new Employee();
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM Employee WHERE email = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, uName);
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				em.setId(rs.getInt("employee_id"));
				em.setfName(rs.getString("firstname"));
				em.setlName(rs.getString("lastname"));
				em.setEmail(rs.getString("email"));
				em.setPhone(rs.getString("phone"));
				em.setPass(rs.getString("pass"));
				em.setIsManager(rs.getBoolean("isManager"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return em;
	}

	@Override
	public void addEmployee(Employee employee) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO Employee (firstname, lastname, email, phone, password, isManager) VALUES (?, ?, ?, ?, ?)";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, employee.getfName());
			stmt.setString(2, employee.getlName());
			stmt.setString(3, employee.getEmail());
			stmt.setString(4, employee.getPhone());
			stmt.setString(5, employee.getPass());
			stmt.setBoolean(6, employee.getIsManager());
			
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (success == 0) {
			throw new Exception("Insert Employee failed: " + employee);
		}
	}

	@Override
	public void deleteEmployee(int id) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM Employee WHERE employee_id = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (success == 0) {
			throw new Exception("Delete Employee faile: " + id);
		}
	}

	@Override
	public void updateEmployee(Employee employee) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "Update Employee (firstname, lastname, email, phone, password, isManager) VALUES (?, ?, ?, ?, ?, ?) WHERE employee_id = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, employee.getfName());
			stmt.setString(2, employee.getlName());
			stmt.setString(3, employee.getEmail());
			stmt.setString(4, employee.getPhone());
			stmt.setString(5, employee.getPass());
			stmt.setBoolean(6, employee.getIsManager());
			stmt.setInt(7, employee.getId());
			
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (success == 0) {
			throw new Exception("Insert Employee failed: " + employee);
		}
	}
}
