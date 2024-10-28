package com.rs.fer.servlet;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rs.fer.bean.Expense;
import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;
import com.rs.fer.util.layoutUtil;

@WebServlet("/deleteExpense")

public class DeleteExpenseServlet extends HttpServlet {

	FERService ferService = null;

	@Override
	public void init() throws ServletException {
		ferService = new FERServiceImpl();

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Header & leftFrame
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		layoutUtil.showHeaderAndLeftFrame(request, response, out, session);
		// Body
		// 1.get the input

		int expenseId = Integer.parseInt(request.getParameter("expenseId"));

		// 2 call the service by passing the input to execute the bussiness logic

		boolean isDeleteExpense = ferService.deleteExpense(expenseId);

		// 3 Show the status
		if (isDeleteExpense) {
			out.println("Expense Deleted  sucessfuly......");
		} else {
			out.println("Delete is failed");
		}
		// footer
		layoutUtil.showFooter(request, response);
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.

	}

	@Override
	public void destroy() {
		ferService = null;
	}
}
