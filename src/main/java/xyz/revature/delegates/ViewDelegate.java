package xyz.revature.delegates;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewDelegate {
	
	public void resolveView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		switch(path) {
		case "/":
			request.getRequestDispatcher("/static/Views/home.html").forward(request, response);
			break;
		case "/approval":
			request.getRequestDispatcher("/static/Views/approval.html").forward(request, response);
			break;
		case "/reimbursement":
			request.getRequestDispatcher("/static/Views/reimbursement.html").forward(request, response);
			break;
		case "/register":
			request.getRequestDispatcher("/static/Views/register.html").forward(request, response);
			break;
		case "/signin":
			request.getRequestDispatcher("/static/Views/signin.html").forward(request, response);
			break;
		case "/home":
			request.getRequestDispatcher("/static/Views/home.html").forward(request, response);
			break;
		case "/expense":
			request.getRequestDispatcher("/static/Views/expense.html").forward(request, response);
			break;
		case "/directory":
			request.getRequestDispatcher("/static/Views/directory.html").forward(request, response);
			break;
		case "/status":
			request.getRequestDispatcher("/static/Views/status.html").forward(request, response);
			break;
		default:
			response.sendError(404, "Static Resource Not Found");
		}
	}
}
	