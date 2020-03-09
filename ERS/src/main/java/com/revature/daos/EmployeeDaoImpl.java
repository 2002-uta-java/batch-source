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

import org.apache.log4j.Logger;

public class EmployeeDaoImpl implements EmployeeDao {
	static final Logger logger = Logger.getLogger(EmployeeDaoImpl.class);
	private List<Employee> employees = new ArrayList<>();

	public EmployeeDaoImpl() {
		super();
	}

	public boolean createEmployee(Employee newEmployee) {
//		String sql = "insert into ers.\"Employee\" (FirstName, LastName, Title, Username, Password, Phone, Email, Address, City, State, Country, PostalCode, Role_Id) "
//				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String sql = "insert into ers.\"Employee\" (FirstName, LastName, Title, Username, Password, Email, manager_id) "
				+ " values(?,?,?,?,?,?,?)";

		try (Connection conn = ConnectionUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, newEmployee.getFirstName());
			ps.setString(2, newEmployee.getLastName());
			ps.setString(3, newEmployee.getTitle());
			ps.setString(4, newEmployee.getUsername());
			ps.setString(5, newEmployee.getPassword());
//			ps.setString(6, newEmployee.getPhone());
			ps.setString(6, newEmployee.getEmail());
//			ps.setString(8, newEmployee.getAddress());
//			ps.setString(9, newEmployee.getCity());
//			ps.setString(10, newEmployee.getState());
//			ps.setString(11, newEmployee.getCountry());
//			ps.setString(12, newEmployee.getPostalCode());
			ps.setInt(7, newEmployee.getManagerId());

			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public Employee getEmployeeDetailsById(int emplID) {
		String sql = "SELECT * FROM ers.\"Employee\" WHERE  EmployeeId=?";
		

		Employee emplFromDB = new Employee();

		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1,emplID);
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				// emplFromDB.setEmployeeId(rs.getString("EmployeeId"));
				emplFromDB.setFirstName(rs.getString("FirstName"));
				emplFromDB.setLastName(rs.getString("LastName"));
				emplFromDB.setTitle(rs.getString("Title"));
				emplFromDB.setUsername(rs.getString("Username"));
				emplFromDB.setPassword(rs.getString("Password"));
				//emplFromDB.setPhone(rs.getString("Phone"));
				emplFromDB.setEmail(rs.getString("Email"));
//				emplFromDB.setAddress(rs.getString("Address"));
//				emplFromDB.setCity(rs.getString("City"));
//				emplFromDB.setState(rs.getString("State"));
//				emplFromDB.setCountry(rs.getString("Country"));
//				emplFromDB.setPostalCode(rs.getString("PostalCode"));
				emplFromDB.setManagerId(rs.getInt("manager_id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// String result = "Updated Customer";
		System.out.print("Employee object from DB " + emplFromDB);

		return emplFromDB;
	}


	public Employee viewEmployeeDetails(String username) {
		String sql = "SELECT * FROM ers.\"Employee\" WHERE  username=?";
		

		Employee emplFromDB = new Employee();

		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				// emplFromDB.setEmployeeId(rs.getString("EmployeeId"));
				emplFromDB.setFirstName(rs.getString("FirstName"));
				emplFromDB.setLastName(rs.getString("LastName"));
				emplFromDB.setTitle(rs.getString("Title"));
				emplFromDB.setUsername(rs.getString("Username"));
				emplFromDB.setPassword(rs.getString("Password"));
				//emplFromDB.setPhone(rs.getString("Phone"));
				emplFromDB.setEmail(rs.getString("Email"));
//				emplFromDB.setAddress(rs.getString("Address"));
//				emplFromDB.setCity(rs.getString("City"));
//				emplFromDB.setState(rs.getString("State"));
//				emplFromDB.setCountry(rs.getString("Country"));
//				emplFromDB.setPostalCode(rs.getString("PostalCode"));
				emplFromDB.setManagerId(rs.getInt("manager_id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// String result = "Updated Customer";
		System.out.print("Employee object from DB " + emplFromDB);

		return emplFromDB;
	}

	public boolean updateEmployee(Employee updateEmployee) {
//		String sql = "update ers.\"Employee\" set FirstName = ?, LastName=?, Title=?, Phone=?,"
//				+ " Email=?, Address=?, City=?, State=?, Country=?, PostalCode=?, Role_Id=?  "
//				+ "where  username= ?";
		
		String sql = "update ers.\"Employee\" set FirstName = ?, LastName=?, Title=?,"
				+ " Email=?, manager_id=?  "
				+ "where  username= ?";

		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, updateEmployee.getFirstName());
			ps.setString(2, updateEmployee.getLastName());
			ps.setString(3, updateEmployee.getTitle());			
			//ps.setString(5, updateEmployee.getPassword());
			//ps.setString(4, updateEmployee.getPhone());
			ps.setString(4, updateEmployee.getEmail());
//			ps.setString(6, updateEmployee.getAddress());
//			ps.setString(7, updateEmployee.getCity());
//			ps.setString(8, updateEmployee.getState());
//			ps.setString(9, updateEmployee.getCountry());
//			ps.setString(10, updateEmployee.getPostalCode());
			ps.setInt(5, updateEmployee.getManagerId());
			ps.setString(6, updateEmployee.getUsername());
			
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public String deleteEmployee(int employeeId) {
		String sql = "delete from ers.Employee where employeeid=?";

		try (Connection conn = ConnectionUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, employeeId);
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "Deleted Employee";
	}

	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		String sql = "select * from ers.\"Employee\";";
		// String customerSQL = "Select * from BANK.Customer where login_id=?";

		try (Connection conn = ConnectionUtil.getConnection();
				Statement ps = conn.createStatement();
				ResultSet rs = ps.executeQuery(sql)) {

			while (rs.next()) {
				// get data from each employee
				Employee empl = new Employee();

				empl.setEmployeeId(rs.getString("EmployeeId"));
				empl.setFirstName(rs.getString("FirstName"));
				empl.setLastName(rs.getString("LastName"));
				empl.setTitle(rs.getString("Title"));
				empl.setUsername(rs.getString("Username"));
				empl.setPassword(rs.getString("Password"));
				//empl.setPhone(rs.getString("Phone"));
				empl.setEmail(rs.getString("Email"));
//				empl.setAddress(rs.getString("Address"));
//				empl.setCity(rs.getString("City"));
//				empl.setState(rs.getString("State"));
//				empl.setCountry(rs.getString("Country"));
//				empl.setPostalCode(rs.getString("PostalCode"));
				empl.setManagerId(rs.getInt("manager_id"));

				employees.add(empl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employees;
	}

	@Override
	public Employee getEmployeeByUsernameAndPassword(String username, String password) {
		// First check if the employee is empty. If it is empty get all employees list
		// first
		if (employees.isEmpty()) {
			employees = getAllEmployees();
		}
		for (Employee e : employees) {
			if (e.getUsername() != null && e.getUsername().equals(username)) {
				if (e.getPassword() != null && e.getPassword().equals(password)) {
					return e;
				}
			}
		}
		return null;
	}
}
