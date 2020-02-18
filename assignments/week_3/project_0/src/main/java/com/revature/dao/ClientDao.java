package com.revature.dao;

import com.revature.models.Client;

public interface ClientDao {
	
	public int createClient();
	public Client createClient(Client c);
	public Client getClientById(int id);
	public int updateClient();
	public int removeClient();

}
