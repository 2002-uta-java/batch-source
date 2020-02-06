package com.chai.sean.bladedTools;

//DoubleBevelBlade inherits from Blade using extends
//Example of Specialization
public abstract class DoubleBevelBlade extends Blade{
	
	public DoubleBevelBlade() {
		super();
		setBevelNum(2);
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
