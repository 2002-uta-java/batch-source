package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.models.Profile;
import com.revature.services.ProfileService;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ProfileService ps = new ProfileService();
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
				
		Profile pr = new Profile();
		
		pr.setUser(username);
		pr.setPassword(password);
		
		Profile pro = ps.validateProfile(pr);
				
		if(pro.getId() > 0) {
			String token = pro.getEmployeeId() + ":" + pro.getIsManager();
			response.setStatus(200);
			response.setHeader("AuthToken", token);
		} else {
			response.sendError(401);
		}
	}
}
