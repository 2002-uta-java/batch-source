package com.revature.jfbennatt.ers.daos.hbm;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.revature.jfbennatt.connections.HibernateUtil;
import com.revature.jfbennatt.ers.daos.EmployeeDao;
import com.revature.jfbennatt.ers.models.Employee;
import com.revature.jfbennatt.ers.models.Reimbursement;

public class EmployeeDaoHBM implements EmployeeDao {

	@Override
	public boolean deleteSessionToken(String token) {
		try (final Session session = HibernateUtil.getSession()) {

			// Find employee by session token and set their token to null
			final String hql = "update employees e set e.sessionToken = null where e.sessionToken =: token";
			final Query query = session.createQuery(hql);
			query.setParameter("token", token);
			final int updated = query.executeUpdate();
			if (updated != 1) {
				Logger.getRootLogger().error("Failed to delete token: " + token);
			}

			return updated == 1;
		}
	}

	@Override
	public Employee getEmployeeByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getEmployeeByToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTokenLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean setTokenById(int empId, String token) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String datePattern() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean submitRequest(Reimbursement newReimb) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getStatus(Reimbursement reimb) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPending(Reimbursement reimb) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setApproved(Reimbursement reimb) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDenied(Reimbursement reimb) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Reimbursement> getAllReimbursementsByEmployeeId(int empId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changeProfile(Employee employee) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Reimbursement> getPendingReimbursementsByEmployeeId(int empId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> getProcessedReimbursementsByEmployeeId(int empId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getAllEmployeesExceptManager(int manId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> getAllPendingRequestsExceptManager(int manId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean approveRequest(int manId, int reimbId, Date date) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean rejectRequest(int manId, int reimbId, Date date) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Reimbursement> getAllProcessedRequests() {
		// TODO Auto-generated method stub
		return null;
	}

}
