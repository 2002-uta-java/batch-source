package com.revature.model;

import java.sql.Time;

public class Reimbursment {
	private int id = 0;
	private int amount = 0;
	private String stage = "";
	private Time time = null;
	private int employeeId = 0;
	
	public Reimbursment() {}
	public Reimbursment(int id, int amount, String stage, Time time, int employeeId) {
		super();
		this.id = id;
		this.amount = amount;
		this.stage = stage;
		this.time = time;
		this.employeeId = employeeId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + employeeId;
		result = prime * result + id;
		result = prime * result + ((stage == null) ? 0 : stage.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		Reimbursment other = (Reimbursment) obj;
		if (amount != other.amount)
			return false;
		if (employeeId != other.employeeId)
			return false;
		if (id != other.id)
			return false;
		if (stage == null) {
			if (other.stage != null)
				return false;
		} else if (!stage.equals(other.stage))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursment [id=" + id + ", amount=" + amount + ", stage=" + stage + ", time=" + time + ", employeeId="
				+ employeeId + "]";
	}
}
