package com.rs.fer.main;

import java.util.List;

import com.rs.fer.bean.Address;
import com.rs.fer.bean.Expense;
import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class UpdateUserMain {

	public static void main(String[] args) {

		// 1.get the input

		int UserId = 2;

		// 2 call the service by passing the input to execute the bussiness logic

		FERService ferService = new FERServiceImpl();

		User user = ferService.getUser(UserId);
		// update Name screen field values
		user.setFirstName("Sai");
		user.setMiddleName("Pavan");
		user.setLastName("Kumar");

		// update contact information
		user.setEmail("sai@gmail.com");
		user.setMobile("9848169719");

		// update Address
		Address address = user.getAddress();

		address.setLineone("5-30");
		address.setLinetwo("Chintalapadu");
		address.setCity("Nandigama");
		address.setState("AP");
		address.setPinCode("50031");
		address.setCountry("IND");

		boolean isUserUpdate = ferService.updateUser(user);

		// 3 Show the status
		if (isUserUpdate) {
			System.out.println("User Profile Updated sucessfuly......");
		} else {
			System.out.println("User Profile Update  is failed");
		}
	}

}
