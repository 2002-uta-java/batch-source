package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import delegates.*;

public class RequestHelper {
	private UserDelegate userDeleegate = new UserDelegate();
	private ProfileDelegate profileDelegate = new ProfileDelegate();
	private ReimbDelegate reimbDelegate = new ReimbDelegate();
	private LoginDelegate loginDelegate = new LoginDelegate();
	private ViewDelegate viewDelegate = new ViewDelegate();
	
	public void delegateTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String path = req.getServletPath();
		System.out.println("IT'S ALLLLLiiIVE "+path);
		if(path.startsWith("/api/")) {
			String record = path.substring(5);
			switch (record) {
			case "index":
				req.getRequestDispatcher("/static/Views/index.html").forward(req,resp);
				break;
			case "profile":
				break;
			case "reimbursements":
				break;
			case "login":
				req.getRequestDispatcher("/static/Views/Login.html").forward(req,resp);
				break;
			default:
				resp.sendError(404,"Path not supported");
			}
		}else {
			viewDelegate.renderView(req, resp);
		}
	}

}
