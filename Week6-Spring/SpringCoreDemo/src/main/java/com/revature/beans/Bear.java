package com.revature.beans;

public abstract class Bear {
	
	protected int id;
	protected String name;
	protected Cave cave;
	
	public Bear() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	public Cave getCave() {
//		return cave;
//	}
	public void setCave(Cave cave) {
		this.cave = cave;
	}
	
	

}
