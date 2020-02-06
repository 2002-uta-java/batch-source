package com.revature.abstracting;

public class Dog extends AbstractAnimal implements Behaving{

	//If i dont make this private, will color still be private due to inher from abstract class?
	//String color = "Brown";
	



	@Override
	public void MakesNoise() {
		System.out.println("The Dog is barking!");
		
	}

	@Override
	public String Eating(String food) throws CustomException {
		if(food == "Catfood") {
			throw new CustomException();
		} else {
			System.out.println("The dog is eating " + food);
		}
		return food;
		
	}

	@Override
	public String Eating(String food1, String food2) {
		System.out.println("The dog is eating " + food1 + " and " + food2);
		return null;
	}


	
	
	
}
