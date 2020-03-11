package com.revature.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// don't need @Component because we declared this bean in the SpringConfig class
@Component
public class Bear {
	@Autowired
	private Cave cave;
	private int id;
	private String name;

	public Bear() {
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
		Bear other = (Bear) obj;
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
		result = prime * result + ((cave == null) ? 0 : cave.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
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
		return "Bear [name=" + name + ", id=" + id + ", cave=" + cave + "]";
	}
}
