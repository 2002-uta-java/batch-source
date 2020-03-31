package xyz.revature.delegates;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.revature.daos.EmployeeDao;
import xyz.revature.daos.EmployeeDaoImpl;
import xyz.revature.models.Employee;
import xyz.revature.services.EmployeeService;

public class AuthDelegate {
	private EmployeeDao empDao = new EmployeeDaoImpl();
	
	public void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username + " " + password);
		Employee e = empDao.getAuthEmployee(username, password);
		if(e!=null) {
			String token = e.getId() + ":" + username + ":" + e.getPosition() + ":" + e.getSalary() + ":" + e.isManagement();
			System.out.println(token);
			response.setStatus(200);
			response.setHeader("Authorization", token);
		} else {
			response.sendError(401);
		}
	}
	
	public boolean isAuthorized(HttpServletRequest request) {
		String authToken = request.getHeader("Authorization");
		if(authToken!=null) {
			String[] tokenArr = authToken.split(":");
			if(tokenArr.length == 5) {
				String idStr = tokenArr[0];
				String managerStr = tokenArr[4];
				if (idStr.matches("^\\d+$") && Boolean.parseBoolean(managerStr)) {
					Employee e = empDao.getEmployee(Integer.parseInt(idStr));
					boolean manager = Boolean.valueOf(managerStr);
					if(e!=null && e.isManagement() == manager) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String position = request.getParameter("position");
		String salaryStr	= request.getParameter("salary");
		String managementStr = request.getParameter("management");
		
		int uid = empDao.getIdByName(username);
		if (uid == 0) {
			Employee e = new Employee();
			e.setName(username);
			e.setPassword(password);
			e.setPosition(position);
			int salary = 0;
			if (salaryStr.matches("-?(0|[1-9]\\d*)")) {
				salary = Integer.parseInt(salaryStr);
			}
			e.setSalary(salary);
			e.setManagement(Boolean.parseBoolean(managementStr));
			int employeeId = empDao.addEmployee(e);
			e.setId(employeeId);
			
			String token = employeeId + ":" + username + ":" + position + ":" + salary + ":" + e.isManagement();
			System.out.println(token);
			response.setStatus(200);
			response.setHeader("Authorization", token);
		}

	}
}
