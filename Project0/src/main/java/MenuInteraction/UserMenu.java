package MenuInteraction;

import userinteraction.UserInterfaceImpl;

public class UserMenu extends MainMenu{

	UserInterfaceImpl n;

	public UserMenu(UserInterfaceImpl n) {
		super();
		this.n = n;
	}
    
	public void displayUserMenu() {
		System.out.println("Welcome to Jungle Banking\n");
		System.out.println("Enter the following options to proceed");
		System.out.println("1) Check Account Balance\n2) Make a deposit\n3) Make a withdrawal\n4) Return to Main Menu");
		System.out.println("Your choice is: ");
	}
	
	public void UserMenuInput() {
		super.getSc();
		super.setSc(sc);
		while(true) {
			displayUserMenu();
			switch (sc.nextLine().trim()) {
			case "1": System.out.println("Follow the prompts\n");
						n.accountBalance();
				break;
			case "2": System.out.println("Follow the prompts\n");
						n.depositFunds();
				break;
			case "3": System.out.println("Follow the prompts\n");
						n.withdrawFunds();
				break;
			case "4": System.out.println("Follow the prompts\n");
						return;

			default: System.out.println("Try again. Thank you");
		     continue;
			
			}
		}
	}
}
