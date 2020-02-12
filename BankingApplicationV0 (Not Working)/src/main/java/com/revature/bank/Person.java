package com.revature.bank;

public class Person {
	final TaxID taxId;
	private final String firstName;
	private final String lastName;
	private final int hashCode;

	public Person(final String firstName, final String lastName, final TaxID id) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.taxId = id;
		this.hashCode = getHashCode(firstName, lastName, id);
	}

	public boolean matches(final String firstName, final String lastName, final TaxID taxId) {
		return this.firstName.equals(firstName) && this.lastName.equals(lastName) && this.taxId.equals(taxId);
	}

	private static int getHashCode(final String firstName, final String lastName, final TaxID taxId) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((taxId == null) ? 0 : taxId.hashCode());
		return result;
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (taxId == null) {
			if (other.taxId != null)
				return false;
		} else if (!taxId.equals(other.taxId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
}
