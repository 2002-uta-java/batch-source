package com.revature;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.daos.EmployeeDao;
import com.revature.models.Employee;

public class Driver {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		final EmployeeDao employeeDao = (EmployeeDao) ac.getBean("employeeDaoImpl");

		List<Employee> employees = employeeDao.getAll();

		for (final Employee e : employees)
			System.out.println(e);

		((ConfigurableApplicationContext) ac).close();
	}
}
