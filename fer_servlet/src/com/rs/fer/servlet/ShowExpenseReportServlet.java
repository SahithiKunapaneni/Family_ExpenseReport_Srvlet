package com.rs.fer.servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rs.fer.bean.Expense;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;
import com.rs.fer.util.layoutUtil;

@WebServlet("/showExpenseReport")

public class ShowExpenseReportServlet extends HttpServlet {

	FERService ferService = null;

	@Override
	public void init() throws ServletException {
		ferService = new FERServiceImpl();

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		// Header & leftFrame
				PrintWriter out = response.getWriter();
				HttpSession session =request.getSession();
				layoutUtil.showHeaderAndLeftFrame(request, response, out, session);
				// Body
				
				

				// 2 call the service by passing the input to execute the bussiness logic

			
				
				
				request.getRequestDispatcher("ExpenseReportSelection.html").include(request, response);
				// footer 
				layoutUtil.showFooter(request, response);
				
			}

	@Override
	public void destroy() {
		ferService = null;
	}
}
