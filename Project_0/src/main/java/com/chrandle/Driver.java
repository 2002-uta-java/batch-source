package com.chrandle;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chrandle.models.*;
import com.chrandle.daos.*;
import com.chrandle.services.*;


public class Driver {

	public static void main(String[] args) {
		System.out.println("		PROGRAM START		\n");
		UserService uService = new UserService();
		List<User> users = uService.getUsers();
		
		for (User u: users) {
			System.out.println(u.toString());
		}
		
		System.out.println("\n		PROGRAM END		");
		return;
		
	}
}
