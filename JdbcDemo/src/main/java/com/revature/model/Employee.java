package com.revature.model;

public class Employee {
	private Department dept;
	private int id;
	private int managerId;
	private double monthlySalary;
	private String name;
	private String position;

	public Employee() {
		super();
	}

	public Employee(int id, String name, double monthlySalary, String position, int managerId, Department deptId) {
		super();
		this.id = id;
		this.name = name;
		this.monthlySalary = monthlySalary;
		this.position = position;
		this.managerId = managerId;
		this.dept = deptId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (dept == null) {
			if (other.dept != null)
				return false;
		} else if (!dept.equals(other.dept))
			return false;
		if (id != other.id)
			return false;
		if (managerId != other.managerId)
			return false;
		if (Double.doubleToLongBits(monthlySalary) != Double.doubleToLongBits(other.monthlySalary))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

	public Department getDeptId() {
		return dept;
	}

	public int getId() {
		return id;
	}

	public int getManagerId() {
		return managerId;
	}

	public double getMonthlySalary() {
		return monthlySalary;
	}

	public String getName() {
		return name;
	}

	public String getPosition() {
		return position;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dept == null) ? 0 : dept.hashCode());
		result = prime * result + id;
		result = prime * result + managerId;
		long temp;
		temp = Double.doubleToLongBits(monthlySalary);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		return result;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public void setMonthlySalary(double monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", monthlySalary=" + monthlySalary + ", position=" + position
				+ ", managerId=" + managerId + ", deptId=" + dept + "]";
	}

}
