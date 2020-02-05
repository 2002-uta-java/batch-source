package com.revature.pillars;

import com.revature.construction.Buildable;
import com.revature.construction.ConstructionException;

public class Column extends Pillar implements Buildable {
	private int height;
	private boolean built;
	
    public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

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
