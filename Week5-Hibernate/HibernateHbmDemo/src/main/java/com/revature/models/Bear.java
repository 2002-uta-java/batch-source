package com.revature.models;

import java.util.ArrayList;
import java.util.List;

public class Bear {
	
	private int id;
	private String name;
	private Cave cave;
	private List<Beehive> beehives = new ArrayList<>();

	public Bear() {
		super();
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

	public Cave getCave() {
		return cave;
	}

	public void setCave(Cave cave) {
		this.cave = cave;
	}

	public List<Beehive> getBeehives() {
		return beehives;
	}

	public void setBeehives(List<Beehive> beehives) {
		this.beehives = beehives;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beehives == null) ? 0 : beehives.hashCode());
		result = prime * result + ((cave == null) ? 0 : cave.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Bear other = (Bear) obj;
		if (beehives == null) {
			if (other.beehives != null)
				return false;
		} else if (!beehives.equals(other.beehives))
			return false;
		if (cave == null) {
			if (other.cave != null)
				return false;
		} else if (!cave.equals(other.cave))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bear [id=" + id + ", name=" + name + ", cave=" + cave + ", beehives=" + beehives + "]";
	}
	
}
