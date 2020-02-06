package com.revature.pillars;

/* Pillar is the base class of my hierarchy.  Tenet and Column directly derive from it.
 * That derivation is an example of inheritance, one of the pillars of OOP.
 */

public abstract class Pillar {
	protected String name;
	
	public Pillar() {
		name = "unnamed";
	}
	
	public Pillar(String n) {
		name = n;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Pillar other = (Pillar) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
