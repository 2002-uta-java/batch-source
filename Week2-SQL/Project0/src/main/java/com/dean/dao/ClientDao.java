package com.dean.dao;

import com.dean.models.Client;

public interface ClientDao {

	public Client getClientById(int id);
	public Client getClientByUsername(String username);
	public int createClient(Client client);
	public boolean isUsernameUnique(Client client);
	
}
