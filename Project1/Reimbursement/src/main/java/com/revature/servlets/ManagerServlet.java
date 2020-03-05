package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daos.ManagerDao;
import com.daos.ManagerDaoImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.Manager;

public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ManagerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		
		ManagerDao mdi = new ManagerDaoImpl();
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();
		
		if(idStr != null) {
			int id = Integer.parseInt(idStr);
			Manager m = mdi.getManagerById(id);
			
			if (m.getId() == 0) {
				pw.print("Invalid Manager id");
			} else {
				String managerString = om.writeValueAsString(m);
				pw.write(managerString);
			}
		} else {
			List<Manager> managers = mdi.getManagers();
			
			String managerString = om.writeValueAsString(managers);
			
			managerString = "{\"managers\":"+managerString+"}";
			
			pw.println(managerString);
		}
		
	}

// unimplemented method
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}