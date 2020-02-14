package com.revature.model;

import java.io.Serializable;

public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	private String deptName;
	private int id;
	private double monthlyBudget;

	public Department() {
		super();
	}

	public Department(final String name, final double budget) {
		this.deptName = name;
		this.monthlyBudget = budget;
	}

	public Department(int id, String dept_name, double monthly_budget) {
		super();
		this.id = id;
		this.deptName = dept_name;
		this.monthlyBudget = monthly_budget;
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
		if (deptName == null) {
			if (other.deptName != null)
				return false;
		} else if (!deptName.equals(other.deptName))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(monthlyBudget) != Double.doubleToLongBits(other.monthlyBudget))
			return false;
		return true;
	}

	public String getName() {
		return deptName;
	}

	public int getId() {
		return id;
	}

	public double getMonthlyBudget() {
		return monthlyBudget;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deptName == null) ? 0 : deptName.hashCode());
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(monthlyBudget);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	public void setDept_name(String dept_name) {
		this.deptName = dept_name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMonthly_budget(double monthly_budget) {
		this.monthlyBudget = monthly_budget;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", dept_name=" + deptName + ", monthly_budget=" + monthlyBudget + "]";
	}

}
