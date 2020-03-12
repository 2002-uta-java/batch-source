package com.revature;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeeDaoImpl;
import com.revature.models.Employee;

public class Driver {

	public static void main(String[] args) {
		
//		EmployeeDao employeeDao = new EmployeeDaoImpl();
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		EmployeeDao employeeDao = (EmployeeDao) ac.getBean("employeeDaoImpl");
		
		List<Employee> employees = employeeDao.getAll();
		for(Employee e: employees) {
			System.out.println(e);
		}

	}

}
