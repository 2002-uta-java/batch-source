package com.exercise.piracy;

/*ABSTRACTION:
 * 	this class ensures that each sailor has the members name, rank, duty and mutinous
 *	while also ensure that they implement the corresponding interface. 
 * */

public abstract class SailorAbstract implements Sailing {
	
	/*ENCAPSULATION:
	 * 	Classes derived from sailor abstract will have private data members only accessible, thus ensuring their data is secure 
	 * */
	private String name = "Bob";
	private String duty = "";
	private String rank = "";
	private Boolean mutinous = false;


	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract void report() throws ScuvryDogException;
	
	public void promoted(String newRank, String newDuty) {
		this.setRank(newRank);
		this.setDuty(newDuty);
		System.out.println(getName()+" is a "+newRank+" now"); 
	}

	public String getDuty() {
		return this.duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	@Override
	public String toString() {
		return getClass()+" [name=" + name + ", duty=" + duty + ", rank=" + rank + "]";
	}

	public String getRank() {
		return this.rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
	
	public Boolean getMutinous() {
		return mutinous;
	}

	public void setMutinous(Boolean mutinous) {
		this.mutinous = mutinous;
	}

	
	/*HASHCODE AND EQUALS
	 * Implements equals and hash_code according to to the name, rank, and  duty of the given sailor
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((duty == null) ? 0 : duty.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
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
		SailorAbstract other = (SailorAbstract) obj;
		if (duty == null) {
			if (other.duty != null)
				return false;
		} else if (!duty.equals(other.duty))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (rank == null) {
			if (other.rank != null)
				return false;
		} else if (!rank.equals(other.rank))
			return false;
		return true;
	}



}
