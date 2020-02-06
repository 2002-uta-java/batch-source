package com.hoang.vehicle;


// inheritance
/* the child acquires all the properties and behaviors from the parent
 * In this case, my child class (van class) inherit all my parent class (car class)
 * properties and behaviors.
 */

public class Van extends Car{
	
	public Van() {
		super();
	}
	
	public Van(int seats, int doors) {
		super.setSeats(seats);
		super.setDoors(doors);	
	}

	@Override
	public int getSeats() {
		return super.getSeats();
	}

	@Override
	public int getDoors() {
		return super.getDoors();
	}

	@Override
	public void setSeats(int seats) {
		super.setSeats(seats);
	}

	@Override
	public void setDoors(int doors) {
		super.setDoors(doors);
	}
	
	// polymorphism
	// override the parent class method
	@Override
	public void weight() {
		System.out.println("Van weight: 4000 lbs");
	}
	
	@Override
	public void speed() {
		System.out.println("Van max speed: 160mph");
	}
	
	@Override
	public void horsePower() {
		System.out.println("Van horsepower: 320hp");
	}

	@Override
	public String toString() {
		return "Van [seats=" + getSeats() + ", doors=" + getDoors() + "]";
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}
	
	
	
	
}
