package delegates;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewDelegate {

	public void renderView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String uri = req.getServletPath();
		//test message
		System.out.println("Render view called with "+uri);
		
		switch(uri) {
			case "/login":
				req.getRequestDispatcher("/static/Views/login.html").forward(req,resp);
				break;
			case "/index":
				req.getRequestDispatcher("/static/Views/index.html").forward(req,resp);
				break;
			case "/new_user":
				req.getRequestDispatcher("/static/Views/New_User.html").forward(req,resp);
				break;
			case "/profile":
				req.getRequestDispatcher("/static/Views/Profile.html").forward(req,resp);
				break;
			case "/static/Views/reimbursements.html":
				break;
			default:
				resp.sendError(404,"Page not found");
		}
		
	}
}
