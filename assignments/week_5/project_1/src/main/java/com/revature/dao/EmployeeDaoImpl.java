package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao{

	// connects to database and creates a new employee entry. Returns the new employee in a Java object
	@Override
	public Employee createEmployee(Employee em) {
		
		String sql = "{call add_employee(?, ?, ?, ?, ?, ?, ?)}";
		
		ResultSet rs = null;
		
		try (Connection con = ConnectionUtil.getConnection();
				CallableStatement cs = con.prepareCall(sql)){
			
			cs.setInt(1, em.getProfile());
			cs.setInt(2, em.getDepartmentId());
			cs.setString(3, em.getFirstName());
			cs.setString(4, em.getLastName());
			cs.setString(5, em.getPhone());
			cs.setString(6, em.getEmail());
			cs.setString(7, em.getAddress());
			
			cs.execute();
			
			rs = cs.getResultSet();
			
			while(rs.next()) {
				int idUsed = rs.getInt("id");
				em.setId(idUsed);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return em;
	}

	// receives an employee object with its id set, it then finds the employee in the database that matches the id. Returns Employee object
	@Override
	public Employee getEmployee(Employee em) {
		
		String sql = "Select * from employee e where e.id = ?";
		
		ResultSet rs = null;
		
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareCall(sql)) {
			
			ps.setInt(1, em.getId());
			
			ps.execute();
			
			rs = ps.getResultSet();
			
			while(rs.next()) {
				em.setProfile(rs.getInt("profile"));
				em.setDepartmentById(rs.getInt("department"));
				em.setFirstName(rs.getString("first_name"));
				em.setLastName(rs.getString("last_name"));
				em.setPhone(rs.getString("phone"));
				em.setEmail(rs.getString("email"));
				em.setAddress(rs.getString("address"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return em;
	}

	// it updates the employee info (phone, email, address) in the database that matches the provided id. Returns an int to check for success
	@Override
	public int updateEmployee(Employee em) {
		
		String sql = "update employee set phone = ?, email = ?, address = ? where id = ?";
		
		int didItRun = 0;
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setString(1, em.getPhone());
			ps.setString(2, em.getEmail());
			ps.setString(3, em.getAddress());
			ps.setInt(4, em.getId());
			
			didItRun = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return didItRun;
	}

	@Override
	public List<Employee> getAllEmployees() {
		
		List<Employee> employeeList = new ArrayList<>();
		
		String sql = "select * from employee";
		
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)){
			
			while (rs.next()) {
				Employee em = new Employee();
				em.setId(rs.getInt("id"));
				em.setProfile(rs.getInt("profile"));
				em.setDepartmentById(rs.getInt("department"));
				em.setFirstName(rs.getString("first_name"));
				em.setLastName(rs.getString("last_name"));
				em.setPhone(rs.getString("phone"));
				em.setEmail(rs.getString("email"));
				em.setAddress(rs.getString("address"));
				
				employeeList.add(em);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeList;
	}
}
