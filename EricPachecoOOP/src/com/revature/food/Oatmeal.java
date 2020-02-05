package com.revature.food;
/**Oatmeal class this will be concrete and use the breakfest interface
 * 
 * @author erpac
 *
 */
public class Oatmeal implements Breakfest {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ready ? 1231 : 1237);
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
		Oatmeal other = (Oatmeal) obj;
		if (ready != other.ready)
			return false;
		return true;
	}
	boolean ready = false;
	public Oatmeal() {
		super();
	}
	/**
	 * getting and setting our values
	 */
	public boolean isReady() {
		return ready;
	}
	public void setReady(boolean ready) {
		this.ready = ready;
	}
	/**overriding the dayReadyMethod from the Breakfest interface
	 * to see if we are ready for the day
	 */
	@Override
	public void dayReady() {
		if(ready) {
			System.out.println("I'm ready! Today is gonna be a great day.");
		}else {
			System.out.println("I wanna go back to bead :( ");
		}
		
	}
}
