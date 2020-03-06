package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import delegates.*;

public class RequestHelper {
	private UserDelegate userDelegate = new UserDelegate();
	private LoginDelegate loginDelegate = new LoginDelegate();
	private ViewDelegate viewDelegate = new ViewDelegate();
	
	public void delegateGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String path = req.getServletPath();
		System.out.println("IT'S ALLLLIIIIIVVVVVEEEE "+path);
		if(path.startsWith("/api/")) {
			
			
			
			String record = path.substring(5);
			System.out.println("	substring "+record);
			switch (record) {
			case "userProfile":
				userDelegate.getUserProfile(req, resp);
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
		case "/create_user":
			userDelegate.createNewUser(req,resp);
			break;
		default:
			resp.sendError(405);
		}
	}


}
