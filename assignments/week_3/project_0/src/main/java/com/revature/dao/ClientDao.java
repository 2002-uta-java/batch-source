package com.revature.dao;

import com.revature.models.Client;

public interface ClientDao {
	
	public Client createClient(Client c);
	public Client getClientById(int id);

}
