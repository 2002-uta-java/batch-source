package com.dean.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.dean.models.Employee;
import com.dean.util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao {
	
	static final Logger logger = Logger.getRootLogger();
	

	@Override
	public Employee getEmployeeById(int id) {
		
		Employee e = new Employee();
		String sql = "SELECT * FROM EMPLOYEE WHERE EMP_ID = ?";
		ResultSet rs = null;
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				e.setId(rs.getInt("EMP_ID"));
				e.setName(rs.getString("EMP_NAME"));
				e.setManagerId(rs.getInt("MANAGER_ID"));
				e.setPosition(rs.getString("POSITION"));
				e.setUsername(rs.getString("EMP_USERNAME"));
				e.setPassword(rs.getString("EMP_PASSWORD"));
				e.setIsManager(rs.getInt("IS_MANAGER"));
				
			}
			
		} catch (SQLException e1) {
			logger.info(e1.getMessage());
		} catch (ClassNotFoundException e2) {
			logger.info(e2.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					logger.info(e1.getMessage());
				}
			}
		}
				
		
		return e;
	}

	@Override
	public Employee getEmployeeByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employeesList = new ArrayList<Employee>();

		String sql = "SELECT * FROM EMPLOYEE";

		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection(); 
				Statement s = con.createStatement();) {

			rs = s.executeQuery(sql);

			while (rs.next()) {
				Employee e = new Employee();

				e.setId(rs.getInt("EMP_ID"));
				e.setName(rs.getString("EMP_NAME"));
				e.setManagerId(rs.getInt("MANAGER_ID"));
				e.setPosition(rs.getString("POSITION"));
				e.setUsername(rs.getString("EMP_USERNAME"));
				e.setPassword(rs.getString("EMP_PASSWORD"));
				e.setIsManager(rs.getInt("IS_MANAGER"));

				employeesList.add(e);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return employeesList;
	}

	@Override
	public int createEmployee(Employee employee) {
		int employeesCreated = 0;
		String sql = "INSERT INTO EMPLOYEE (EMP_NAME, MANAGER_ID, POSITION, EMP_USERNAME, EMP_PASSWORD, IS_MANAGER)  VALUES (?,?,?,?,?,?)";
		try (Connection con = ConnectionUtil.getConnection(); 
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, employee.getName());
			ps.setInt(2, employee.getManagerId());
			ps.setString(3, employee.getPosition());
			ps.setString(4, employee.getUsername());
			ps.setString(5, employee.getPassword());
			ps.setInt(6, employee.getIsManager());

			employeesCreated = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return employeesCreated;
	}

	@Override
	public Employee getEmployeeByUsername(String username) {
		Employee e = new Employee();
		String sql = "SELECT * FROM EMPLOYEE WHERE EMP_USERNAME = ?";
		ResultSet rs = null;
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			
			ps.setString(1, username);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				e.setId(rs.getInt("EMP_ID"));
				e.setName(rs.getString("EMP_NAME"));
				e.setManagerId(rs.getInt("MANAGER_ID"));
				e.setPosition(rs.getString("POSITION"));
				e.setUsername(rs.getString("EMP_USERNAME"));
				e.setPassword(rs.getString("EMP_PASSWORD"));
				e.setIsManager(rs.getInt("IS_MANAGER"));
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return e;
	}

	@Override
	public int updateEmployeeById(int id, String name, String username, String position) {
		int updatedEmployee = 0;
		String sql = "UPDATE EMPLOYEE SET EMP_NAME = ?,EMP_USERNAME=?, POSITION=? WHERE EMP_ID = ?";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, name);
			ps.setString(2, username);
			ps.setString(3, position);
			ps.setInt(4, id);
			updatedEmployee = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return updatedEmployee;
	}
	
	@Override
	public int updateEmployeeByName(String name) {
		int updatedEmployee = 0;
		String sql = "UPDATE EMPLOYEE SET EMP_NAME = ? WHERE EMP_NAME = ?";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			
			ps.setString(1, name);
			updatedEmployee = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return updatedEmployee;
	}

}
