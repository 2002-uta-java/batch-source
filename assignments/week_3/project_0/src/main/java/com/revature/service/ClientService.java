package com.revature.service;

import com.revature.dao.ClientDao;
import com.revature.dao.ClientDaoImpl;
import com.revature.models.Client;

public class ClientService {
	
	private ClientDao cd = new ClientDaoImpl();
	
	public Client createClient(Client c) {
		return cd.createClient(c);
	}
	
	public Client getClientById(int id) {
		return cd.getClientById(id);
	}
	
}
