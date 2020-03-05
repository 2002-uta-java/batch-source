package com.revature.jfbennatt.ers.daos.postgres;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.jfbennatt.connections.ConnectionUtil;
import com.revature.jfbennatt.ers.daos.EmployeeDao;
import com.revature.jfbennatt.ers.models.Employee;
import com.revature.jfbennatt.ers.models.Reimbursement;

/**
 * Implementation of {@link EmployeeDao} used to access the Postgresql database
 * on AWS.
 * 
 * @author Jared F Bennatt
 *
 */
public class EmployeeDaoPostgres implements EmployeeDao {
	public static final String DATE_PATTERN = "mm/dd/yyyy";
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
	/**
	 * Name of reimbursements table
	 */
	public static final String REIMBURSEMENTS_TABLE = "reimbursements";
	/**
	 * name of reimb_id column for the reimbursements table.
	 */
	public static final String REIMB_ID = "reimb_id";
	/**
	 * name of empl_id column for the reimbursements table.
	 */
	public static final String REIMB_EMPL_ID = "empl_id";
	/**
	 * name of description column for the reimbursements table.
	 */
	public static final String REIMB_DESCRIPT = "description";
	/**
	 * name of amount column for the reimbursements table.
	 */
	public static final String REIMB_AMOUNT = "amount";
	/**
	 * name of reimb_date (date of expense) column for the reimbursements table.
	 */
	public static final String REIMB_DATE = "reimb_date";
	/**
	 * name of submit date column for the reimbursements table.
	 */
	public static final String REIMB_SUBMIT_DATE = "submit_date";
	/**
	 * name of reply date (date request was approved or denied) column for the
	 * reimbursements table.
	 */
	public static final String REIMB_REPLY_DATE = "reply_date";
	/**
	 * name of status column for the reimbursements table.
	 */
	public static final String REIMB_STATUS = "reimb_status";
	/**
	 * String representing a reimbursement is pending.
	 */
	public static final String PENDING = "pending";
	/**
	 * String representing a reimbursement is approved.
	 */
	public static final String APPROVED = "approved";
	/**
	 * String representing a reimbursement is denied.
	 */
	public static final String DENIED = "denied";
	/**
	 * Status code for a reimbursement that is pending.
	 */
	public static final int PENDING_INT = 1;
	/**
	 * Status code for a reimbursement that is approved.
	 */
	public static final int APPROVED_INT = 2;
	/**
	 * Status code for a reimbursement that is denied.
	 */
	public static final int DENIED_INT = 3;

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

	@Override
	public String datePattern() {
		return DATE_PATTERN;
	}

