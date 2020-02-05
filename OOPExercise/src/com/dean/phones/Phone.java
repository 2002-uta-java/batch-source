package com.dean.phones;

import com.dean.exceptions.NegativeButtonsException;

public abstract class Phone implements Ringable {

	public int numOfButtons;
	public boolean touchScreen;
	
	public Phone() {
		
	}

	public int getNumOfButtons() {
		return numOfButtons;
	}

	public void setNumOfButtons(int numOfButtons) {
		if(numOfButtons >= 0) {
			this.numOfButtons = numOfButtons;
		} else {
			try {
				throw new NegativeButtonsException("Cannot have a negative number of buttons.");
			} catch (NegativeButtonsException e){
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numOfButtons;
		result = prime * result + (touchScreen ? 1231 : 1237);
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
		Phone other = (Phone) obj;
		if (numOfButtons != other.numOfButtons)
			return false;
		if (touchScreen != other.touchScreen)
			return false;
		return true;
	}

	public boolean isTouchScreen() {
		return touchScreen;
	}

	public void setTouchScreen(boolean touchScreen) {
		this.touchScreen = touchScreen;
	}
	
	
}
