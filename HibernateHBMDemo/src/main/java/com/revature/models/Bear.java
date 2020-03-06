package com.revature.models;

import java.util.ArrayList;
import java.util.List;

public class Bear {
	private List<Beehive> beehives = new ArrayList<>();
	private Cave cave;
	private int id;
	private String name;

	public Bear() {
		super();
		// TODO Auto-generated constructor stub
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

	public List<Beehive> getBeehives() {
		return beehives;
	}

	public Cave getCave() {
		return cave;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
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

	public void setBeehives(List<Beehive> beehives) {
		this.beehives = beehives;
	}

	public void setCave(Cave cave) {
		this.cave = cave;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Bear [id=" + id + ", name=" + name + ", cave=" + cave + ", beehives=" + beehives + "]";
	}

}
