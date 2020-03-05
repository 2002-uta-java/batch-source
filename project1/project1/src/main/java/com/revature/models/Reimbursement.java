package com.revature.models;

import java.io.Serializable;

public class Reimbursement implements Serializable{
	private static final long serialVersionUID = 1L;

	int rid;
	Employee e;
	String status;
	double expense;
	String resolver;
	String rtype;
	
	public Reimbursement() {
		super();
	}
	
	public Reimbursement(int rid, Employee e, String status, double expense, String resolver, String rtype) {
		this.rid = rid;
		this.e = e;
		this.status = status;
		this.expense = expense;
		this.resolver = resolver;
		this.rtype = rtype;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public Employee getE() {
		return e;
	}

	public void setE(Employee e) {
		this.e = e;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getExpense() {
		return expense;
	}

	public void setExpense(double expense) {
		this.expense = expense;
	}

	public String getResolver() {
		return resolver;
	}

	public void setResolver(String resolver) {
		this.resolver = resolver;
	}

	public String getRtype() {
		return rtype;
	}

	public void setRtype(String rtype) {
		this.rtype = rtype;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((e == null) ? 0 : e.hashCode());
		long temp;
		temp = Double.doubleToLongBits(expense);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((resolver == null) ? 0 : resolver.hashCode());
		result = prime * result + rid;
		result = prime * result + ((rtype == null) ? 0 : rtype.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Reimbursement other = (Reimbursement) obj;
		if (e == null) {
			if (other.e != null)
				return false;
		} else if (!e.equals(other.e))
			return false;
		if (Double.doubleToLongBits(expense) != Double.doubleToLongBits(other.expense))
			return false;
		if (resolver == null) {
			if (other.resolver != null)
				return false;
		} else if (!resolver.equals(other.resolver))
			return false;
		if (rid != other.rid)
			return false;
		if (rtype == null) {
			if (other.rtype != null)
				return false;
		} else if (!rtype.equals(other.rtype))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [rid=" + rid + ", e=" + e + ", status=" + status + ", expense=" + expense + ", resolver="
				+ resolver + ", rtype=" + rtype + "]";
	}
}
