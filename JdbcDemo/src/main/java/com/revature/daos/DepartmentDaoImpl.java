package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Department;
import com.revature.util.ConnectionUtil;

public class DepartmentDaoImpl implements DepartmentDao {

	public List<Department> getDepartments() {
		final String sql = "select * from department order by dept_id";
		final List<Department> depts = new ArrayList<Department>();

		try (final Connection c = ConnectionUtil.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql)) {
			while (rs.next()) {
				final int deptId = rs.getInt("dept_id");
				final String deptName = rs.getString("dept_name");
				final double budget = rs.getDouble("monthly_budget");
				final Department d = new Department(deptId, deptName, budget);
				depts.add(d);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return depts;
	}

	public Department getDepartmentById(int id) {
		final String sql = "select * from department where dept_id = ? order by dept_id";
		Department d = null;
		ResultSet rs = null;

		try (final Connection c = ConnectionUtil.getConnection();
				final PreparedStatement ps = c.prepareStatement(sql);) {
			// 1-indexed
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				final int deptId = rs.getInt("dept_id");
				final String deptName = rs.getString("dept_name");
				final double budget = rs.getDouble("monthly_budget");
				d = new Department(deptId, deptName, budget);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return d;
	}

	public int createDepartment(Department d) {
		final String sql = "insert into Department (dept_name, monthly_budget) values (?, ?)";
		int deptCreated = 0;

		try (final Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, d.getName());
			ps.setDouble(2, d.getMonthlyBudget());

			deptCreated = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return deptCreated;
	}

	public int updateDepartment(Department d) {
		final String sql = "update department set dept_name = ?, monthly_budget = ? where dept_id = ?";
		int updatedDepts = 0;
		try (final Connection c = ConnectionUtil.getConnection();
				final PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setString(1, d.getName());
			ps.setDouble(2, d.getMonthlyBudget());
			ps.setInt(3, d.getId());

			updatedDepts = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return updatedDepts;
	}

	public int deleteDepartment(Department d) {
		final String sql = "delete from department where dept_id = ?";
		int rowsDeleted = 0;
		try (final Connection c = ConnectionUtil.getConnection();
				final PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setInt(1, d.getId());
			rowsDeleted = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rowsDeleted;
	}

}
