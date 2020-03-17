package com.revature;

import org.springframework.web.client.RestTemplate;

import com.revature.models.Employee;

public class Driver {

	public static void main(String[] args) {
		
		
		RestTemplate restTemplate = new RestTemplate();
		
		String url = "http://localhost:8082/employees/2";
		
		Employee e = restTemplate.getForObject(url, Employee.class);
		System.out.println(e);
		
//		String postUrl = "http://localhost:8082/employees";
//		
//		Employee e = new Employee("Bob Smith", "IT Rep");
//		restTemplate.postForObject(postUrl, e, Employee.class);

	}

}
