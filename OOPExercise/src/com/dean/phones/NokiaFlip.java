package com.dean.phones;


public class NokiaFlip extends Phone {
	
	public int numOfKeyboardButtons;

	public NokiaFlip() {
		super();
		setNumOfButtons(20);
		setTouchScreen(false);
	}
	
	public NokiaFlip(int numOfKeyboardButtons) {
		this.numOfKeyboardButtons = numOfKeyboardButtons;
	}
	
	public void ring() {
		System.out.println("Nokia flip phone is ringing");
	}

	public int getNumOfKeyboardButtons() {
		return numOfKeyboardButtons;
	}

	public void setNumOfKeyboardButtons(int numOfKeyboardButtons) {
		this.numOfKeyboardButtons = numOfKeyboardButtons;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + numOfKeyboardButtons;
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
		NokiaFlip other = (NokiaFlip) obj;
		if (numOfKeyboardButtons != other.numOfKeyboardButtons)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NokiaFlip [numOfKeyboardButtons=" + numOfKeyboardButtons + "]";
	}
	

}
