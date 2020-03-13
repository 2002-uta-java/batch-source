package com.revature.models;

import java.io.Serializable;

public class Bird implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private int wingspan;
	private String breed;
	private Habitat habitat;

	public Bird() {
		super();
	}

	public Bird(final String name, final String breed) {
		super();
		this.name = name;
		this.breed = breed;
	}

	public Bird(int id, String name, int wingspan, String breed, final Habitat habitat) {
		super();
		this.id = id;
		this.name = name;
		this.wingspan = wingspan;
		this.breed = breed;
		this.habitat = habitat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWingspan() {
		return wingspan;
	}

	public void setWingspan(int wingspan) {
		this.wingspan = wingspan;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public Habitat getHabitat() {
		return habitat;
	}

	public void setHabitat(Habitat habitat) {
		this.habitat = habitat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((breed == null) ? 0 : breed.hashCode());
		result = prime * result + ((habitat == null) ? 0 : habitat.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + wingspan;
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
		Bird other = (Bird) obj;
		if (breed == null) {
			if (other.breed != null)
				return false;
		} else if (!breed.equals(other.breed))
			return false;
		if (habitat == null) {
			if (other.habitat != null)
				return false;
		} else if (!habitat.equals(other.habitat))
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

	@Override
	public String toString() {
		return "Bird [id=" + id + ", name=" + name + ", wingspan=" + wingspan + ", breed=" + breed + ", habitat="
				+ habitat + "]";
	}

}
