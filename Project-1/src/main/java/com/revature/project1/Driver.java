package com.revature.project1;

import com.revature.project1.daos.AppuserDao;
import com.revature.project1.daos.AppuserDaoImpl;

public class Driver {
	
	public static void main(String[] args) {
		
		AppuserDao ad = new AppuserDaoImpl();
		ad.updateAppuser();
		
		System.out.println(ad.toString());
		
	}

}
