package com.revature.pillars;

public class Tenet extends Pillar {
	private String detail;
	
	public Tenet() {
		detail = "";
	}
	public Tenet(String name) {
		super(name);
		detail = "";
	}
	public Tenet(String name, String detail) {
		super(name);
		this.detail = detail;
	}
	
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String toString() {
		return name + ": " + detail;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((detail == null) ? 0 : detail.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tenet other = (Tenet) obj;
		if (detail == null) {
			if (other.detail != null)
				return false;
		} else if (!detail.equals(other.detail))
			return false;
		return true;
	}
}