	@Override
	public boolean submitRequest(final Reimbursement newReimb) {
		final String sql = "insert into " + REIMBURSEMENTS_TABLE + " (" + REIMB_EMPL_ID + ", " + REIMB_DESCRIPT + ", "
				+ REIMB_AMOUNT + ", " + REIMB_DATE + ", " + REIMB_SUBMIT_DATE + ", " + REIMB_STATUS
				+ ") values(? , ? , ? , ? , ? , ?)";

		try (final Connection con = ConnectionUtil.getConnection();
				final PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, newReimb.getEmplId());
			ps.setString(2, newReimb.getDescription());
			ps.setObject(3, newReimb.getAmount());
			ps.setDate(4, new Date(newReimb.getReimbDate().getTime()));
			ps.setDate(5, new Date(newReimb.getSubmitDate().getTime()));
			ps.setInt(6, newReimb.getStatus());

			final int updated = ps.executeUpdate();

			if (updated == 0) {
				// nothing was updated, this failed
				return false;
			}
		} catch (SQLException e) {
			Logger.getRootLogger().error(e.getMessage());
			return false;
		}
		// if we made it this far, the insert was a success
		return true;
	}

	@Override
	public String getStatus(Reimbursement reimb) {
		switch (reimb.getStatus()) {
		case PENDING_INT:
			return PENDING;
		case APPROVED_INT:
			return APPROVED;
		case DENIED_INT:
			return DENIED;
		default:
			throw new RuntimeException(reimb.getStatus() + " is not a recognized status code");
		}
	}

	@Override
	public void setPending(Reimbursement reimb) {
		reimb.setStatus(PENDING_INT);
	}

	@Override
	public void setApproved(Reimbursement reimb) {
		reimb.setStatus(APPROVED_INT);
	}

	@Override
	public void setDenied(Reimbursement reimb) {
		reimb.setStatus(DENIED_INT);
	}

	@Override
	public List<Reimbursement> getAllReimbursementsByEmployeeId(int empId) {
		final String sql = "select * from " + REIMBURSEMENTS_TABLE + " where " + REIMB_EMPL_ID + " = ?";
		final List<Reimbursement> reimbs = new ArrayList<>();
		ResultSet rs = null;
		try (final Connection con = ConnectionUtil.getConnection();
				final PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, empId);
			rs = ps.executeQuery();

			while (rs.next()) {
				final Reimbursement reimb = new Reimbursement();
				reimb.setReimbId(rs.getInt(REIMB_ID));
				reimb.setEmplId(rs.getInt(REIMB_EMPL_ID));
				reimb.setDescription(rs.getString(REIMB_DESCRIPT));
				reimb.setAmount(rs.getBigDecimal(REIMB_AMOUNT));
				reimb.setReimbDate(rs.getDate(REIMB_DATE));
				reimb.setStatus(rs.getInt(REIMB_STATUS));
				reimb.setStatusString(getStatus(reimb));
				reimb.setSubmitDate(rs.getDate(REIMB_SUBMIT_DATE));
				// need to check whether or not the reply date date is null (it can be)
				final java.util.Date replyDate = rs.getDate(REIMB_REPLY_DATE);
				Logger.getRootLogger().debug("Submit Date: " + replyDate + " and rs.wasNull? " + rs.wasNull());
				if (!rs.wasNull()) {
					// if it's not null set it, if it is, don't
					reimb.setReplyDate(replyDate);
				}

				reimbs.add(reimb);
			}
		} catch (SQLException e) {
			Logger.getRootLogger().error(e.getMessage());
			return null;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					Logger.getRootLogger().error("Failed to close ResultSet: " + e.getMessage());
				}
			}
		}

		return reimbs;
	}

	@Override
	public boolean changeProfile(Employee employee) {
		// need to check whether or not the password is null, if so, don't attempt to
		// update it
		if (employee.getPassword() == null) {
			return changeProfileWithoutPassword(employee);
		} else {
			return changeProfileWithPassword(employee);
		}
	}

	private boolean changeProfileWithPassword(Employee employee) {
		final String sql = "update " + EMPLOYEE_TABLE + " set " + EMP_EMAIL + " = ?, " + EMP_PASSWORD + " = ?, "
				+ EMP_FIRST_NAME + " = ?, " + EMP_LAST_NAME + " = ? where " + EMP_ID + " = ?";

		try (final Connection con = ConnectionUtil.getConnection();
				final PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, employee.getEmail());
			ps.setString(2, employee.getPassword());
			ps.setString(3, employee.getFirstName());
			ps.setString(4, employee.getLastName());
			ps.setInt(5, employee.getEmpId());

			Logger.getRootLogger().debug(ps);

			final int updated = ps.executeUpdate();

			if (updated == 0) {
				Logger.getRootLogger().error("Failed to update employee: " + employee);
				return false;
			}

		} catch (SQLException e) {
			Logger.getRootLogger().error(e.getMessage());
			return false;
		}

		// if we made here, it was successful
		return true;
	}

	private boolean changeProfileWithoutPassword(Employee employee) {
		final String sql = "update " + EMPLOYEE_TABLE + " set " + EMP_EMAIL + " = ?, " + EMP_FIRST_NAME + " = ?, "
				+ EMP_LAST_NAME + " = ? where " + EMP_ID + " = ?";

		try (final Connection con = ConnectionUtil.getConnection();
				final PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, employee.getEmail());
			ps.setString(2, employee.getFirstName());
			ps.setString(3, employee.getLastName());
			ps.setInt(4, employee.getEmpId());

			Logger.getRootLogger().debug(ps);

			final int updated = ps.executeUpdate();

			if (updated == 0) {
				Logger.getRootLogger().error("Failed to update employee: " + employee);
				return false;
			}

		} catch (SQLException e) {
			Logger.getRootLogger().error(e.getMessage());
			return false;
		}

		// if we made here, it was successful
		return true;
	}

	@Override
	public List<Reimbursement> getPendingReimbursementsByEmployeeId(int empId) {
		final String sql = "select * from " + REIMBURSEMENTS_TABLE + " where " + REIMB_EMPL_ID + " = ? and "
				+ REIMB_STATUS + " = 1";
		final List<Reimbursement> reimbs = new ArrayList<>();
		ResultSet rs = null;
		try (final Connection con = ConnectionUtil.getConnection();
				final PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, empId);
			rs = ps.executeQuery();

			while (rs.next()) {
				final Reimbursement reimb = new Reimbursement();
				reimb.setReimbId(rs.getInt(REIMB_ID));
				reimb.setEmplId(rs.getInt(REIMB_EMPL_ID));
				reimb.setDescription(rs.getString(REIMB_DESCRIPT));
				reimb.setAmount(rs.getBigDecimal(REIMB_AMOUNT));
				reimb.setReimbDate(rs.getDate(REIMB_DATE));
				reimb.setStatus(rs.getInt(REIMB_STATUS));
				reimb.setStatusString(getStatus(reimb));
				reimb.setSubmitDate(rs.getDate(REIMB_SUBMIT_DATE));
				// need to check whether or not the reply date date is null (it can be)
				final java.util.Date replyDate = rs.getDate(REIMB_REPLY_DATE);
				Logger.getRootLogger().debug("Submit Date: " + replyDate + " and rs.wasNull? " + rs.wasNull());
				if (!rs.wasNull()) {
					// if it's not null set it, if it is, don't
					reimb.setReplyDate(replyDate);
				}

				reimbs.add(reimb);
			}
		} catch (SQLException e) {
			Logger.getRootLogger().error(e.getMessage());
			return null;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					Logger.getRootLogger().error("Failed to close ResultSet: " + e.getMessage());
				}
			}
		}

		return reimbs;
	}

	@Override
	public List<Reimbursement> getProcessedReimbursementsByEmployeeId(int empId) {
		final String sql = "select * from " + REIMBURSEMENTS_TABLE + " where " + REIMB_EMPL_ID + " = ? and "
				+ REIMB_STATUS + " != 1";
		final List<Reimbursement> reimbs = new ArrayList<>();
		ResultSet rs = null;
		try (final Connection con = ConnectionUtil.getConnection();
				final PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, empId);
			rs = ps.executeQuery();

			while (rs.next()) {
				final Reimbursement reimb = new Reimbursement();
				reimb.setReimbId(rs.getInt(REIMB_ID));
				reimb.setEmplId(rs.getInt(REIMB_EMPL_ID));
				reimb.setDescription(rs.getString(REIMB_DESCRIPT));
				reimb.setAmount(rs.getBigDecimal(REIMB_AMOUNT));
				reimb.setReimbDate(rs.getDate(REIMB_DATE));
				reimb.setStatus(rs.getInt(REIMB_STATUS));
				reimb.setStatusString(getStatus(reimb));
				reimb.setSubmitDate(rs.getDate(REIMB_SUBMIT_DATE));
				// need to check whether or not the reply date date is null (it can be)
				final java.util.Date replyDate = rs.getDate(REIMB_REPLY_DATE);
				Logger.getRootLogger().debug("Submit Date: " + replyDate + " and rs.wasNull? " + rs.wasNull());
				if (!rs.wasNull()) {
					// if it's not null set it, if it is, don't
					reimb.setReplyDate(replyDate);
				}

				reimbs.add(reimb);
			}
		} catch (SQLException e) {
			Logger.getRootLogger().error(e.getMessage());
			return null;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					Logger.getRootLogger().error("Failed to close ResultSet: " + e.getMessage());
				}
			}
		}

		return reimbs;
	}

}
