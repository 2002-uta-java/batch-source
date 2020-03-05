package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao{
	
	private static Logger log = Logger.getRootLogger();
	
	@Override
	public List<Employee> getAllEmployees() {
		String sql = "select * from employee where title = 'employee'";
		List<Employee> employees = new ArrayList<>();
		
		try(Connection c = ConnectionUtil.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql)) {
			while(rs.next()) {
				Employee e = new Employee();
				e.setEid(rs.getInt("eid"));
				e.setLogin(rs.getString("login"));
				e.setPassword(rs.getString("pw"));
				e.setTitle(rs.getString("title"));
				e.setFirstName(rs.getString("first_name"));
				e.setLastName(rs.getString("last_name"));
				e.setEmail(rs.getString("email"));
				employees.add(e);
			}
		} catch(SQLException e1) {
			log.error("SQLException when getting all Employees\n");
		}
		return employees;
	}

	@Override
	public int createEmployee(String login, String pw, String title, String firstName, String lastName, String email) {
		String sql = "insert into employee(login, pw, title, first_name, last_name, email) values(?, ?, ?, ?, ?, ?)";
		int employeesCreated = 0;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, login);
			ps.setString(2, pw);
			ps.setString(3, title);
			ps.setString(4, firstName);
			ps.setString(5, lastName);
			ps.setString(6, email);
			employeesCreated = ps.executeUpdate();
			
		} catch (SQLException e1) {
			log.error("SQLException when creating an Employee\n");
		}
		return employeesCreated;
	}

	@Override
	public Employee getEmployeeById(int eid) {
		String sql = "select * from employee where eid = ?";
		Employee e = null;
		ResultSet rs = null;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, eid);
			rs = ps.executeQuery();
			while(rs.next()) {
				e = new Employee();
				e.setEid(rs.getInt("eid"));
				e.setLogin(rs.getString("login"));
				e.setPassword(rs.getString("pw"));
				e.setTitle(rs.getString("title"));
				e.setFirstName(rs.getString("first_name"));
				e.setLastName(rs.getString("last_name"));
				e.setEmail(rs.getString("email"));
			}
		} catch (SQLException e1) {
			log.error("SQLException when getting an Employee\n");
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException e1) {
				log.error("ResultSet couldn't close after getting Employee account by their eid\n");
			}
		}
		return e;
	}

	@Override
	public Employee getEmployeeByLogin(String login, String password) {
		String sql = "select * from employee where login = ? and pw = ?";
		Employee e = null;
		ResultSet rs = null;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, login);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while(rs.next()) {
				e = new Employee();
				e.setEid(rs.getInt("eid"));
				e.setLogin(rs.getString("login"));
				e.setPassword(rs.getString("pw"));
				e.setTitle(rs.getString("title"));
				e.setFirstName(rs.getString("first_name"));
				e.setLastName(rs.getString("last_name"));
				e.setEmail(rs.getString("email"));
			}
		} catch (SQLException e1) {
			log.error("SQLException when getting an Employee\n");
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException e1) {
				log.error("ResultSet couldn't close after getting Employee by their login\n");
			}
		}
		return e;
	}

	public int updateEmployee(Employee e) {
		String sql = "update employee set login = ?, pw = ?, first_name = ?, last_name = ?, email = ? where eid = ?";
		int employeesUpdated = 0;
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1,  e.getLogin());
			ps.setString(2, e.getPassword());
			ps.setString(3, e.getFirstName());
			ps.setString(4, e.getLastName());
			ps.setString(5, e.getEmail());
			ps.setInt(6, e.getEid());
			
			employeesUpdated = ps.executeUpdate();
		} catch (SQLException e1) {
			log.error("SQLException when updating an Employee\n");
		}
		return employeesUpdated;
	}

	public int deleteEmployee(Employee e) {
		// TODO Auto-generated method stub
		return 0;
	}

}
