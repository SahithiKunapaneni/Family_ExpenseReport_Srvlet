package com.rs.fer.main;

import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class LoginMain {

	public static void main(String[] args) {

		// 1.get the input

		String username = "Sahithi";
		String password = "Admin";
		// 2 call the service by passing the input to execute the bussiness logic

		FERService ferService = new FERServiceImpl();
		int userId = ferService.login(username, password);

		// 3 Show the status
		if (userId>0) {
			System.out.println("Welcome to the user :" + username);
		} else {
			System.out.println("Incorrect username/password please try again");
		}

	}

}
