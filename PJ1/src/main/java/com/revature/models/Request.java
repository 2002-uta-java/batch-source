/**
 * 
 */
package com.revature.models;

/**
 * @author erpac
 *
 */
public class Request {
	String employeeName;
	boolean status;
	String aprovedBy;
	int id;
	String name;
	/**
	 * 
	 */
	public Request() {
		// TODO Auto-generated constructor stub
	}
	public Request(String employeeName, boolean status, String aprovedBy, int id, String name) {
		super();
		this.employeeName = employeeName;
		this.status = status;
		this.aprovedBy = aprovedBy;
		this.id = id;
		this.name = name;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getAprovedBy() {
		return aprovedBy;
	}
	public void setAprovedBy(String aprovedBy) {
		this.aprovedBy = aprovedBy;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aprovedBy == null) ? 0 : aprovedBy.hashCode());
		result = prime * result + ((employeeName == null) ? 0 : employeeName.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (status ? 1231 : 1237);
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
		Request other = (Request) obj;
		if (aprovedBy == null) {
			if (other.aprovedBy != null)
				return false;
		} else if (!aprovedBy.equals(other.aprovedBy))
			return false;
		if (employeeName == null) {
			if (other.employeeName != null)
				return false;
		} else if (!employeeName.equals(other.employeeName))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Request [employeeName=" + employeeName + ", status=" + status + ", aprovedBy=" + aprovedBy + ", id="
				+ id + ", name=" + name + "]";
	}
	
	

}
