package com.revature.assignment1;

import java.util.Objects;

public class Residential extends Buildings implements HomePrice { // extended to superclass Inherit

	private int num_rooms;  // access modifier private keeps it to this class unchangeable; Encapsulation 
	private String size;
	
	public Residential() {
		super();    // calling on to the superclass
	}
	
	public Residential(String size, int num_rooms) { // constructor method that is complete.
		this();
		this.size = size;
		this.num_rooms = num_rooms;

	}
    
	public void Residential() {
		if(num_rooms>0) {
			this.num_rooms = num_rooms;
		}else {
			try { 
				throw new NegativeRoomsException("Cannot have a home without rooms" +num_rooms); //custom exception
			} catch (NegativeRoomsException e) {
				e.printStackTrace();
			}
		}
	}
// hashcode returns integer number
	@Override
	public int hashCode() {
		return Objects.hash(num_rooms, size);
	}
// boolean created for num_rooms and size
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Residential other = (Residential) obj;
		return num_rooms == other.num_rooms && Objects.equals(size, other.size);
	}

	@Override          //  Overriding the Superclass / Poly
	public String Buildings_Size() {
		return this.size;
	}

	@Override
	public int Building_Rooms() {
		return (int) this.num_rooms;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getNum_rooms() {
		return num_rooms;
	}

	public void setNum_rooms(int num_rooms) {
		this.num_rooms = num_rooms;
	}

	@Override
	public int HomePrice() {
		return num_rooms*1000;
	}

}
