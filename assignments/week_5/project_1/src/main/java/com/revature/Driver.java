package com.revature;

import java.sql.Connection;

import com.revature.models.Employee;
import com.revature.models.Profile;
import com.revature.models.Reimbursement;
import com.revature.services.CategoryService;
import com.revature.services.EmployeeService;
import com.revature.services.ProfileService;
import com.revature.services.ReimbursementService;
import com.revature.util.ConnectionUtil;

public class Driver {

	private static ProfileService ps = new ProfileService();
	private static EmployeeService es = new EmployeeService();
	private static ReimbursementService rs = new ReimbursementService();
	private static CategoryService cs = new CategoryService();
	
	public static void main(String[] args) {
		
//		Profile p = new Profile("lolap", "1234", 1);
//		int idUsed = ps.createProfile(p);
//		Employee em = new Employee(idUsed, "Human Resources", "Lola", "Palmer", "5555550001", "lolap@mail.com", "I live here");
//		es.createEmployee(em);
//		
//		Employee e = new Employee();
//		e.setId(2);
//		
//		System.out.println(es.getEmployee(e));
//		
//		e.setPhone("5551230000");
//		e.setEmail("jortiz@revature.com");
//		e.setAddress("411 North Toll, Dallas, TX, 70643");
//		
//		System.out.println(es.updateEmployee(e));
//
//		System.out.println(es.getAllEmployees());
//		
//		Profile pro = new Profile();
//		
//		pro.setUser("lolap");
//		pro.setPassword("1234");
//		
//		System.out.println(ps.validateProfile(pro));
//		
//		try {
//			Connection con = ConnectionUtil.getConnection();
//			String driverName = con.getMetaData().getDriverName();
//			System.out.println(driverName);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//		Reimbursement rem = new Reimbursement();
//		rem.setId(4);
//		rem.setBatch(2);
//		rem.setEmployee(2);
//		rem.setCategoryById(6);
//		rem.setAmount(75);
//		rem.setStatusById(4);
//		rem.setApprovedBy(3);
//		
//		System.out.println(rs.createReimbursement(rem));
		
//		System.out.println(rs.getReimbursementById(4));
		
//		System.out.println(rs.updateReimbursement(rem));
		
//		Employee em = new Employee();
//		em.setId(2);
//		
//		System.out.println(rs.getAllReimbursements(em));
		
//		System.out.println(cs.getAllCategories());
		
		
	}
}
