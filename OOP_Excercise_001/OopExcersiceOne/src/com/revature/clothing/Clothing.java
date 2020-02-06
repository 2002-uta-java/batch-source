package com.revature.clothing;

// Here we find another example of Abstraction. Clothing is an abstract class, thus
// we do not have to implement the inherited methods from the interface. In Clothing
// we also see encapsulation. There a group of set and get methods that are protecting
// the private instance variables from direct manipulation

public abstract class Clothing implements Wearable {
	
	// set to private to use Encapsulation
	private String brand = new String();
	private String color = new String();
	private String material = new String();
	
	public boolean status;
	
	public Clothing() {
		super();
	}
	
	// get and set methods to implement encapsulation
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
		
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
		
	}
	
	public String getMaterial() {
		return material;
	}
	
	public void setMaterial(String material) {
		this.material = material;
		
	}

	public void wearStatus(boolean status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "[Brand= " + getBrand() + ", color= " + getColor() + ", material= " + getMaterial() + ", currently wearing= " + status+"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((material == null) ? 0 : material.hashCode());
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
		Clothing other = (Clothing) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (material == null) {
			if (other.material != null)
				return false;
		} else if (!material.equals(other.material))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
}