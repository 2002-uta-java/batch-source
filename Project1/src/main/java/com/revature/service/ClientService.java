package com.revature.service;

import java.util.List;

import com.revature.dao.ClientDao;
import com.revature.dao.ClientDaoImpl;
import com.revature.model.Client;

public class ClientService {
	
	private ClientDao clientDao = new ClientDaoImpl();
	private boolean result;

	public ClientService() {
		super();
	}
	
	public List<Client> getClient() {
		return clientDao.getClient();
	}
	
	public int getClientId(String email) {
		return clientDao.getClientId(email);
	}
	
	public String getClientEmail(int id) {
		return clientDao.getClientEmail(id);
	}
	
	public String getClientPassword(int id) {
		return clientDao.getClientPassword(id);
	}
	
	public Client createClientByFunction(Client create) {
	   return clientDao.createClientByFunction(create);	
	}
	
	public Client updateClientPasswordByFunction(Client update) {
		return clientDao.updateClientPasswordByFunction(update);
	}
	
	public int getClientPermission(int id) {
		return clientDao.getClientPermission(id);
	}
	
	public int updateClientPermission(int permission, int id) {
		return clientDao.updateClientPermission(permission, id);
	}
	
	public int deleteClient(int id) {
		return clientDao.deleteClient(id);
	}
	
	public boolean clientAuth(String email, String password) {
		List<Client> newList = clientDao.getClient();
		for (Client c : newList) {
			if(c.getClientEmail() != null && c.getClientEmail().equals(email)) {
				if(c.getClientPassword() != null && c.getClientPassword().equals(password)) {
					return result;
				} 
			}
		}
		return result;
	}
	
	public int verfiyClientId(int id) {
		return clientDao.verfiyClientId(id);
	}

}
