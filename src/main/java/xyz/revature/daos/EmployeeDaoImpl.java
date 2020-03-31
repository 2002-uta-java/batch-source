package xyz.revature.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xyz.revature.models.Employee;
import xyz.revature.util.DatabaseConnecter;



public class EmployeeDaoImpl implements EmployeeDao {
	private List<Employee> employees = new ArrayList<>();
	
	public EmployeeDaoImpl() {
		super();
	}
	
	@Override
	public int addEmployee(Employee employee) {
		int id = 0;
		String sql = "select add_employee(?, ?, ?, ?, ?)";
		ResultSet rs = null;
		try (Connection c = DatabaseConnecter.hardcodedConnection();
				CallableStatement cs = c.prepareCall(sql)){
			cs.setString(1, employee.getName());
			cs.setString(2, employee.getPassword());
			cs.setString(3, employee.getPosition());
			cs.setInt(4, employee.getSalary());
			cs.setBoolean(5, employee.isManagement());
			cs.execute();
			rs = cs.getResultSet();
			while (rs.next()) {
				id = rs.getInt(1);
				employee.setId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return id;
	}
	
	
	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<>();
		String sql = "select * from Employee";
		ResultSet rs = null;
		try (Connection c = DatabaseConnecter.hardcodedConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			rs = ps.executeQuery();
			while (rs.next()) {
				Employee e = new Employee();
				e.setId(rs.getInt("employee_id"));
				e.setName(rs.getString("employee_name"));
				e.setPosition(rs.getString("employee_position"));
				e.setSalary(rs.getInt("employee_salary"));
				e.setManagement(rs.getBoolean("management"));
				employees.add(e);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}

	
	@Override
	public Employee getEmployee(int id) {
		Employee emp = null;
		ResultSet rs = null;
		String sql = "select * from Employee where employee_id = ?";
		try (Connection c = DatabaseConnecter.hardcodedConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (!rs.next()) {
				System.out.println("not result set");
				return emp;
			}
			System.out.println("result set");
			emp = new Employee();
			emp.setId(rs.getInt("employee_id"));
			emp.setPosition(rs.getString("employee_position"));
			emp.setSalary(rs.getInt("employee_salary"));
			emp.setManagement(rs.getBoolean("management"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emp;
	}
	@Override
	public int getIdByName(String name) {
		int id = 0;
		String sql = "select employee_id from Employee where employee_name = ?";
		try (Connection c = DatabaseConnecter.hardcodedConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	@Override
	public Employee getAuthEmployee(String name, String password) {
		Employee emp = null;
		ResultSet rs = null;
		String sql = "select * from Employee where employee_name = ? and employee_password = ?";
		try (Connection c = DatabaseConnecter.hardcodedConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1, name);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (!rs.next()) {
				System.out.println("not result set");
				return emp;
			}
			System.out.println("result set");
			emp = new Employee();
			emp.setId(rs.getInt("employee_id"));
			emp.setPosition(rs.getString("employee_position"));
			emp.setSalary(rs.getInt("employee_salary"));
			emp.setManagement(rs.getBoolean("management"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emp;
	}

}

