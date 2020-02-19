package MenuInteraction;

import java.util.Scanner;

import userinteraction.UserInterfaceImpl;

public class MainMenu {
	protected Scanner sc = new Scanner(System.in);
	UserInterfaceImpl n = new UserInterfaceImpl();

	public MainMenu() {
		super();
	}

	public void displayMenu() {
		System.out.println("Welcome to Jungle Banking\n");
		System.out.println("Enter the following options to proceed");
		System.out.println("1) Existing User Login\n2) Create a New Login\n3) Exit System");
		System.out.println("Your choice is: ");
	}
	
	public void MainMenuInput() {
		while (true) {
			displayMenu();
			switch (sc.nextLine().trim()) {
			case "1":
				System.out.println("Follow the prompts\n");
				n.userValidation();
				break;
			case "2":
				System.out.println("Follow the prompts\n");
				 n.newUserInput();
				 break;
				 
			case "3":
				 System.out.println("Thank you for banking with us. Have a great day");
				 sc.close();
				 System.exit(0);
				 break;
			default: System.out.println("Try again. Thank you");
				     continue;
				
			
			}
		}
	}

	public Scanner getSc() {
		return sc;
	}

	public void setSc(Scanner sc) {
		this.sc = sc;
	}
}
