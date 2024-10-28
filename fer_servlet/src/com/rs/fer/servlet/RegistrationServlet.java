package com.rs.fer.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

@WebServlet("/registration")

public class RegistrationServlet extends HttpServlet {
	
	FERService ferService = null;

	@Override
	public void init() throws ServletException {
		 ferService = new FERServiceImpl();
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		// 1.get the input

		User user = new User();
		user.setFirstName(request.getParameter("FirstName"));
		user.setMiddleName(request.getParameter("MiddleName"));
		user.setLastName(request.getParameter("lastName"));
		user.setEmail(request.getParameter("email"));
		user.setUsername(request.getParameter("userName"));
		user.setPassword(request.getParameter("password"));
		user.setMobile(request.getParameter("mobile"));

		// 2 call the service by passing the input to execute the bussiness logic

		
		boolean isRegister = ferService.registration(user);
         String path =null;
         PrintWriter out = response.getWriter();
		// 3 Show the status
		if (isRegister) {
			out.println("User registration is done sucessfuly......");
			path = "Login.html";
		} else {
			out.println("User registration is failed");
			path ="Registration.html";
		}
		request.getRequestDispatcher(path).include(request, response);
	}
	@Override
	public void destroy() {
		 ferService = null;
	}
}
