package com.revature.jfbennatt.reimbursement.daos.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.jfbennatt.reimbursement.connections.ConnectionUtil;
import com.revature.jfbennatt.reimbursement.dao.EmployeeDao;
import com.revature.jfbennatt.reimbursement.dao.models.Employee;

public class EmployeeDaoPostgres implements EmployeeDao {

	@Override
	public Employee getEmployee(String email) {
		final String sql = "select * from employees where " + EMAIL_FIELD + " = ?";
		ResultSet rs = null;
		Logger.getRootLogger().debug("Tryig to find employee: " + email);

		try (final Connection con = ConnectionUtil.getConnection();
				final PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, email);
			rs = ps.executeQuery();

			while (rs.next()) {
				final Employee emp = new Employee();
				emp.setEmail(rs.getString(EMAIL_FIELD));
				emp.setFirstName(rs.getString(FIRSTNAME_FIELD));
				emp.setLastName(rs.getString(LASTNAME_FIELD));
				emp.setPassword(rs.getString(PASSWORD_FIELD));

				return emp;
			}
		} catch (SQLException e) {
			Logger.getRootLogger().fatal("getEmployee, " + email + ", failed: " + e.getMessage());
			return null;
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					Logger.getRootLogger().error("Couldn't close ResultSet: )" + e.getMessage());
				}
		}

		// if we got here, no records were found
		Logger.getRootLogger().debug("Didn't find any records for email: " + email);
		return null;
	}

}
