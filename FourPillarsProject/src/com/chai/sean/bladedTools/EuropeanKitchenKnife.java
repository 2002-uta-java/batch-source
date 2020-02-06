package com.chai.sean.bladedTools;

//EuropeanKitchenKnife inherits from DoubleBevelBlade with extends
//Example of Specialization
public class EuropeanKitchenKnife extends DoubleBevelBlade{
	
	public EuropeanKitchenKnife() {
		super();
	}
	
	public EuropeanKitchenKnife(int handleLengthInches, int spineLengthInches, int primaryBevelAngle, String grindName) {
		super.setHandleLengthInches(handleLengthInches);
		super.setSpineLengthInches(spineLengthInches);
		super.setPrimaryBevelAngle(primaryBevelAngle);
		super.setGrindName(grindName);
	}
	
	//Method Overriding is an example of Runtime Polymorphism or Dynamic Polymorphism. 

	@Override
	public int calculateGrindAngle() {
		int temp = 2*getPrimaryBevelAngle();
		return temp;
	}

	@Override
	public void Cut() {
		System.out.println("Cutting");
		
	}

	@Override
	public void Sharpen() {
		System.out.println("Sharpening into "+ getGrindName() + " grind");
	}

	@Override
	public void Stats() {
		System.out.println("RazorBlade Grind Angle: " + calculateGrindAngle() + "°, Bevel Number: " + getBevelNum()
		+ ", Handle Length: " + getHandleLengthInches() + "\", Spine Length "
		+ getSpineLengthInches() + "\", Grind Name: " + getGrindName() + "]");		
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
