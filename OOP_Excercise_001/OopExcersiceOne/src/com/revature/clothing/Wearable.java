package com.revature.clothing;

// An interface is an example of Abstraction. Here we are using the Wearable interface
// to ensure that objects will have the functionality of putting on or taking off an item
// of clothing. We also provide a status check to see if the item is currently being worn.

public interface Wearable {
	
	// the following three methods are not fully implemented
	public void putOn();
	
	public void takeOff();

}