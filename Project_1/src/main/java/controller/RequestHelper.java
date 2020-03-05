package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import delegates.*;

public class RequestHelper {
	private UserDelegate userDelegate = new UserDelegate();
	private ReimbDelegate reimbDelegate = new ReimbDelegate();
	private LoginDelegate loginDelegate = new LoginDelegate();
	private ViewDelegate viewDelegate = new ViewDelegate();
	
	public void delegateGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String path = req.getServletPath();
		System.out.println("IT'S ALLLLIIIIIVVVVVEEEE "+path);
		if(path.startsWith("/api/")) {
			
			if(!loginDelegate.authorized(req)) {
				resp.sendError(401);
				return;
			}
			
			String record = path.substring(5);
			switch (record) {
			case "index":
				req.getRequestDispatcher("/static/Views/index.html").forward(req,resp);
				break;
			case "profile":
				req.getRequestDispatcher("/static/Views/profile.html").forward(req,resp);
				break;
			case "reimbursements":
				break;
			case "login":
				//req.getRequestDispatcher("/static/Views/Login.html").forward(req,resp);
				loginDelegate.authenticate(req, resp);
				break;
			default:
				resp.sendError(404,"Path not supported");
			}
		}else {
			viewDelegate.renderView(req, resp);
		}
	}
	
	public void delegatePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String path = req.getServletPath();
		System.out.println("IT'S A POOOOOOOOOSSSSSST "+path);
		switch(path) {
		case "/ulogin":
			loginDelegate.authenticate(req, resp);
			break;
		case "/userProfile":
			userDelegate.getUser(req, resp);
			break;
		default:
			resp.sendError(405);
		}
	}


}
