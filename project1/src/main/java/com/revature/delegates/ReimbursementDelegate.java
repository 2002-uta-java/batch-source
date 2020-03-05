package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;

public class ReimbursementDelegate {
	private static final Logger logger = LogManager.getLogger("ERS");
	private ReimbursementService rService = new ReimbursementService();
	
	public void listReimbursements(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//String reimbursementType = request.getParameter("r");
		//logger.info("listReimbursements parameter r = " + reimbursementType);
		List<Reimbursement> rList = rService.getAll();
		
		ObjectMapper om = new ObjectMapper();
		String rJSON = om.writeValueAsString(rList);
		
		try(PrintWriter pw = response.getWriter();){
			pw.write(rJSON);
			//pw.write("[{\"description\": \"some stuff\", \"status\":\"pending\"},{\"description\": \"other junk\", \"status\":\"resolved\"}]");
		}
		
	}

}
