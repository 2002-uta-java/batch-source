package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao{

	@Override
	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getEmployeeByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> getReimbursementsAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> getReimbursementsByEmployee(Employee e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createEmployee(Employee e) {
		String sql = "insert into employee (email, position, first_name, last_name, gender, password) "
				+ "values (?, ?, ?, ?, ?, ?)";
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, e.getEmail());
			ps.setString(2, e.getPosition());
			ps.setString(3, e.getFirstName());
			ps.setString(4, e.getLastName());
			ps.setString(5, e.getGender());
			ps.setString(6, e.getPassword());
			
			ps.executeUpdate();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		
	}

	@Override
	public void createReimbursement(Reimbursement r) {
		// TODO Auto-generated method stub
	}

	@Override
	public void updateEmployee(Employee e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateReimbursement(Reimbursement r) {
		// TODO Auto-generated method stub
		
	}

}
