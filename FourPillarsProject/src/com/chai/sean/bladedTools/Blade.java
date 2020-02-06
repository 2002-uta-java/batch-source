package com.chai.sean.bladedTools;

import com.chai.sean.exceptions.NegativeHandleLengthException;
import com.chai.sean.exceptions.ZeroOrLessAngleException;
import com.chai.sean.exceptions.ZeroOrLessBevelException;
import com.chai.sean.exceptions.ZeroOrLessSpineLengthException;


//Blade Displays inheritance through implementing interfaces
public abstract class Blade implements CalcGrindAngleable, Cuttable, Sharpenable, StatReadoutable{
	
	//Private variables only allow access through public getters and setters
	//Example of Encapsulation
	private int bevelNum;
	private double handleLengthInches;
	private double spineLengthInches;
	private String grindName = "unamed";
	private int primaryBevelAngle;

	
	public Blade() {
		super(); //calls object class
	}

	public int getBevelNum() {
		return bevelNum;
	}

	public void setBevelNum(int bevelNum) {
		
		if (bevelNum >0) {
		this.bevelNum = bevelNum;
		}
		else try{
			throw new ZeroOrLessBevelException("Bevel Number Must be greater than 0");
		}catch(ZeroOrLessBevelException e) {
			e.printStackTrace();
		}
	}

	public double getHandleLengthInches() {
		return handleLengthInches;
	}

	public void setHandleLengthInches(double handleLengthInches) {
	
		if(handleLengthInches >= 0) {
		this.handleLengthInches = handleLengthInches;
		}
		else try{
			throw new NegativeHandleLengthException("Handle Length must be 0 or Greater");
		}catch(NegativeHandleLengthException e) {
			e.printStackTrace();
		}
	}

	public double getSpineLengthInches() {
		return spineLengthInches;
	}

	public void setSpineLengthInches(double spineLengthInches) {
		
		if(spineLengthInches>0) {
		this.spineLengthInches = spineLengthInches;
		}
		else try {
			throw new ZeroOrLessSpineLengthException("Spine length must be greater than 0");
		}catch(ZeroOrLessSpineLengthException e) {
			e.printStackTrace();
		}	
	}

	public String getGrindName() {
		return grindName;
	}

	public void setGrindName(String grindName) {
		
		if(grindName!= null) {
		this.grindName = grindName;
		}
	}
	
	public int getPrimaryBevelAngle() {
		return primaryBevelAngle;
	}

	public void setPrimaryBevelAngle(int primaryBevelAngle) {
		
		if (primaryBevelAngle>0) {
			this.primaryBevelAngle = primaryBevelAngle;
		}
		
		else try {
			
			throw new ZeroOrLessAngleException("Angle Must be greater than 0");
			
		}catch(ZeroOrLessAngleException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bevelNum;
		result = prime * result + ((grindName == null) ? 0 : grindName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(handleLengthInches);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + primaryBevelAngle;
		temp = Double.doubleToLongBits(spineLengthInches);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Blade other = (Blade) obj;
		if (bevelNum != other.bevelNum)
			return false;
		if (grindName == null) {
			if (other.grindName != null)
				return false;
		} else if (!grindName.equals(other.grindName))
			return false;
		if (Double.doubleToLongBits(handleLengthInches) != Double.doubleToLongBits(other.handleLengthInches))
			return false;
		if (primaryBevelAngle != other.primaryBevelAngle)
			return false;
		if (Double.doubleToLongBits(spineLengthInches) != Double.doubleToLongBits(other.spineLengthInches))
			return false;
		return true;
	}
	
	
	
}
