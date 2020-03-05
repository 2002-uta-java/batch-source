package com.dean.managers;

import java.util.ArrayList;
import java.util.List;

import com.dean.daos.EmployeeDao;
import com.dean.daos.EmployeeDaoImpl;
import com.dean.models.Employee;
import com.dean.models.Reimbursement;

public class ReimbursementManager {

	public List<Reimbursement> connectEmployeeERB(List<Reimbursement> reimbursements) {
		List<Reimbursement> empArr = new ArrayList<>();
		empArr.addAll(reimbursements);
		EmployeeDao ed = new EmployeeDaoImpl();
		
		for (Reimbursement r : empArr) {
			Employee emp = ed.getEmployeeById(r.getEmployeeId());
			emp.setPassword("");
			r.setEmployee(emp);
			
			if (r.getManagerId() != 0) {
				Employee mgmt = ed.getEmployeeById(r.getManagerId());
				r.setManager(mgmt);
			}
		}
		
		return empArr;
	}
}
