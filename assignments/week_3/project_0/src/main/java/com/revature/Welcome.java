package com.revature;

import java.util.Scanner;

public class Welcome {
	
	private static Scanner sc = new Scanner(System.in);
	
	public void welcomeScreen() {
		
		boolean proceed = true;
		
		do {
			System.out.println("________________________________________________________________________________________________________________________");
			System.out.println("#                                                                                                                      #");
			System.out.println("#                                               CONSOLE BANK APPLICATION                                               #");
			System.out.println("#----------------------------------------------------------------------------------------------------------------------#");
			System.out.println("#  TYPE: (1) TO LOG IN  |  (2) TO CREATE NEW USER  |  (3) TO EXIT                                                      #");
			System.out.println("#----------------------------------------------------------------------------------------------------------------------#");
			System.out.println(">");
			System.out.println(">");
			System.out.print("> INPUT: ");
			
			String userInput = sc.nextLine();
			
			
			switch (userInput) {
			case "1":
				LogIn logIn = new LogIn();
				logIn.logIntoAccount();
				break;
			case "2":
				CreateAccount cc = new CreateAccount();
				cc.CreateNewAccount();
				break;
			case "3":
				proceed = false;
				System.out.println(">");
				System.out.println(">");
				System.out.println("#----------------------------------------------------------------------------------------------------------------------#");
				System.out.println("> GOODBYE..............................................................................................................#");
				System.out.println("#----------------------------------------------------------------------------------------------------------------------#");
				break;
			default:
				System.out.println(">");
				System.out.println(">");
				System.out.println("> Invalid input, please try again. PRESS ENTER TO CONTINUE...");
				System.out.println(">");
				System.out.println(">");
				System.out.println("#----------------------------------------------------------------------------------------------------------------------#");
				sc.nextLine();
			}	
		
		} while (proceed);
		
	}

}
