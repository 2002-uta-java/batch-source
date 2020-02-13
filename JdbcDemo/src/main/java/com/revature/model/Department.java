package com.revature.model;

import java.io.Serializable;

public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	private String dept_name;
	private int id;
	private double monthly_budget;

	public Department() {
		super();
	}

	public Department(int id, String dept_name, double monthly_budget) {
		super();
		this.id = id;
		this.dept_name = dept_name;
		this.monthly_budget = monthly_budget;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		if (dept_name == null) {
			if (other.dept_name != null)
				return false;
		} else if (!dept_name.equals(other.dept_name))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(monthly_budget) != Double.doubleToLongBits(other.monthly_budget))
			return false;
		return true;
	}

	public String getDept_name() {
		return dept_name;
	}

	public int getId() {
		return id;
	}

	public double getMonthly_budget() {
		return monthly_budget;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dept_name == null) ? 0 : dept_name.hashCode());
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(monthly_budget);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMonthly_budget(double monthly_budget) {
		this.monthly_budget = monthly_budget;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", dept_name=" + dept_name + ", monthly_budget=" + monthly_budget + "]";
	}

}
