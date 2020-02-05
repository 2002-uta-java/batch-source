package com.revature.construction;

public interface Buildable {
	public void build() throws ConstructionException;
	//public void build(int height) throws ConstructionException;
	public void raze() throws ConstructionException;
}
