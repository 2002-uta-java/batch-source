package com.revature.pillars;

import com.revature.construction.Buildable;
import com.revature.construction.ConstructionException;

public class Column extends Pillar implements Buildable {
	/* The height field being private and having getters and setters
	 * are an example of encapsulation.
	 */
	private int height;
	private boolean built;
	
    public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	/* With the three constructors with differing signatures, we 
	 * see the overloading type of polymorphism.
	 */
	public Column() {
    	height = 0;
    	name = "random";
    }
    
    public Column(int h) {
    	this();
    	height = h;
    }
    
    public Column(String name) {
    	this.name = name;
    	height = 0;
    }

    // The two build methods are another instance of overloading.
    @Override
	public void build() throws ConstructionException {
    	
    	build(height);
	}

	public void build(int height) throws ConstructionException {
		if(height < 1) {
			throw new ConstructionException("Invalid height of " + height);
		}
		if(built) {
    		throw new ConstructionException("Can't build twice");
    	}
		this.height = height;
		built = true;
	}

	// This isn't really overriding as the method it's overriding is abstract.
	// So there's no reimplementation here, just implementation.
	@Override
	public void raze() throws ConstructionException {
		//height = 0;		
		built = false;
	}

	public String toString() {
		return (built ? "built " : "unbuilt " ) + name + " column of height " + height;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + height;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Column other = (Column) obj;
		if (height != other.height)
			return false;
		return true;
	}

	
}
