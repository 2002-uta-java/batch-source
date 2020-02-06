package com.revature.construction;

// This is the required interface.  It ensures a class can be built and destroyed.
// The interface is a type of abstraction where the implementation details
// are separated from the behavior specification.

public interface Buildable {
	public void build() throws ConstructionException;
	public void raze() throws ConstructionException;
}
