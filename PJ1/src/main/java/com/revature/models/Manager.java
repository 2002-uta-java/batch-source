/**
 * 
 */
package com.revature.models;

/**
 * @author erpac
 *
 */
public class Manager {
	
	String name;
	String passWord;
	String email;
	int aprovedRequests;
	int empId;
	
	public Manager() {
		// TODO Auto-generated constructor stub
	}

	
	public Manager(String name, String passWord, String email, int aprovedRequests, int empId) {
		super();
		this.name = name;
		this.passWord = passWord;
		this.email = email;
		this.aprovedRequests = aprovedRequests;
		this.empId = empId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassWord() {
		return passWord;
	}


	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getAprovedRequests() {
		return aprovedRequests;
	}


	public void setAprovedRequests(int aprovedRequests) {
		this.aprovedRequests = aprovedRequests;
	}


	public int getEmpId() {
		return empId;
	}


	public void setEmpId(int empId) {
		this.empId = empId;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aprovedRequests;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + empId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((passWord == null) ? 0 : passWord.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manager other = (Manager) obj;
		if (aprovedRequests != other.aprovedRequests)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (empId != other.empId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (passWord == null) {
			if (other.passWord != null)
				return false;
		} else if (!passWord.equals(other.passWord))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Manager [name=" + name + ", passWord=" + passWord + ", email=" + email + ", aprovedRequests="
				+ aprovedRequests + ", empId=" + empId + "]";
	}


	

}
