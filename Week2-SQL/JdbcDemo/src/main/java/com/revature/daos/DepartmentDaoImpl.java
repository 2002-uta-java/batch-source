package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Department;
import com.revature.util.ConnectionUtil;

public class DepartmentDaoImpl implements DepartmentDao {

	@Override
	public List<Department> getDepartments() {
		String sql = "select * from department";
		
		List<Department> departments = new ArrayList<>();
		
		try (Connection c = ConnectionUtil.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql)){
			
			while(rs.next()) {
				/*
				 * Department d = new Department();
				 * d.setDeptId(rs.getInt("dept_id"));
				 * ....
				 */
				
				int deptId = rs.getInt("dept_id");
				String deptName = rs.getString("dept_name");
				double budget = rs.getDouble("monthly_budget");
				Department d = new Department(deptId, deptName, budget);
				departments.add(d);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return departments;
	}

	@Override
	public Department getDepartmentById(int id) {
		String sql = "select * from department where dept_id = ?";
		Department d = null;
		ResultSet rs = null;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int deptId = rs.getInt("dept_id");
				String deptName = rs.getString("dept_name");
				double budget = rs.getDouble("monthly_budget");
				d = new Department(deptId, deptName, budget);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return d;
	}

	@Override
	public int createDepartment(Department d) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateDepartment(Department d) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteDepartment(Department d) {
		// TODO Auto-generated method stub
		return 0;
	}

}
