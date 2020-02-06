package com.hoang.vehicle;

public class Car extends Vehicle {
	
	// excapsulation
	/* this is encapsulation because I'm protecting car object 
	 * data from direct access and manipulation
	 * 
	 */
	private int seats;
	private int doors;
	
	// polymorphism
	/* this is polymorphism because of constructor overloading
	* 	Constructor overloading of Car with different parameters
	*/
	public Car() {
		super();
		setNumOfWheels(4);
	}
	
	public Car(int seats, int doors) {
		this();
		this.seats = seats;
		this.doors = doors;
	}
	
	public int getSeats() {
		return this.seats;
	}
	
	public int getDoors() {
		return this.doors;
	}
	
	public void setSeats(int seats) {
		this.seats = seats;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + doors;
		result = prime * result + seats;
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
		Car other = (Car) obj;
		if (doors != other.doors)
			return false;
		if (seats != other.seats)
			return false;
		return true;
	}

	public void setDoors(int doors) {
		this.doors = doors;
	}
	
	// polymorphism
	/* this is polymorphism because of overriding method
	 * Method of parent class is being overwritten by method of subclass or childclass
	 */
	@Override
	public void weight() {
		System.out.println("Car weight: 3000 lbs");
	}

	@Override
	public void speed() {
		System.out.println("Car max speed: 200 mph");
	}

	@Override
	public void horsePower() {
		System.out.println("Car horsepower: 450 hp");
	}

	@Override
	public String toString() {
		return "Car [seats=" + seats + ", doors=" + doors + "]";
	}
	
	
	
	
	

}
