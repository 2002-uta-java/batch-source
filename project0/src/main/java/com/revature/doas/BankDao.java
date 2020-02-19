package com.revature.doas;

import com.revature.models.Bank;

public interface BankDao {
	public int createAccount(Bank b);

	public int depositMoney(Bank b, double amount);
	public int withdrawMoney(Bank b, double amount);
	public Void viewBallance(Bank b);
	public Void login(String userName, String passWord, Bank b);
	public Void logout();

	public boolean checkUserNameAndPassWord(String userName, String passWord, Bank b);
}
