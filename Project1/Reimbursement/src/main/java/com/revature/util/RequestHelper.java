package com.revature.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.daos.ProfileDao;
import com.daos.ProfileDaoImpl;
import com.daos.ReimbursementDao;
import com.daos.ReimbursementDaoImpl;
import com.models.Profile;
import com.models.Reimbursement;

public class RequestHelper {

	public static String process(HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		int id = Integer.parseInt(session.getAttribute("id").toString());
		ReimbursementDao rdi = new ReimbursementDaoImpl();
		Reimbursement r = null;
		switch(request.getParameter("Submit")) {
			case "Update":
				ProfileDao pdi = new ProfileDaoImpl();
				Profile p = pdi.getProfileById(id);
				if(request.getParameter("firstname") != "") {
					p.setFirstname(request.getParameter("firstname"));
				}
				if(request.getParameter("lastname") != "" ) {
					p.setLastname(request.getParameter("lastname"));
				}
				if(request.getParameter("phone") != "") {
					p.setPhone(Long.parseLong(request.getParameter("phone")));
				}
				if(request.getParameter("email") != "") {
					p.setEmail(request.getParameter("email"));
				}
				pdi.updateProfile(p);
				return "Update";
			case "Create":
				r = new Reimbursement();
				r.setEmpId(id);
				r.setStatus("PENDING");
				if(request.getParameter("decription")  != "") {
					r.setDescription(request.getParameter("description"));
					rdi.createReimbursement(r);
				}
				return "Create";
			case "Handle":
				int RId = Integer.parseInt(request.getParameter("RId").toString());
				r = rdi.getReimbursementByRId(RId);
				r.setManId(id);
				r.setStatus("RESOLVED");
				r.setAction(request.getParameter("action"));
				rdi.updateReimbursement(r);
				return "Handle";
			case "Logout":
			default:
				session.invalidate();
				return "Logout";
		}
	}
}
