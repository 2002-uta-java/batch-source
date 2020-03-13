package com.revature.models;

public class Habitat {

	private int id;
	private String name;
	private int temperature;
	private int size;
	
	public Habitat() {
		super();
	}

	public Habitat(int id, String name, int temperature, int size) {
		super();
		this.id = id;
		this.name = name;
		this.temperature = temperature;
		this.size = size;
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

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + size;
		result = prime * result + temperature;
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
		Habitat other = (Habitat) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (size != other.size)
			return false;
		if (temperature != other.temperature)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Habitat [id=" + id + ", name=" + name + ", temperature=" + temperature + ", size=" + size + "]";
	}
	
}
