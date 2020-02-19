package main;

import java.util.Scanner;

import daos.HolderDao;
import daos.HolderImplementation;
import models.Holder;

public class Driver {

	public static void main(String[] args) {
		
		HolderDao holderDao = new HolderImplementation();
		char cont = 'y';
		int whichCase = 0;		
        Scanner sc = new Scanner(System.in); 
		
		while(cont == 'y') {
			
			System.out.println("1: Create an new user");
			System.out.println("2: Log in");
			
			whichCase = sc.nextInt();
			
			if (whichCase == 1) {
				sc.nextLine();
				System.out.println("Enter username: ");
				String name = sc.nextLine(); 
				System.out.println("Enter Password: ");
				String pass = sc.nextLine(); 
				
				Holder temp = new Holder(name,pass);
				holderDao.createHolder(temp);
				System.out.println("User Created");
				
			}else if(whichCase == 2) {
				sc.nextLine();
				
				System.out.println("Enter username: ");
				String name = sc.nextLine(); 
				
				Holder temp = holderDao.getHolderByUsername(name);
				if (temp != null) {
					
				System.out.println("Enter Password: ");
				String pass = sc.nextLine(); 
					
					if(temp.getHolderPassword().equals(pass)) {
						System.out.println("1: Widthdrawl");
						System.out.println("2: Deposit");
						
						int subWhichCase = sc.nextInt();
						
						if (subWhichCase == 1) {
							sc.nextLine();
							System.out.println("Enter Amount to Widthdrawl:");
							int subtractAmount = sc.nextInt();
							holderDao.subtractBalance(name, subtractAmount);
							

						}
						else if(subWhichCase == 2){
							sc.nextLine();
							System.out.println("Enter Amount to Deposit");
							int widthdrawlAmount = sc.nextInt();
							holderDao.addBalance(name, widthdrawlAmount);
							
							
						}else{
							System.out.println("Please Choose an Option");
						}
						
					}else {
						System.out.println("Password is Incorrect");
					}
					
				}else {
					System.out.println("User Does Not Exist");
				}
			}else{
			
				System.out.println("Please Choose an Option");
			}
			
			System.out.println("Do you wish to continue (y/n)");
			
			cont = sc.next().charAt(0);
			
		}
		sc.close();
		
	}

}
