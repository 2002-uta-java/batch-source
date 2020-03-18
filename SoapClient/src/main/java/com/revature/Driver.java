package com.revature;

import java.io.PrintWriter;
import java.util.List;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.revature.models.Employee;
import com.revature.services.EmployeeService;

public class Driver {
	public static final String ENDPOINT = "http://localhost:8080/SoapService/employees";

	public static void main(String[] args) {
		final JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

		factory.setServiceClass(EmployeeService.class); // setting service endpoint
		factory.setAddress(ENDPOINT); // set service location

		final LoggingInInterceptor inInterceptor = new LoggingInInterceptor();
		final LoggingOutInterceptor outInterceptor = new LoggingOutInterceptor();

		factory.getInInterceptors().add(inInterceptor);
		factory.getOutInterceptors().add(outInterceptor);

		inInterceptor.setPrintWriter(new PrintWriter(System.out));
		outInterceptor.setPrintWriter(new PrintWriter(System.out));

		final EmployeeService service = (EmployeeService) factory.create();

		final Employee newEmp = new Employee(3, "John Doe", "CEO");
		service.addEmployee(newEmp);

		final List<Employee> employees = service.getAllEmployees();

		for (final Employee e : employees) {
			System.out.println(e);
		}
	}
}
