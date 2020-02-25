package com.revature.models;

import java.io.Serializable;

public class Bird implements Serializable {
	/**
	 * default from eclipse
	 */
	private static final long serialVersionUID = 1L;
	public Bird(String breed, int id, String name, int wingspan) {
		super();
		this.breed = breed;
		this.id = id;
		this.name = name;
		this.wingspan = wingspan;
	}

	private String breed;
	private int id;
	private String name;
	private int wingspan;

	public Bird() {
		super();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bird other = (Bird) obj;
		if (breed == null) {
			if (other.breed != null)
				return false;
		} else if (!breed.equals(other.breed))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (wingspan != other.wingspan)
			return false;
		return true;
	}

	public String getBreed() {
		return breed;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getWingspan() {
		return wingspan;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((breed == null) ? 0 : breed.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + wingspan;
		return result;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWingspan(int wingspan) {
		this.wingspan = wingspan;
	}

	@Override
	public String toString() {
		return "Bird [id=" + id + ", name=" + name + ", wingspan=" + wingspan + ", breed=" + breed + "]";
	}

}
