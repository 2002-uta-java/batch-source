package com.chai.sean.bladedTools;

//SingleBeveled Blade inherits from Blade using extends
//Example of specialization
public abstract class SingleBevelBlade extends Blade{
	
	
	public SingleBevelBlade() {
		super();
		setBevelNum(1);
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
