package com.dean.phones;

public class IPhoneXS extends IPhoneX {

	private String color;
	
	public IPhoneXS() {
		super();
	}
	
	public IPhoneXS(String color) {
		this.color = color;
	}
	
	@Override
	public String toString() {
		return "IPhoneXS [color=" + color + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((color == null) ? 0 : color.hashCode());
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
		IPhoneXS other = (IPhoneXS) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		return true;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public void ring() {
		System.out.println("IPhoneXS is ringing");
	}
}
