package com.dean.phones;

public class Sidekick extends NokiaFlip {
	
	public Sidekick() {
		super();
		setNumOfButtons(40);
	}
	
	public Sidekick(int numOfKeyboardButtons) {
		this.numOfKeyboardButtons = numOfKeyboardButtons;
	}
	
	@Override 
	public void ring() { 
		System.out.println("Sidekick is ringing");
	}
	
	@Override 
	public void setNumOfKeyboardButtons(int numOfKeyboardButtons) {
		super.setNumOfKeyboardButtons(numOfKeyboardButtons);
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
