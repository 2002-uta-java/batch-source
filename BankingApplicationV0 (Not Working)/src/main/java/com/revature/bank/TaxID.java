package com.revature.bank;

/**
 * This class is used to uniquely identify a person. No two people should have
 * the same TaxID
 * 
 * @author Jared F Bennatt
 *
 */
public final class TaxID {
	private final String id;

	public TaxID(final String id) {
		this.id = id;
	}

	public boolean matches(final String id) {
		return this.id.equals(id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaxID other = (TaxID) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
