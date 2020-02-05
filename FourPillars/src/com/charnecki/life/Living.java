package com.charnecki.life;

// Abstract class
public abstract class Living {
	
	// declaring fields
	private String type;
	private int age;
	private Boolean alive = true;

	// Constructor sets default type
	public Living() {
		super();
		this.setType("Living Thing");
	}
	
	// abstract method for abstraction
	public abstract void birth();
	
	// concrete method for death
	public void die() {
		System.out.println("A "+type+" has died.");
		this.setAlive(false);
	}
	
	// getters/setters for fields
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public Boolean getAlive() {
		return alive;
	}

	public void setAlive(Boolean alive) {
		this.alive = alive;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if (age >= 0) {
			this.age = age;
		} else {
			// Throws an exception if someone's age is set to a negative number
			throw new NegativeAgeException();
		}
	}
	
	// Overrides
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((alive == null) ? 0 : alive.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Living other = (Living) obj;
		if (age != other.age)
			return false;
		if (alive == null) {
			if (other.alive != null)
				return false;
		} else if (!alive.equals(other.alive))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
}
