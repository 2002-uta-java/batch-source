package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao{

	@Override
	public List<Employee> getEmployees() {
		String sql = "select * from employee";
		
		List<Employee> employees = new ArrayList<>();
		
		try (Connection c = ConnectionUtil.getConnection()) {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("id"); 
				String email = rs.getString("email");
				String position = rs.getString("position");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String gender = rs.getString("gender");
				String password = rs.getString("password");
				Employee e = new Employee(id, email, firstName, lastName, gender, password, position);
				employees.add(e);
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return employees;
	}

	@Override
	public Employee getEmployeeById(int id) {
		String sql = "select * from employee where id = ?";
		Employee e = null;
		ResultSet rs = null;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String email = rs.getString("email");
				String position = rs.getString("position");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String gender = rs.getString("gender");
				String password = rs.getString("password");
				e = new Employee(id, email, firstName, lastName, gender, password, position);
			}
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return e;
	}
	
	@Override
	public void createEmployee(Employee e) {
		String sql = "insert into employee (id, email, position, first_name, last_name, gender, password) "
				+ "values (?, ?, ?, ?, ?, ?, ?)";
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, e.getId());
			ps.setString(2, e.getEmail());
			ps.setString(3, e.getPosition());
			ps.setString(4, e.getFirstName());
			ps.setString(5, e.getLastName());
			ps.setString(6, e.getGender());
			ps.setString(7, e.getPassword());
			
			ps.executeUpdate();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		
	}

	@Override
	public void updateEmployee(Employee e) {
		String sql = "update employee set email = ?, position = ?, first_name = ?, last_name = ?,"
				+ " gender = ?, password = ? where id = ?";
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, e.getEmail());
			ps.setString(2, e.getPosition());
			ps.setString(3, e.getFirstName());
			ps.setString(4, e.getLastName());
			ps.setString(5, e.getGender());
			ps.setString(6, e.getPassword());
			ps.setInt(7, e.getId());
			
			ps.executeUpdate();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		
	}

}
