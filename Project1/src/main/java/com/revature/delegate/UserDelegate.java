package com.revature.delegate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Client;
import com.revature.service.ClientService;
import com.revature.service.EmployeeService;

public class UserDelegate {
	private ClientService clientService = new ClientService();
	private EmployeeService employeeService = new EmployeeService();
	private ObjectMapper om = new ObjectMapper();
	private String clientEmail;
	private String clientPassword;
	private int clientId;
	private int clientPermission;
	private int emplId;
	

	public UserDelegate() {
		super();
	}

	public void clientAuth(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String clientLoginName = request.getParameter("email");
		String clientLoginPassword = request.getParameter("password");
		
		boolean result = clientService.clientAuth(clientLoginName, clientLoginPassword);
		if(result == true) {
			int id = employeeService.getEmployeeId(clientLoginName);
			this.emplId = id;
			String token = clientService.getClientId(clientLoginName) + ":"+ clientService.getClientPermission(emplId) + ":" + emplId + ":" + employeeService.getManagerId(emplId);
			response.setStatus(200);
			response.setHeader("Authorization", token);
			} else {
			response.sendError(401, "Unauthorized Access");
				}

			try(PrintWriter pw = response.getWriter()){
				pw.write(om.writeValueAsString(""));
			}
			
	}
	public boolean isAuthorized(HttpServletRequest request) {
		String authToken = request.getHeader("Authorization");
		if(authToken!=null) {
			String[] tokenArr = authToken.split(":");
			if(tokenArr.length == 4) {
				String id = tokenArr[0];
				String permission = tokenArr[1];
				if(id.matches("^\\d+$")) {
					int c = clientService.verfiyClientId(Integer.parseInt(id));
					int p = Integer.parseInt(permission);
					if(c!=0 && clientService.getClientPermission(c) == p) {
						return true;
					}
				}
			}
		}
		return false;
	}
		
	public void clientCreate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try (BufferedReader requestReader = request.getReader();) {
			String newClientJson = requestReader.readLine();	
			JSONArray jsonArr = new JSONArray(newClientJson);
		    for (int i = 0; i < jsonArr.length(); i++) {
		        JSONObject jsonObj = jsonArr.getJSONObject(i);
		        Client newClient = new Client(clientId, clientEmail, clientPassword, clientPermission);
		        clientId = newClient.setClientId(jsonObj.getInt("clientId"));
		        clientEmail = newClient.setClientEmail(jsonObj.getString("clientEmail"));
		        clientPassword = newClient.setClientPassword(jsonObj.getString("clientPassword"));
		        clientPermission = newClient.setClientPermissionId(jsonObj.getInt("clientPermission"));
		        clientService.createClientByFunction(newClient);
		    	}		
				try(PrintWriter pw = response.getWriter()) {
					pw.write(om.writeValueAsString("Client added")); 
				}
			}
	}
	
	public void updateClient(HttpServletRequest request, HttpServletResponse response) throws IOException {
				String b = request.getParameter("clientId");
				String a = request.getParameter("clientPassword");
				int b1 = Integer.parseInt(b);
		        Client updateClient = new Client(clientId, clientPassword);
		        clientId = updateClient.setClientId(b1);
		        clientPassword = updateClient.setClientPassword(a);
		        clientService.updateClientPasswordByFunction(updateClient);   
	}
}
		
