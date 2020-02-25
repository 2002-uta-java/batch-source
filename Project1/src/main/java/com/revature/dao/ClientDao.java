package com.revature.dao;

import java.util.List;

import com.revature.model.Client;

public interface ClientDao {
	
	public List<Client> getClient();
	public int getClientId(String email);
	public String getClientEmail(int id);
	public String getClientPassword(int id);
	public Client createClientByFunction(Client create);
	public Client updateClientPasswordByFunction(Client update);
	public int getClientPermission(int id);
	public int updateClientPermission(int permission, int id);
	public int deleteClient(int id);
	

}
