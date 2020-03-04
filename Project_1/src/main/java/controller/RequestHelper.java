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
		String path = req.getRequestURI().substring(req.getContextPath().length());
		
		if(path.startsWith("/api/")) {
			String record = path.substring(5);
			switch (record) {
			case "/user":
				break;
			case "/profile":
				break;
			case "/reimbursements":
				break;
			case "/login":
				loginDelegate.authenticate(req,resp);
				break;
			default:
				resp.sendError(404,"Path not supported");
			}
		}else {
			viewDelegate.renderView(req, resp);
		}
	}

}
