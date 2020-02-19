package userinteraction;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;


import com.revature.model.Account;
import com.revature.model.Customer;
import com.revature.service.AccountService;
import com.revature.service.CustomerService;

import MenuInteraction.UserMenu;


public class UserInterfaceImpl {

	private Scanner sc = new Scanner(System.in);
	protected int id;
	protected String username;
	protected String password;
	public String name;
	public String lastName;
	public String address;
	public int zipcode;
	public String email;
	protected int accountNumber;
	protected double accountBalance;
	public double moneyAmount;
	AccountService a = new AccountService();
	CustomerService c1 = new CustomerService();
	Account number = new Account();
	public UserInterfaceImpl() {
		super();
	}  
	
	public UserInterfaceImpl(int id, String username, String password, String name, String lastName, String address,
			int zipcode, String email, int accountNumber, double accountBalance, double moneyAmount) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.address = address;
		this.zipcode = zipcode;
		this.email = email;
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		this.moneyAmount = moneyAmount;
	}

	public void userValidation() {
		System.out.println("Enter a username\n");
		String username = sc.nextLine();
		System.out.println("Username is "+ username + "\n");
		this.username = username;
		
		System.out.println("Enter password\n");
		String password = sc.nextLine();
		System.out.println("\nPassword entered\n");
		this.password = password;
		System.out.println(password);
		

		
		int id = c1.getCustomerId(username);
		this.id = id;
	    System.out.println(id);
	    setId(id);
	    int account_Number = a.getAccountNumber(id);
	    this.accountNumber = account_Number;
	    System.out.println(account_Number);
	    System.out.println(c1.validationPasswordCheck(id));
		if(password.equals(c1.validationPasswordCheck(id))) {
			System.out.println("Logged on");
			UserMenu u = new UserMenu(this);
			u.UserMenuInput();
		} else {
			System.out.println("Invalid password");
			return;
		} 
	}

	
	public void withdrawFunds() {

		System.out.println("Enter amount you wish to withdraw\n");
		String withdraw = sc.nextLine();
		double moneyAmount = Double.parseDouble(withdraw);
		System.out.println(moneyAmount);
		this.moneyAmount = moneyAmount;
		double balance = a.getBalance(accountNumber);
		if(balance > moneyAmount) {
			a.withdrawFunds(accountNumber, moneyAmount);
		} else {
			System.out.println("Insufficent Funds");
		}
		
		System.out.println("Your new balance is " + a.getBalance(accountNumber) + "\n");
		
		return ;	
		
	}
	
	public void accountBalance() {
		System.out.println("Your account balance is " + a.getBalance(accountNumber) + "\n");
	}
	
	public void depositFunds() {
		System.out.println("Enter amount you wish to deposit\n");
		String deposit = sc.nextLine();
		double moneyAmount = Double.parseDouble(deposit);
		System.out.println(moneyAmount);
		this.moneyAmount = moneyAmount;
		a.depositFunds(accountNumber, moneyAmount);
		a.getBalance(accountNumber);
		
		System.out.println("Your new balance is " + a.getBalance(accountNumber) + "\n");
		
	}
	
	public void newUserInput() {	
		System.out.println("Enter a username\n");
		String username = sc.nextLine();
		System.out.println("Username is "+ username + "\n");
		this.username = username;
		
		System.out.println("Enter password\n");
		String password = sc.nextLine();
		System.out.println("\nPassword entered\n");
		this.password = password;
        
		System.out.println("Enter first name\n");
        String name = sc.nextLine();
        System.out.println("Your name is " + name);
        this.name = name;
        
        System.out.println("Enter last name\n");
        String lastName = sc.nextLine();
        this.lastName = lastName;
        System.out.println("Your name is " + lastName);
        
        System.out.println("Enter your street address");
        String address = sc.nextLine();
        System.out.println("Your address is " + address);
        this.address = address;
        
        System.out.println("Enter email address");
        String email = sc.nextLine();
        this.email = email;
        System.out.println("Your email is " + email);
        
        System.out.println("Enter zipcode");
        int zipcode = sc.nextInt();
        this.zipcode = zipcode;
        System.out.println("Your zipcode is " + zipcode);
        Customer customer = new Customer(username, password, name, lastName, address, zipcode, email); 
        
        CustomerService c = new CustomerService();
        c.createCustomer(customer);	
		
        System.out.println("Creating an account");
	    
        int id = c.getCustomerId(username);
        System.out.println(id);
	    double balance = 00.00;
		this.accountBalance = balance;
		System.out.println(balance);
		Random account = new Random();
		int num = (account.nextInt(100000000) % 10000000);
		this.accountNumber = num;
		
		Account number = new Account(num, balance, id);
		System.out.println(number);
		AccountService a = new AccountService();
		a.newAccount(number);
		System.out.println("Your account number is " + num);
		System.out.println("Please sign in");
	    
	}

	public Scanner getSc() {
		return sc;
	}

	public void setSc(Scanner sc) {
		this.sc = sc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public double getMoneyAmount() {
		return moneyAmount;
	}

	public void setMoneyAmount(double moneyAmount) {
		this.moneyAmount = moneyAmount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountBalance, accountNumber, address, email, id, lastName, moneyAmount, name, password,
				sc, username, zipcode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserInterfaceImpl other = (UserInterfaceImpl) obj;
		return Double.doubleToLongBits(accountBalance) == Double.doubleToLongBits(other.accountBalance)
				&& accountNumber == other.accountNumber && Objects.equals(address, other.address)
				&& Objects.equals(email, other.email) && id == other.id && Objects.equals(lastName, other.lastName)
				&& Double.doubleToLongBits(moneyAmount) == Double.doubleToLongBits(other.moneyAmount)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(sc, other.sc) && Objects.equals(username, other.username) && zipcode == other.zipcode;
	}

	@Override
	public String toString() {
		return "UserInterfaceImpl [sc=" + sc + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", lastName=" + lastName + ", address=" + address + ", zipcode=" + zipcode + ", email=" + email
				+ ", accountNumber=" + accountNumber + ", accountBalance=" + accountBalance + ", moneyAmount="
				+ moneyAmount + ", getSc()=" + getSc() + ", getUsername()=" + getUsername() + ", getPassword()="
				+ getPassword() + ", getName()=" + getName() + ", getLastName()=" + getLastName() + ", getAddress()="
				+ getAddress() + ", getZipcode()=" + getZipcode() + ", getEmail()=" + getEmail()
				+ ", getAccountNumber()=" + getAccountNumber() + ", getAccountBalance()=" + getAccountBalance()
				+ ", getMoneyAmount()=" + getMoneyAmount() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	
}
