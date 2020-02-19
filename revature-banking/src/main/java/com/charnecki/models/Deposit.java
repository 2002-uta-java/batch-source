package com.charnecki.models;

public class Deposit extends Transaction {

	private static final long serialVersionUID = 1L;
	
	public Deposit() {
		super();
	}
	
	public Deposit(int id, int amount, int acctId, String note) {
		super(id, amount, acctId, note);
	}

	@Override
	public int transact() {
		// TODO Auto-generated method stub
		return 0;
	}

}
