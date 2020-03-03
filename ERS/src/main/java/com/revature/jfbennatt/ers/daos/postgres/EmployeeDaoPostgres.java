package com.revature.jfbennatt.ers.daos.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.log4j.Logger;

import com.revature.jfbennatt.connections.ConnectionUtil;
import com.revature.jfbennatt.ers.daos.EmployeeDao;
import com.revature.jfbennatt.ers.models.Employee;

/**
 * Implementation of {@link EmployeeDao} used to access the Postgresql database
 * on AWS.
 * 
 * @author Jared F Bennatt
 *
 */
public class EmployeeDaoPostgres implements EmployeeDao {
	/**
	 * Column name for employee id in Postgresql database.
	 */
	public static final String EMP_ID = "empl_id";
	/**
	 * Column name for employee email in Postgresql database.
	 */
	public static final String EMP_EMAIL = "empl_email";
	/**
	 * Column name for employee password in Postgresql database.
	 */
	public static final String EMP_PASSWORD = "password";
	/**
	 * Column name for employee first name in Postgresql database.
	 */
	public static final String EMP_FIRST_NAME = "empl_first_name";
	/**
	 * Column name for employee last name in Postgresql database.
	 */
	public static final String EMP_LAST_NAME = "empl_last_name";
	/**
	 * Column name for the session token in Postgresql database.
	 */
	public static final String EMP_SESSION_TOKEN = "session_token";
	/**
	 * Column name for whether or not employee is a manager in Postgresql database.
	 */
	public static final String EMP_IS_MANAGER = "is_manager";
	/**
	 * Length of the session token used by this postgresql database.
	 */
	public static final int SESSION_TOKEN_LENGTH = 64;
	/**
	 * Name of the table for employees in this database.
	 */
	public static final String EMPLOYEE_TABLE = "employees";

	public EmployeeDaoPostgres() {
		super();
	}

	@Override
	public Employee getEmployeeByToken(String token) {
		final String sql = "select * from " + EMPLOYEE_TABLE + " where " + EMP_SESSION_TOKEN + " = ?";
		Employee emp = null;
		ResultSet rs = null;

		try (final Connection con = ConnectionUtil.getConnection();
				final PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, token);
			rs = ps.executeQuery();

			while (rs.next()) {
				emp = new Employee();
				emp.setEmpId(rs.getInt(EMP_ID));
				emp.setEmail(rs.getString(EMP_EMAIL));
				emp.setFirstName(rs.getString(EMP_FIRST_NAME));
				emp.setLastName(rs.getString(EMP_LAST_NAME));
				emp.setManager(rs.getBoolean(EMP_IS_MANAGER));
				emp.setToken(rs.getString(EMP_SESSION_TOKEN));
			}
		} catch (SQLException e) {
			Logger.getRootLogger().error(e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					Logger.getRootLogger().error("Failed to close ResultSet: " + e.getMessage());
				}
			}
		}

		return emp;
	}

	@Override
	public Employee getEmployeeByEmail(String email) {
		final String sql = "select * from " + EMPLOYEE_TABLE + " where " + EMP_EMAIL + " = ?";
		Employee emp = null;
		ResultSet rs = null;

		try (final Connection con = ConnectionUtil.getConnection();
				final PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, email);
			rs = ps.executeQuery();

			while (rs.next()) {
				emp = new Employee();
				emp.setEmpId(rs.getInt(EMP_ID));
				emp.setEmail(rs.getString(EMP_EMAIL));
				emp.setFirstName(rs.getString(EMP_FIRST_NAME));
				emp.setLastName(rs.getString(EMP_LAST_NAME));
				emp.setPassword(rs.getString(EMP_PASSWORD));
				emp.setManager(rs.getBoolean(EMP_IS_MANAGER));
				emp.setToken(rs.getString(EMP_SESSION_TOKEN));
			}
		} catch (SQLException e) {
			Logger.getRootLogger().error(e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					Logger.getRootLogger().error("Failed to close ResultSet: " + e.getMessage());
				}
			}
		}

		return emp;
	}

	@Override
	public int getTokenLength() {
		return SESSION_TOKEN_LENGTH;
	}

	@Override
	public boolean setTokenById(int empId, String token) {
		final String sql = "update " + EMPLOYEE_TABLE + " set " + EMP_SESSION_TOKEN + " = ? where " + EMP_ID + " = ?";

		try (final Connection con = ConnectionUtil.getConnection();
				final PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, token);
			ps.setInt(2, empId);

			if (ps.executeUpdate() == 0) {
				Logger.getRootLogger().error("Failed to update employee " + empId);
				return false;
			}
		} catch (SQLException e) {
			Logger.getRootLogger().error(e.getMessage());
			return false;
		}

		// if we made it this far, we successfully updated the session token for this
		// employee
		return true;
	}

	@Override
	public boolean deleteSessionToken(String token) {
		final String sql = "update " + EMPLOYEE_TABLE + " set " + EMP_SESSION_TOKEN + " = ? where " + EMP_SESSION_TOKEN
				+ " = ?";

		try (final Connection con = ConnectionUtil.getConnection();
				final PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setNull(1, Types.VARCHAR);
			ps.setString(2, token);

			final int updated = ps.executeUpdate();

			if (updated == 0) {
				Logger.getRootLogger().error("Failed delete a token: " + token);
				return false;
			}
		} catch (SQLException e) {
			Logger.getRootLogger().error(e.getMessage());
		}
		// if we get here, the delete was successful
		return true;
	}

}
