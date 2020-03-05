package com.revature.daos;

import java.sql.CallableStatement;
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

public class EmployeeDaoImpl implements EmployeeDao {
	
	private static Logger log = Logger.getRootLogger();

	@Override
	public List<Employee> getAllEmployee() {
		String sql = "select * from Employee";
		
		List<Employee> Employee = new ArrayList<>();
		
		try (Connection c = ConnectionUtil.getConnection(); 
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql);) {
			
			while(rs.next()) {
				Employee e = new Employee();
				e.setEmployeeId(rs.getInt("EmployeeId"));
				e.setRoleId(rs.getInt("RoleId"));
				e.setUsername(rs.getString("Username"));
				e.setEmail(rs.getString("Email"));
				e.setPasswrd(rs.getString("Passwrd"));
				e.setFirstname(rs.getString("Firstname"));
				e.setLastname(rs.getString("Lastname"));
				
				Employee.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQLException");
		}
		return Employee;
	}

	@Override
	public Employee getEmployeeByUsername(String login, String passwrd) {
		String sql = "select * from Employee where Username = ? and Passwrd = ?";
		Employee emp = null;
		ResultSet rs = null;
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, login);
			ps.setString(2, passwrd);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				emp = new Employee();
				emp.setEmployeeId(rs.getInt("EmployeeId"));
				emp.setRoleId(rs.getInt("RoleId"));
				emp.setFirstname(rs.getString("Firstname"));
				emp.setLastname(rs.getString("Lastname"));
				emp.setUsername(rs.getString("Username"));
				emp.setEmail(rs.getString("Email"));
				emp.setPasswrd(rs.getString("Passwrd"));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
			log.error("SQLException");
		}
		finally {
			try {
				if(rs != null) {
					rs.close();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
				log.error("ResultSet couldn't close");
			}
		}
		return emp;
	}
	

	@Override
	public int updateEmployee(Employee e) {
		
		String sql = "update Employee set Username = ?, Passwrd = ?, Email = ?, Firstname = ?, Lastname = ?, RoleId = ? where EmployeeId = ?";
		
		int update = 0;
       
		ResultSet rs = null;
		Employee updateEmployee  = null;
		try(Connection c = ConnectionUtil.getConnection();
			PreparedStatement ps = c.prepareCall(sql)) {
			ps.setString(1, e.getUsername());
			ps.setString(2, e.getPasswrd());
			ps.setString(3, e.getEmail());
			ps.setString(4, e.getFirstname());
			ps.setString(5, e.getLastname());
			ps.setInt(6, e.getRoleId());
			ps.setInt(7, e.getEmployeeId());

			update = ps.executeUpdate();
			}
		
		catch (SQLException ex) {
			ex.printStackTrace();
		} 
		finally {
			try {
				if(rs != null)
					rs.close();				
			}
			catch(SQLException ex) {
				ex.printStackTrace();
				log.error("SQLException");
			}
		}
		return update;
	}

	@Override
	public int save(Employee e) {

		String sql = "insert into Employee(roleId,firstname,lastname,username,passwrd,email) values (?,?,?,?,?,?)";

		int update = 0;

		ResultSet rs = null;
		Employee updateEmployee  = null;
		try(Connection c = ConnectionUtil.getConnection();
			PreparedStatement ps = c.prepareCall(sql)) {
			ps.setInt(1, e.getRoleId());
			ps.setString(2,e.getFirstname());
			ps.setString(3, e.getLastname());
			ps.setString(4, e.getUsername());
			ps.setString(5, e.getPasswrd());
			ps.setString(6, e.getEmail());

			update = ps.executeUpdate();
		}

		catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			try {
				if(rs != null)
					rs.close();
			}
			catch(SQLException ex) {
				ex.printStackTrace();
				log.error("SQLException");
			}
		}
		return update;
	}

	@Override
	public Employee getEmployeeById(int id) {
		String sql = "select * from Employee where EmployeeId = ?";
		Employee emp = null;
		ResultSet rs = null;
		try (Connection c = ConnectionUtil.getConnection();
			 PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				emp = new Employee();
				emp.setEmployeeId(rs.getInt("EmployeeId"));
				emp.setRoleId(rs.getInt("RoleId"));
				emp.setFirstname(rs.getString("Firstname"));
				emp.setLastname(rs.getString("Lastname"));
				emp.setUsername(rs.getString("Username"));
				emp.setEmail(rs.getString("Email"));
				emp.setPasswrd(rs.getString("Passwrd"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQLException");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				log.error("ResultSet couldn't close");
			}
		}
		return emp;
	}
}
