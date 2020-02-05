package com.dean.phones;

public class IPhoneX extends Phone {
	
	private Integer diagonalScreenLength;
	
	public IPhoneX() {
		super();
		setNumOfButtons(3);
		setTouchScreen(true);
	}
	
	public IPhoneX(int diagonalScreenLength) {
		setNumOfButtons(3);
		setTouchScreen(true);
		this.diagonalScreenLength = diagonalScreenLength;
	}

	
	public void ring() {
		System.out.println("iPhoneX is ringing");
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((diagonalScreenLength == null) ? 0 : diagonalScreenLength.hashCode());
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
		IPhoneX other = (IPhoneX) obj;
		if (diagonalScreenLength == null) {
			if (other.diagonalScreenLength != null)
				return false;
		} else if (!diagonalScreenLength.equals(other.diagonalScreenLength))
			return false;
		return true;
	}

	public int getDiagonalScreenLength() {
		return diagonalScreenLength;
	}

	public void setDiagonalScreenLength(Integer diagonalScreenLength) {
		if(diagonalScreenLength.equals(7)) {
			System.out.println("your phone is an iphone max");
		}
		this.diagonalScreenLength = diagonalScreenLength;
	}
	
	@Override
	public boolean isTouchScreen() {
		return super.isTouchScreen();
	}
	
	@Override
	public int getNumOfButtons() {
		return super.getNumOfButtons();
	}
	
	
	@Override
	public String toString() {
		return "IPhoneX [diagonalScreenLength=" + diagonalScreenLength + "]";
	}
	
	

}
