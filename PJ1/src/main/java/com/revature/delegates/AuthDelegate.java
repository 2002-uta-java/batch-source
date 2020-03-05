
package com.revature.delegates;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeeDaoImpl;
import com.revature.models.Employee;
import com.revature.services.EmployeeService;
import com.revature.daos.ManagerDao;
import com.revature.daos.ManagerDaoImpl;
import com.revature.models.Manager;
import com.revature.services.ManagerService;;

public class AuthDelegate {
	//using both our employees and mangers for authentification
	private EmployeeService empService = new EmployeeService();
	private ManagerService mService = new ManagerService();
	private EmployeeDao empd = new EmployeeDaoImpl();
	private ManagerDao md = new ManagerDaoImpl();
	
	
	/**
	 * comparing if user is an employee or manger
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// extract request parameters (i.e. data sent by posting a html form)
		// it gets them by their name
		String name = request.getParameter("name");
		String pw = request.getParameter("password1");
		String role = request.getParameter("workers");
		System.out.println("from quthentication" + request.toString());
		//our login objects hint this might not work
		Employee emp = null;
		Manager mg = null;
		
		//checking to see if we have an employee or manager
		if(role.equals("employee")) {
			  emp = empService.getEmpLogin(name, pw);
	
		}else if(role.equals("manager")) {
			 mg = mService.getManagerNameAndPw(name, pw);
		}
		
		//checking for null values
		if(emp != null) {
			//might need to get more vals later on
			String token = emp.getEmployeeName() + ":" + emp.getEmpId();
			response.setStatus(200);
			//setting our authorization tokens
			response.setHeader("Authorization", token);
		}else if(mg != null) {
			String token = mg.getName() + ":" + mg.getEmpId();
			response.setStatus(200);
			response.setHeader("Authorization", token);
		}else {
			response.setStatus(400);
		}
		System.out.println("from the end of auth" + request.toString() + " response" + response.toString());
	}
	/**checking to see if we have a manager
	 * 
	 * @param request
	 * @return
	 */
	public boolean isAuthorized(HttpServletRequest request) {
		String authTkn = request.getHeader("Authorization");
		
		//checking for our authentificaton header
		if(authTkn != null) {
			String[] tknArr = authTkn.split(":");
			if (tknArr.length == 2) {
				String username = tknArr[0];
				String id = tknArr[1];
				
				if (id.matches("^\\d+$")) {
					Employee e = empd.getEmployeeByName(username);
					
					if (e != null && e.getEmpId() == Integer.parseInt(id)) {
						return true;
					}
				}
			}
			
		}
		
		return false;
	}
	/**
	 * authenticating our manager
	 * @param request
	 * @return
	 */
	public boolean authManager(HttpServletRequest request) {
String token = request.getHeader("Authorization");
		
		if (token != null) {
			String[] tokenArr = token.split(":");
			
			if (tokenArr.length == 2) {
				String username = tokenArr[0];
				String id = tokenArr[1];
				
				if (id.matches("^\\d+$")) {
					Manager m = md.getMangerByName(username);
					
					if (m != null && m.getEmpId() == Integer.parseInt(id)) {
						return true;
					}
				}
			}
		}
		return false;
		
	}
public void authenticateM(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String username = request.getParameter("name");
		String password = request.getParameter("password");
		
		Manager m = md.getMangerByName(username);

		if (m.getName() != null && m.getPassWord().equals(password)) {
			String token = m.getName() + ":" + m.getEmpId();
			response.setStatus(200);
			response.setHeader("Authorization", token);
		} else {
			response.setStatus(400);
		}
		
	}
}
