package com.rs.fer.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;
import com.rs.fer.util.layoutUtil;

@WebServlet("/login")

public class LoginServlet extends HttpServlet {
	
	FERService ferService = null;

	@Override
	public void init() throws ServletException {
		 ferService = new FERServiceImpl();
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1.get the input

				String username = request.getParameter("userName");
				String password = request.getParameter("password");
				// 2 call the service by passing the input to execute the bussiness logic

				int userId = ferService.login(username, password);
                 PrintWriter out =response.getWriter();
				// 3 Show the status
				if (userId >0) {
					
					HttpSession session = request.getSession();
					session.setAttribute("username",username);
					session.setAttribute("userId",userId);
					
					// Header & leftFrame
					
					layoutUtil.showHeaderAndLeftFrame(request, response, out, session);
					// Body
					out.println("Welcome to the user :" + username+".....!");
					
					// footer 
					layoutUtil.showFooter(request, response);
				} else {
					out.println("Incorrect username/password please try again");
					request.getRequestDispatcher("Login.html").include(request, response);
				}
	}
	@Override
	public void destroy() {
		 ferService = null;
	}
}
