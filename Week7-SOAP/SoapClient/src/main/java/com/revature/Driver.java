package com.revature;

import java.io.PrintWriter;
import java.util.List;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.revature.models.Employee;
import com.revature.services.EmployeeService;

public class Driver {

	public static void main(String[] args) {
		String endpoint = "http://localhost:8080/SoapService/employees";
		
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(EmployeeService.class); // setting service endpoint interface
		factory.setAddress(endpoint); // set service location

		LoggingInInterceptor inInterceptor = new LoggingInInterceptor();
		LoggingOutInterceptor outInterceptor = new LoggingOutInterceptor();
		
		factory.getInInterceptors().add(inInterceptor);
		factory.getOutInterceptors().add(outInterceptor);
		
		inInterceptor.setPrintWriter(new PrintWriter(System.out));
		outInterceptor.setPrintWriter(new PrintWriter(System.out));
		
		EmployeeService service = (EmployeeService) factory.create();
		
		Employee newEmployee = new Employee(3, "John Doe", "CEO");
		System.out.println(service.addEmployee(newEmployee));
		
		List<Employee> employees = service.getAllEmployees();
		for(Employee e: employees) {
			System.out.println(e);
		}
		
	}

}
