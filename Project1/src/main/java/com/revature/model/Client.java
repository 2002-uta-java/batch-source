package com.revature.model;

import java.io.Serializable;
import java.util.Objects;

public class Client implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int clientId;
	private String clientEmail;
	private String clientPassword;
	private int clientPermissionId;

	public Client() {
		super();
	}

	public Client(int clientId, String clientEmail, String clientPassword, int clientPermissionId) {
		super();
		this.clientId = clientId;
		this.clientEmail = clientEmail;
		this.clientPassword = clientPassword;
		this.clientPermissionId = clientPermissionId;
	}

	public int getClientId() {
		return clientId;
	}

	public int setClientId(int clientId) {
		return this.clientId = clientId;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public String setClientEmail(String clientEmail) {
		return this.clientEmail = clientEmail;
	}

	public String getClientPassword() {
		return clientPassword;
	}

	public String setClientPassword(String clientPassword) {
		return this.clientPassword = clientPassword;
	}

	public int getClientPermissionId() {
		return clientPermissionId;
	}

	public int setClientPermissionId(int clientPermissionId) {
		return this.clientPermissionId = clientPermissionId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(clientEmail, clientId, clientPassword, clientPermissionId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(clientEmail, other.clientEmail) && clientId == other.clientId
				&& Objects.equals(clientPassword, other.clientPassword)
				&& clientPermissionId == other.clientPermissionId;
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", clientEmail=" + clientEmail + ", clientPassword=" + clientPassword
				+ ", clientPermissionId=" + clientPermissionId + "]";
	}

}
