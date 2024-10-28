package com.rs.fer.main;

import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class RegistrationMain {

	public static void main(String[] args) {

		// 1.get the input

		User user = new User();
		user.setFirstName("Vinay");
		user.setMiddleName("Kumar");
		user.setLastName("Bikki");
		user.setEmail("bikkisai@rs.com");
		user.setUsername("Vinay");
		user.setPassword("Vinays");
		user.setMobile("1234567890");

		// 2 call the service by passing the input to execute the bussiness logic

		FERService ferService = new FERServiceImpl();
		boolean isRegister = ferService.registration(user);

		// 3 Show the status
		if (isRegister) {
			System.out.println("User registration is done sucessfuly......");
		} else {
			System.out.println("User registration is failed");
		}

	}

}
