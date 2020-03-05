package com.revature.servlets.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.LoggerClass;
import com.revature.models.Category;
import com.revature.services.CategoryService;

/**
 * Servlet implementation class CategoryServlet
 */
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CategoryService cs = new CategoryService();
	private static LoggerClass lc = new LoggerClass();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Category> catList = new ArrayList<>();
		
		catList = cs.getAllCategories();
		
		ObjectMapper om = new ObjectMapper();
		
		String catJson = om.writeValueAsString(catList);
		
		try (PrintWriter pw = response.getWriter();) {
			pw.write(catJson);
			response.setStatus(200);
			lc.postInfoLog("categories retrieved for create reimbursement form");
		}
	}

}
