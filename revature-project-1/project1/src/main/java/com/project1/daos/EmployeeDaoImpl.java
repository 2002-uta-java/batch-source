package com.project1.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project1.models.Employee;
import com.project1.util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao{

	@Override
	public Employee credential(String identifier, String password) {
		String sql = "select * from  employee where emp_user_name = ? and passwd = ?";
		Employee emp = new Employee();
		ResultSet rs = null;
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, identifier);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				emp.setEmpId(rs.getInt("emp_id"));
				emp.setPosition(rs.getString("emp_position"));
				emp.setReportTo(rs.getInt("report_to"));
				emp.setName(rs.getString("emp_name"));
				emp.setAge(rs.getInt("age"));
				emp.setEmail(rs.getString("email"));
				emp.setUserName(rs.getString("emp_user_name"));
				emp.setPassword(rs.getString("passwd"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return emp;
	}

	@Override
	public Employee getEmployeeById(int id) {
		String sql = "select * from employee where emp_id = ?";
		Employee emp = null;
		ResultSet rs = null;
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);){
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int empId = rs.getInt("emp_id");
				String empPosition = rs.getString("emp_position");
				int reportTo = rs.getInt("report_to");
				String name = rs.getString("emp_name");
				int age = rs.getInt("age");
				String email = rs.getString("email");
				String userName = rs.getString("emp_user_name");
				String password = rs.getString("passwd");
				emp = new Employee(empId, empPosition, reportTo, name, age, email, userName, password);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return emp;
	}

	@Override
	public int updateEmployee(Employee emp) {
		String sql = "update employee set emp_name = ?, age = ?, email = ? where emp_id = ?";
		int employeeUpdated = 0;
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, emp.getName());
			ps.setInt(2, emp.getAge());
			ps.setString(3, emp.getEmail());
			ps.setInt(4, emp.getEmpId());
			
			employeeUpdated = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employeeUpdated;
	}

	@Override
	public List<Employee> getAllEmployee(String position) {
		String sql = "select * from employee where emp_position = ?";
		List<Employee> empList = new ArrayList<>();
		ResultSet rs = null;
		
		try(Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);){
			ps.setString(1, position);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int empId = rs.getInt("emp_id");
				String empPosition = rs.getString("emp_position");
				int reportTo = rs.getInt("report_to");
				String name = rs.getString("emp_name");
				int age = rs.getInt("age");
				String email = rs.getString("email");
				String userName = rs.getString("emp_user_name");
				String password = rs.getString("passwd");
				Employee emp = new Employee(empId, empPosition, reportTo, name, age, email, userName, password);
				empList.add(emp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return empList;
	}

}
