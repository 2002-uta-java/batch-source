package com.revature.daos;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import com.revature.util.ConnectionUtil;
import com.revature.models.Employee;


public class EmployeeDaoImpl implements EmployeeDao{

	/**
	 * gettingS our employees by name
	 */
	@Override
	public Employee getEmployeeByName(String name) {
		String sql = "select * from \"Employee\" where \"name\" = ?";

		try (Connection c = ConnectionUtil.getHardConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

			ps.setString(1, name);
			ResultSet rs = ps.executeQuery(sql);

			if (rs.next()) {
				String name1 = rs.getString("employee_name");
				String passWord = rs.getString("pass_word");
				String email = rs.getString("email");
				String requestId = rs.getString("request_id");
				int empId = rs.getInt("emp_id");

				Employee em = new Employee(name1, passWord, email, requestId, empId);

				return em;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Employee getEmployeeByRequest(String reqeustId) {

		String sql = "select * from \"Employee\" where \"request_id\" = ?";

		try (Connection c = ConnectionUtil.getHardConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

			ps.setString(1, reqeustId);
			ResultSet rs = ps.executeQuery(sql);

			if (rs.next()) {
				String name1 = rs.getString("employee_name");
				String passWord = rs.getString("pass_word");
				String email = rs.getString("email");
				String requestId = rs.getString("request_id");
				int empId = rs.getInt("emp_id");

				Employee em = new Employee(name1, passWord, email, requestId, empId);
				return em;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Employee getEmployeeNameAndPassword(String name, String password) {
		
		String sql = "select * from \"Employee\" where \"employee_name\" = ? and \"pass_word\" = ?";

		try (Connection c = ConnectionUtil.getHardConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

			ps.setString(1, name);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery(sql);

			if (rs.next()) {
				String name1 = rs.getString("employee_name");
				String passWord = rs.getString("pass_word");
				String email = rs.getString("email");
				String requestId = rs.getString("request_id");
				int empId = rs.getInt("emp_id");

				Employee em = new Employee(name1, passWord, email, requestId, empId);
				return em;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Employee> getAllEmployees() {
		
		String sql = "select * from \"Employee\" order by \"employee_name\"";
		List<Employee> employees = new ArrayList<Employee>();
		try (Connection c = ConnectionUtil.getHardConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql)) {

			while (rs.next()) {
				// getting our employee attributes from the database and
				// putting them into our employees list
				String name1 = rs.getString("employee_name");
				String passWord = rs.getString("pass_word");
				String email = rs.getString("email");
				String requestId = rs.getString("request_id");
				int empId = rs.getInt("emp_id");

				Employee em = new Employee(name1, passWord, email, requestId, empId);

				employees.add(em);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}

	/**updating our employee name
	 */
	@Override
	public int updateEmployee(String name, int id) {
		String sql = "update \"Employee\" set name = ?,  where emp_id = ?";
		int update = 0;
		
		try(Connection c = ConnectionUtil.getHardConnection();
					PreparedStatement ps = c.prepareStatement(sql)) {
			
			ps.setString(1, name);
			ps.setInt(3, id);
			update = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return update;
	}

}
